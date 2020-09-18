package com.example.mytestapp.Activity.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mytestapp.Activity.Adapter.OriginalContentFeedPagedListAdapter
import com.example.mytestapp.Activity.ViewModel.OriginalContentFeedViewModel
import com.example.mytestapp.R
import kotlinx.android.synthetic.main.fragment_original_content_feed.view.*
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val viewModel: OriginalContentFeedViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_original_content_feed, container, false)
        setView(view)
        addListenerToView(view)

        return view
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchDataFromServer()

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun setView(view: View) {

    }

    private fun addListenerToView(view: View) {

    }

    //    private fun fetchDataFromServer() {
//
//            val layoutManager = GridLayoutManager(context,2)
//            view?.recyclerView_in_fragment_original_content_feed?.layoutManager = layoutManager;
//            view?.recyclerView_in_fragment_original_content_feed?.setHasFixedSize(true);
//            val itemViewModel = ViewModelProviders.of(this).get(OriginalContentFeedViewModel::class.java)
//            val adapter = OriginalContentFeedPagedListAdapter(context!!) {
//                itemViewModel
//            };
//            itemViewModel.itemPagedList.observe(this, Observer {
//                adapter.submitList(it)
//            })
//
//            //setting the adapter
//            view?.recyclerView_in_fragment_original_content_feed?.adapter = adapter
////        adapter.currentList?.dataSource?.invalidate()
//
//
//
//
//
//    }
    private fun fetchDataFromServer() {

        val layoutManager = GridLayoutManager(context, 2)
        view?.recyclerView_in_fragment_original_content_feed?.layoutManager = layoutManager;
        view?.recyclerView_in_fragment_original_content_feed?.setHasFixedSize(true);
//         viewModel = ViewModelProviders.of(activity!!).get(OriginalContentFeedViewModel::class.java)
//        viewModel = ViewModelProviders.of(
//            activity!!,
//            OriginalContentFeedViewModelFactory(activity?.application!!)
//        ).get(OriginalContentFeedViewModel::class.java)
        val adapter = OriginalContentFeedPagedListAdapter(context!!) {
            viewModel
        };
        viewModel.getBigSummaryModel().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        //setting the adapter
        view?.recyclerView_in_fragment_original_content_feed?.adapter = adapter
//        adapter.currentList?.dataSource?.invalidate()
        observersRegisters()

    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun observersRegisters() {

        val pageListAdapter = OriginalContentFeedPagedListAdapter(context!!) {
            it
        }
        viewModel.getBigSummaryModel()
            .observe(viewLifecycleOwner, Observer { it -> pageListAdapter.submitList(it) })
//        viewModel.getNetworkState()
//            .observe(this, Observer { it->pageListAdapter.setNetworkState(it) })
        view?.recyclerView_in_fragment_original_content_feed?.setAdapter(pageListAdapter)
//        detailsViewModel = ViewModelProviders.of(activity).get(MovieDetailsViewModel::class.java)
    }

}