package uz.gita.memorygame.ui.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.Window
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygame.R
import uz.gita.memorygame.animation.GameAnimation
import uz.gita.memorygame.data.LeaderEntity
import uz.gita.memorygame.databinding.FragmentPlaygroundBinding
import uz.gita.memorygame.ui.viewmodel.PlaygroundViewModel

@AndroidEntryPoint
class PlaygroundFragment : Fragment(R.layout.fragment_playground) {

    private val binding by viewBinding(FragmentPlaygroundBinding::bind)
    private val animator by lazy { GameAnimation() }
    private val args by navArgs<PlaygroundFragmentArgs>()
    private var hits = 0
    var isTopUser = false
    private var currentTime = 0L
    private var duration = 0L

    private val viewModel: PlaygroundViewModel by viewModels()

    private lateinit var mediaPlayer: MediaPlayer
    var currentPos = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch {
            viewModel.insertedFlow.collect {
                Toast.makeText(
                    requireContext(),
                    "User has been added to leaderboard",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launch {

            viewModel.isTopUser.collect() {
                isTopUser = it

            }
            Log.d("TAGDF", "showWinDialog: $isTopUser")

        }



        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.lost_in_space)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        binding.chronometer.start()

        binding.pic2.setOnClickListener {
            animator.animateZoomIn(it)
        }
        binding.pic4.setOnClickListener {
            animator.animateZoomIn(it)
        }
        binding.pic3.setOnClickListener {
            animator.animateZoomIn(it)
        }

        binding.homeBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.voiceBtn.setOnClickListener {

            it as ToggleButton

            if (mediaPlayer.isPlaying) {
                currentPos = mediaPlayer.currentPosition;
                mediaPlayer.pause();

            } else {
                mediaPlayer.seekTo(currentPos);
                mediaPlayer.start();

            }
        }

        setImages(0L)


    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setImages(time: Long) {


        hits = 0
        var images = getList()
        currentTime = System.currentTimeMillis()

        var counter = 0
        var ind = -1
        var selectedImage: ImageView? = null

        resetImages(time)
        getImages().forEachIndexed { index, view ->

            view.setOnClickListener {

                it as ImageView
                if (it.tag.toString() == "close") {
                    animator.animateFlipOut(it, images[index])
                    it.tag = "open"
                    if (selectedImage != null) {

                        if (images[index] == images[ind]) {

                            animator.animateZoomIn(selectedImage!!)
                            animator.animateZoomIn(it)
                            counter++

                            if (counter == images.size / 2) {
                                binding.confettiLottie.playAnimation()
                                duration = System.currentTimeMillis() - currentTime
                                viewModel.finishGame(duration, args.level)
                                showWinDialog()
                                binding.chronometer.stop()
                            }

                        } else {
                            animator.animateFlipIn(selectedImage!!, 1000L)
                            animator.animateFlipIn(it, 1000L)
                            selectedImage!!.tag = "close"
                            it.tag = "close"

                        }
                        increaseHints()

                        selectedImage = null
                        ind = -1
                    } else {
                        ind = index
                        selectedImage = it
                        it.tag = "open"
                    }


                } else {
                    it.tag = "close"
                    animator.animateFlipIn(it, 0L)
                    selectedImage = null
                    ind = -1
                }
            }


        }
    }

    private fun getImages(): List<ImageView> {
        val list = ArrayList<ImageView>()
        when (args.level) {
            "easy" -> {
                for (i in 0..11) {
                    val image = binding.container.getChildAt(i) as ImageView
                    image.visibility = VISIBLE
                    list.add(image)
                }
            }
            "medium" -> {
                for (i in 0..19) {
                    val image = binding.container.getChildAt(i) as ImageView
                    image.visibility = VISIBLE
                    list.add(image)
                    binding.container.setPadding(0, 0, 0, 0)

                }
            }
            "hard" -> {
                for (i in 0..23) {
                    val image = binding.container.getChildAt(i) as ImageView
                    image.visibility = VISIBLE
                    list.add(image)
                    binding.container.setPadding(0, 0, 0, 0)

                }
            }
        }
        return list
    }

    private fun resetImages(time: Long) = lifecycleScope.launch {
        delay(time)
        getImages().forEach {
            delay(50L)
            animator.animateZoomOut(it)
            it.apply {
                setImageResource(R.drawable.ic_question_mark)
                tag = "close"
                rotationY = 0f

            }

        }
    }

    private fun getList(): ArrayList<Int> {
        val list = ArrayList<Int>()

        list.add(R.drawable.ic_planet_01)
        list.add(R.drawable.ic_planet_02)
        list.add(R.drawable.ic_planet_03)
        list.add(R.drawable.ic_planet_04)
        list.add(R.drawable.ic_planet_05)
        list.add(R.drawable.ic_planet_06)

        when (args.level) {

            "medium" -> {
                list.add(R.drawable.ic_planet_07)
                list.add(R.drawable.ic_planet_08)
                list.add(R.drawable.ic_planet_09)
                list.add(R.drawable.ic_planet_10)

            }
            "hard" -> {
                list.add(R.drawable.ic_planet_07)
                list.add(R.drawable.ic_planet_08)
                list.add(R.drawable.ic_planet_09)
                list.add(R.drawable.ic_planet_10)
                list.add(R.drawable.ic_planet_11)
                list.add(R.drawable.ic_planet_12)
            }
        }
        list.addAll(list)
//
        list.shuffle()

        return list
    }

    @SuppressLint("SetTextI18n")
    fun increaseHints() {
        hits++
        binding.scoreTv.text = "Hits : $hits"
    }

    @SuppressLint("SetTextI18n")
    private fun showWinDialog() {

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.win_dialog)


        val timeTv = dialog.findViewById<TextView>(R.id.time_tv)
        val hitsTv = dialog.findViewById<TextView>(R.id.hits_tv)
        val homeBtn = dialog.findViewById<Button>(R.id.home_button)
        val retryBtn = dialog.findViewById<Button>(R.id.retry_button)
        val nameEt = dialog.findViewById<EditText>(R.id.name_et)
//
        timeTv.text = "Time - ${binding.chronometer.text}"
        hitsTv.text = "Hits - ${this.hits}"


        nameEt.isVisible = isTopUser

        homeBtn.setOnClickListener {
            Log.d("TAGDF", "showWinDialog: $isTopUser")
            if (isTopUser) {
                viewModel.insertUserToLeaderboard(
                    LeaderEntity(
                        name = nameEt.text.toString(),
                        duration = binding.chronometer.text.toString(),
                        drawingTime = duration,
                        category = args.level
                    )
                )
            }
            dialog.dismiss()
            requireActivity().onBackPressed()
        }

        retryBtn.setOnClickListener {

            Log.d("TAGDF", "showWinDialog: $isTopUser")


            if (isTopUser) {
                viewModel.insertUserToLeaderboard(
                    LeaderEntity(
                        name = nameEt.text.toString(),
                        duration = binding.chronometer.text.toString(),
                        drawingTime = duration,
                        category = args.level
                    )
                )
            }

            dialog.dismiss()
            binding.scoreTv.text = "Hits : 0"
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.start()
            setImages(1000L)

        }
        dialog.show()

    }

    override fun onPause() {
        super.onPause()
        binding.voiceBtn.isChecked = true
        mediaPlayer.pause()
    }


}

