package uz.ravshanbaxranov.memorygame.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ramotion.circlemenu.CircleMenuView
import com.ramotion.circlemenu.CircleMenuView.EventListener
import kotlinx.coroutines.delay
import uz.ravshanbaxranov.memorygame.R
import uz.ravshanbaxranov.memorygame.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.circleMenu.eventListener = object : EventListener() {
            override fun onButtonClickAnimationEnd(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonClickAnimationEnd(view, buttonIndex)
                when (buttonIndex) {
                    0 -> {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("easy")
                        )
                    }
                    1 -> {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("hard")
                        )
                    }
                    2 -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
                    }
                    3 -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLeaderboardFragment())
                    }
                    4 -> {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("medium")
                        )
                    }
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            delay(500)
            binding.circleMenu.open(true)
        }

    }


}