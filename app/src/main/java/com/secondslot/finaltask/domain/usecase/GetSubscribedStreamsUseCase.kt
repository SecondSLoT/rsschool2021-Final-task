package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.domain.model.Stream
import com.secondslot.finaltask.domain.repository.StreamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSubscribedStreamsUseCase @Inject constructor(
    private val streamsRepository: StreamsRepository
) {

    fun execute(searchQuery: String = ""): Flow<List<Stream>> {
        return if (searchQuery.isEmpty()) {
            streamsRepository.getSubscribedStreams()
        } else {
            streamsRepository.getSubscribedStreams().map { channels ->
                channels.filter { it.streamName.contains(searchQuery, ignoreCase = true) }
            }
        }
    }
}
