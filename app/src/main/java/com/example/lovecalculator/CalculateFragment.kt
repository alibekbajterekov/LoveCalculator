package com.example.lovecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lovecalculator.databinding.FragmentCalculateBinding
import com.example.lovecalculator.room.local.LoveDao
import com.example.lovecalculator.viewmodel.LoveViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculateFragment : Fragment() {

    @Inject
    lateinit var loveDao: LoveDao

    private lateinit var binding: FragmentCalculateBinding
    private val viewModel: LoveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }
    private fun initClickers() {
        with(binding) {
            historyBtn.setOnClickListener {
                findNavController().navigate(R.id.historyFragment)
            }
            calculateBtn.setOnClickListener {
                viewModel.getLiveLove(firstNameEd.text.toString(), secondNameEd.text.toString())
                    .observe(
                        viewLifecycleOwner, Observer {
                            loveDao.insert(listOf(it))
                            findNavController().navigate(
                                R.id.resultFragment, bundleOf(
                                    LOVE_KEY to (it?.percentage
                                            )
                                )
                            )
                        }
                    )
            }
        }
    }

    companion object {
        const val LOVE_KEY = "KEY"
    }

}