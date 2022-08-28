package uz.gita.memorygame.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.memorygame.R
import uz.gita.memorygame.animation.GameAnimation
import uz.gita.memorygame.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val animator = GameAnimation()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.easyBtn.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("easy")
            )
        }
        binding.mediumBtn.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("medium")
            )
        }
        binding.hardBtn.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPlaygroundFragment("hard")
            )
        }

        binding.leaderboardBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLeaderboardFragment())
        }

        binding.aboutBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment())
        }

    }

}