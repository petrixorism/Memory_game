package uz.gita.memorygame.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import uz.gita.memorygame.R
import uz.gita.memorygame.animation.GameAnimation
import uz.gita.memorygame.databinding.FragmentPlaygroundBinding

class PlaygroundFragment : Fragment(R.layout.fragment_playground) {

    private val binding by viewBinding(FragmentPlaygroundBinding::bind)
    private val animator by lazy { GameAnimation() }

    var isFlipped = true
    var canAnimate = true
    var hits = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


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
            setImages()
        }

        setImages()

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setImages() {
        val images = getList()
        var counter = 0
        var ind = -1
        var selectedImage: ImageView? = null

        binding.container.children.forEachIndexed { index, view ->

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
                            if (counter == 6) {

                                Snackbar.make(requireView(), "Siz yutdingiz", Snackbar.LENGTH_SHORT)
                                    .show()
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


    private fun getList(): ArrayList<Int> {
        val list = ArrayList<Int>()

        list.add(R.drawable.ic_planet_01)
        list.add(R.drawable.ic_planet_02)
        list.add(R.drawable.ic_planet_03)
        list.add(R.drawable.ic_planet_04)
        list.add(R.drawable.ic_planet_05)
        list.add(R.drawable.ic_planet_06)

        list.add(R.drawable.ic_planet_01)
        list.add(R.drawable.ic_planet_02)
        list.add(R.drawable.ic_planet_03)
        list.add(R.drawable.ic_planet_04)
        list.add(R.drawable.ic_planet_05)
        list.add(R.drawable.ic_planet_06)

        list.shuffle()

        return list
    }

    fun increaseHints() {
        hits++
        binding.scoreTv.setText("Hits : $hits")
    }


}

