package uz.ravshanbaxranov.memorygame.animation

import android.animation.ValueAnimator
import android.view.View
import android.view.View.VISIBLE
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.ravshanbaxranov.memorygame.R

class GameAnimation(val lifecycleCoroutineScope: LifecycleCoroutineScope) {

    fun animateFlipOut(imageView: View, image: Int) {
        imageView as ImageView
        lifecycleCoroutineScope.launch(Dispatchers.Main) {
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
        lifecycleCoroutineScope.launch(Dispatchers.Main) {
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
        lifecycleCoroutineScope.launch(Dispatchers.Main) {
            delay(1000L)
            ValueAnimator.ofFloat(1f, 0f).apply {
                addUpdateListener {
                    view.scaleX = animatedValue as Float
                    view.scaleY = animatedValue as Float
                    view.alpha = animatedValue as Float
                }
                duration = 1000L
                interpolator = AnticipateOvershootInterpolator()
                start()
            }

        }
    }

    fun animateZoomOut(view: View) {

        ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                view.alpha = animatedValue as Float
                view.scaleX = animatedValue as Float
                view.scaleY = animatedValue as Float
                view.visibility = VISIBLE
            }
            duration = 500L
            interpolator = AnticipateOvershootInterpolator()
            start()
        }


    }

    fun swipeExpand(view: TextView) {
        ValueAnimator.ofFloat(.3f, 1f).apply {
            addUpdateListener {
                view.x = animatedValue as Float
            }
            duration = 500L
            interpolator = AnticipateOvershootInterpolator()
            start()
        }
    }

}