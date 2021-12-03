package com.secondslot.finaltask.features.channels.ui

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondslot.finaltask.App
import com.secondslot.finaltask.R
import com.secondslot.finaltask.databinding.FragmentStreamsListBinding
import com.secondslot.finaltask.features.channels.adapter.StreamsItemDecoration
import com.secondslot.finaltask.features.channels.adapter.StreamsListAdapter
import com.secondslot.finaltask.features.channels.di.DaggerStreamsComponent
import com.secondslot.finaltask.features.channels.model.ExpandableStreamModel
import com.secondslot.finaltask.features.channels.ui.ChannelsState.Error
import com.secondslot.finaltask.features.channels.ui.ChannelsState.Loading
import com.secondslot.finaltask.features.channels.ui.ChannelsState.Result
import com.secondslot.finaltask.features.channels.vm.StreamsListViewModel
import com.secondslot.finaltask.features.chat.ui.ChatFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class StreamsListFragment :
    Fragment(),
    ExpandCollapseListener,
    SearchQueryListener,
    OnTopicClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _viewModel: StreamsListViewModel? = null
    private val viewModel get() = requireNotNull(_viewModel)

    private var _binding: FragmentStreamsListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val streamsListAdapter = StreamsListAdapter(this, this)

    private var streamModelList = mutableListOf<ExpandableStreamModel>()

    private val viewType by lazy { arguments?.getString(CONTENT_KEY, "") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val streamsComponent = DaggerStreamsComponent.factory().create(App.appComponent)
        streamsComponent.injectStreamsListFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStreamsListBinding.inflate(inflater, container, false)

        _viewModel =
            ViewModelProvider(this, viewModelFactory)[StreamsListViewModel::class.java]

        initViews()
        setListeners()
        setObservers()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val typedValue = TypedValue()
        requireActivity().run {
            theme.resolveAttribute(android.R.attr.statusBarColor, typedValue, true)
            window.statusBarColor = typedValue.data
        }
    }

    private fun initViews() {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = streamsListAdapter
            addItemDecoration(StreamsItemDecoration())
            itemAnimator = null
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setListeners() {
        binding.includedRetryButton.retryButton.setOnClickListener {
            viewModel.onRetryClicked(viewType)
        }
    }

    @ExperimentalCoroutinesApi
    private fun setObservers() {
        observeStreams()

        lifecycleScope.launchWhenStarted {
            viewModel.retryFlow.collect { retryEvent ->
                retryEvent.getContentIfNotHandled()?.let {
                    if (it) observeStreams()
                }
            }
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeStreams() {
        lifecycleScope.run {
            launchWhenStarted {
                viewModel.loadStreams(viewType)
                    .catch { processFragmentState(Error(it)) }
                    .collect {
                        if (it.isNullOrEmpty()) {
                            processFragmentState(Loading)
                        } else {
                            processFragmentState(Result(it))
                        }
                    }
            }

            var justLaunched = true
            launchWhenStarted {
                viewModel.observeSearchChanges(viewType)
                    .catch { processFragmentState(Error(it)) }
                    .collect {
                        if (justLaunched) {
                            justLaunched = false
                        } else {
                            processFragmentState(Result(it))
                        }
                    }
            }
        }
    }

    private fun processFragmentState(state: ChannelsState) {
        when (state) {
            is Result -> {
                streamModelList = state.items.toMutableList()

                streamsListAdapter.submitList(streamModelList)
                binding.run {
                    shimmer.isVisible = false
                    includedRetryButton.retryButton.isVisible = false
                    recyclerView.isVisible = true
                }
            }

            Loading -> {
                binding.run {
                    recyclerView.isVisible = false
                    includedRetryButton.retryButton.isVisible = false
                    shimmer.isVisible = true
                }
            }

            is Error -> {
                Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                state.error.message?.let { Log.e(TAG, it) }

                binding.run {
                    shimmer.isVisible = false
                    recyclerView.isVisible = false
                    includedRetryButton.retryButton.isVisible = true
                }
            }
        }
    }

    override fun expandRow(position: Int) {
        val row = streamModelList[position]
        var nextPosition = position

        if (row.type == ExpandableStreamModel.PARENT) {
            for (child in row.stream.topics) {
                streamModelList.add(
                    ++nextPosition,
                    ExpandableStreamModel(ExpandableStreamModel.CHILD, child)
                )
            }
        }

        streamsListAdapter.submitList(streamModelList.toList())
    }

    override fun collapseRow(position: Int) {
        val row = streamModelList[position]
        val nextPosition = position + 1

        if (row.type == ExpandableStreamModel.PARENT) {
            outerloop@ while (true) {
                if (nextPosition == streamModelList.size ||
                    streamModelList[nextPosition].type == ExpandableStreamModel.PARENT
                ) {
                    break@outerloop
                }

                streamModelList.removeAt(nextPosition)
            }

            streamsListAdapter.submitList(streamModelList.toList())
        }
    }

    override fun search(searchQuery: String) {
        viewModel.searchStreams(searchQuery)
    }

    override fun onTopicClicked(topicName: String, maxMessageId: Int, streamId: Int) {
        val extras = bundleOf(
            ChatFragment.TOPIC_NAME to topicName,
            ChatFragment.MAX_MESSAGE_ID to maxMessageId,
            ChatFragment.STREAM_ID to streamId
        )

        findNavController().navigate(R.id.chatFragment, extras)
    }

    companion object {

        private const val TAG = "StreamsListFragment"
        private const val CONTENT_KEY = "list_type"

        fun newInstance(contentKey: String): Fragment {
            return StreamsListFragment().apply {
                arguments = bundleOf(CONTENT_KEY to contentKey)
            }
        }
    }
}

internal sealed class ChannelsState {

    class Result(val items: List<ExpandableStreamModel>) : ChannelsState()

    object Loading : ChannelsState()

    class Error(val error: Throwable) : ChannelsState()
}
