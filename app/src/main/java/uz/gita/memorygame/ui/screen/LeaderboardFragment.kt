package uz.gita.memorygame.ui.screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.memorygame.R
import uz.gita.memorygame.databinding.FragmentLeaderboardsBinding
import uz.gita.memorygame.ui.LeaderboardAdapter
import uz.gita.memorygame.ui.viewmodel.LeaderboardViewModel

@AndroidEntryPoint
class LeaderboardFragment : Fragment(R.layout.fragment_leaderboards) {

    private val binding by viewBinding(FragmentLeaderboardsBinding::bind)
    private val viewModel: LeaderboardViewModel by viewModels()
    private val adapter by lazy { LeaderboardAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.leaderboardRv.adapter = adapter

        lifecycleScope.launch {
            viewModel.leaderBoardFlow.collect() {
                adapter.submitList(it)
                binding.noLeaderboardTv.isVisible = it.isEmpty()

            }
        }

        viewModel.getLeaderboardByCategory("easy")


        binding.easyBtn.setOnClickListener {
            viewModel.getLeaderboardByCategory("easy")
            binding.titleTv.text = "Easy"
        }
        binding.mediumBtn.setOnClickListener {
            viewModel.getLeaderboardByCategory("medium")
            binding.titleTv.text = "Medium"
        }
        binding.hardBtn.setOnClickListener {
            viewModel.getLeaderboardByCategory("hard")
            binding.titleTv.text = "Hard"
        }
    }

}