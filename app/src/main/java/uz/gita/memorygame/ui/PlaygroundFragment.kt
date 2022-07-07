package uz.gita.memorygame.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.memorygame.R
import uz.gita.memorygame.animation.GameAnimation
import uz.gita.memorygame.databinding.FragmentPlaygroundBinding

class PlaygroundFragment : Fragment(R.layout.fragment_playground) {

    private val binding by viewBinding(FragmentPlaygroundBinding::bind)
    private val animator by lazy { GameAnimation() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var isFlipped = true
        binding.pic1.setOnClickListener {
            if (isFlipped) {
                isFlipped = !isFlipped
                animator.animateFlipOut(it, R.drawable.ufo)
            } else {
                isFlipped = !isFlipped
                animator.animateFlipIn(it)
            }
        }

        binding.pic2.setOnClickListener {
            animator.animateZoomIn(it)
        }
        binding.pic4.setOnClickListener {
            animator.animateZoomIn(it)
        }
        binding.pic3.setOnClickListener {
            animator.animateZoomIn(it)
        }

    }

}