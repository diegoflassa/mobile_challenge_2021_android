package io.github.diegoflassa.mobile_challenge_2021_android.ui.searchBar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.AndroidEntryPoint
import io.github.diegoflassa.mobile_challenge_2021_android.R
import io.github.diegoflassa.mobile_challenge_2021_android.databinding.FragmentSearchBarBinding
import io.github.diegoflassa.mobile_challenge_2021_android.enums.Gender
import io.github.diegoflassa.mobile_challenge_2021_android.helper.viewLifecycle
import io.github.diegoflassa.mobile_challenge_2021_android.models.SearchBarViewModel
import io.github.diegoflassa.mobile_challenge_2021_android.interfaces.OnSearch


@AndroidEntryPoint
class SearchBarFragment : Fragment() {

    companion object {
        const val key_on_search = "key_on_search"
    }

    private lateinit var viewModel: SearchBarViewModel
    private var binding: FragmentSearchBarBinding by viewLifecycle()
    private var onSearch: OnSearch? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SearchBarViewModel::class.java)
        onSearch = requireArguments().getParcelable(key_on_search)
        binding = FragmentSearchBarBinding.inflate(inflater, container, false)
        binding.edttxtSearch.setCompoundDrawables(
            null,
            null,
            ResourcesCompat.getDrawable(resources, R.drawable.ic_user_tag, null),
            null
        )
        viewModel.queryLiveData.observe(viewLifecycleOwner) {
            updateUI(viewModel)
        }
        var gender = ""
        if (viewModel.gender != Gender.UNKNOWN) {
            gender = viewModel.gender.toString()
        }
        binding.btnGender.text = getString(R.string.btn_gender, gender)
        binding.btnGender.setOnClickListener {
            onSearch?.onSearch(
                viewModel.query,
                viewModel.nationality,
                viewModel.gender
            )
        }
        binding.btnNationality.text = getString(R.string.btn_nationality, viewModel.nationality)
        binding.btnNationality.setOnClickListener {
            onSearch?.onSearch(
                viewModel.query,
                viewModel.nationality,
                viewModel.gender
            )
        }
        binding.btnFilter.setOnClickListener {
            onSearch?.onSearch(
                viewModel.query,
                viewModel.nationality,
                viewModel.gender
            )
        }
        binding.btnReset.setOnClickListener {
            onSearch?.clear()
        }
        return binding.root
    }

    private fun updateUI(viewModel: SearchBarViewModel) {
        //Do Nothing
    }

}
