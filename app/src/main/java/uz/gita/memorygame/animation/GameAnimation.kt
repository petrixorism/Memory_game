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

    fun animateFlipIn(imageView: View, time: Long) {

        imageView as ImageView
        GlobalScope.launch(Dispatchers.Main) {
            delay(time)
            ValueAnimator.ofFloat(180f, 360f).apply {
                addUpdateListener {
                    imageView.rotationY = animatedValue as Float
                }
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator()
                start()
            }
            delay(500L)
            imageView.setImageResource(R.drawable.ic_question_mark)

        }
    }


    fun animateZoomIn(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
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