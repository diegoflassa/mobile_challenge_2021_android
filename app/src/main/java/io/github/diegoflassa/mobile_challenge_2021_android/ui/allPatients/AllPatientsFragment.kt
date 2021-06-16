package io.github.diegoflassa.mobile_challenge_2021_android.ui.allPatients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.diegoflassa.mobile_challenge_2021_android.R
import io.github.diegoflassa.mobile_challenge_2021_android.adapters.AllPatientsAdapter
import io.github.diegoflassa.mobile_challenge_2021_android.databinding.FragmentAllPatientsBinding
import io.github.diegoflassa.mobile_challenge_2021_android.helper.viewLifecycle
import io.github.diegoflassa.mobile_challenge_2021_android.models.AllPatientsFragmentViewModel
import java.lang.ref.WeakReference

/**
 * A simple [Fragment] subclass.
 * Use the [AllPatientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("unused")
class AllPatientsFragment : Fragment() {

    private var binding: FragmentAllPatientsBinding by viewLifecycle()
    private lateinit var viewModel: AllPatientsFragmentViewModel
    private lateinit var adapter: WeakReference<AllPatientsAdapter>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPatientsBinding.inflate(inflater, container, false)
        val swipeContainer =
            binding.root.findViewById(R.id.swipeContainerAllPatients) as SwipeRefreshLayout
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener {
            viewModel.reloadData()
            Log.i(AllPatientsFragment.tag, "AllPatientsFragment.onRefreshListener")
        }
        initRecyclerView()
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        viewModel.patientsLiveData.observe(
            viewLifecycleOwner,
            {
                updateAdapter()
                swipeContainer.isRefreshing = false
                hideLoadingScreen()
                Log.i(
                    AllPatientsFragment.tag,
                    "AllPatientsFragment.patientsLiveData.observe: Data updated!"
                )
            }
        )
        showLoadingScreen()
        Log.i(AllPatientsFragment.tag, "AllPatientsFragment.onCreateView")
        return binding.root
    }

    private fun initRecyclerView() {
        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.card_item_divider
            )!!
        )
        binding.recyclerview.addItemDecoration(itemDecoration)
        updateAdapter()
        Log.i(AllPatientsFragment.tag, "AllPatientsFragment:initRecyclerView")
    }

    private fun showLoadingScreen() {
        binding.allPatientsProgress.visibility = View.VISIBLE
        Log.i(AllPatientsFragment.tag, "AllPatientsFragment.showLoadingScreen")
    }

    private fun hideLoadingScreen() {
        binding.allPatientsProgress.visibility = View.GONE
        Log.i(AllPatientsFragment.tag, "AllPatientsFragment.hideLoadingScreen")
    }

    private fun updateAdapter() {
        adapter = WeakReference(AllPatientsAdapter(viewModel.patients))
        adapter.get()!!.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.recyclerview.adapter = adapter.get()
        Log.i(AllPatientsFragment.tag, "AllPatientsFragment.updateAdapter")
    }

    companion object {
        private val tag = AllPatientsFragment::class.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AllPatientsFragment.
         */
        @JvmStatic
        fun newInstance() =
            AllPatientsFragment()
    }
}
