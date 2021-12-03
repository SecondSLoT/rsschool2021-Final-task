package com.secondslot.finaltask.domain.usecase

import com.secondslot.finaltask.domain.model.Stream
import com.secondslot.finaltask.domain.repository.StreamsRepository
import javax.inject.Inject

class GetStreamByIdUseCase @Inject constructor(
    private val streamsRepository: StreamsRepository

) {

    suspend fun execute(streamId: Int): Stream {
        return streamsRepository.getStreamById(streamId)
    }
}
