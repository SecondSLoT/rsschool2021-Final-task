package com.secondslot.finaltask.features.channels.vm

import androidx.lifecycle.ViewModel
import com.secondslot.finaltask.core.Event
import com.secondslot.finaltask.features.channels.core.StreamsListType
import com.secondslot.finaltask.features.channels.ui.SearchQueryListener
import com.secondslot.finaltask.features.channels.ui.StreamsListFragment
import kotlinx.coroutines.flow.MutableStateFlow

class StreamsViewModel : ViewModel() {

    val clearSearchFlow = MutableStateFlow(Event(false))

    var streamsFragmentsList = listOf(
        StreamsListFragment.newInstance(StreamsListType.SUBSCRIBED),
        StreamsListFragment.newInstance(StreamsListType.ALL_STREAMS)
    )
        private set

    fun onPageChanged() {
        clearSearchFlow.value = Event(true)
    }

    fun searchStreams(currentPosition: Int, searchQuery: String) {
        (streamsFragmentsList[currentPosition] as SearchQueryListener).search(searchQuery)
    }

    companion object {
        private const val TAG = "StreamsViewModel"
    }
}
