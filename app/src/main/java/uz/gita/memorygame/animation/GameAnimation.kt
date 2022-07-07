package uz.gita.memorygame.animation

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.memorygame.R

class GameAnimation {

    fun animateFlipOut(imageView: View, image: Int) {
        imageView as ImageView
        GlobalScope.launch(Dispatchers.Main) {
            imageView.rotationY = 0f
            ValueAnimator.ofFloat(0f, 180f).apply {
                addUpdateListener {
                    imageView.rotationY = animatedValue as Float
                }
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator()
                start()
            }
            delay(500L)

            imageView.setImageResource(image)

        }
    }

    fun animateFlipIn(imageView: View) {
        imageView as ImageView
        GlobalScope.launch(Dispatchers.Main) {
            ValueAnimator.ofFloat(0f, 180f).apply {
                addUpdateListener {
                    imageView.rotationY = animatedValue as Float
                }
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator()
                start()
            }
            delay(500L)
            imageView.setImageResource(R.drawable.brain)

        }
    }

    fun animateZoomIn(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            ValueAnimator.ofFloat(view.scaleX, 0f).apply {
                addUpdateListener {
                    view.alpha = animatedValue as Float
                    view.scaleX = animatedValue as Float
                    view.scaleY = animatedValue as Float
                }
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator()
                start()
            }
        }
    }

}