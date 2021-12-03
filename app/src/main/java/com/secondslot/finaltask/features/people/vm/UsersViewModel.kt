package com.secondslot.finaltask.features.people.vm

import androidx.lifecycle.ViewModel
import com.secondslot.finaltask.core.Event
import com.secondslot.finaltask.domain.model.User
import com.secondslot.finaltask.domain.usecase.GetAllUsersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class UsersViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _searchQueryStateFlow = MutableStateFlow("")

    val openUserFlow = MutableStateFlow(Event(-1))
    val retryFlow = MutableStateFlow(Event(false))

    suspend fun loadUsers(): Flow<List<User>> {
        return getAllUsersUseCase.execute()
    }

    fun searchUsers(searchQuery: String) {
        _searchQueryStateFlow.value = searchQuery
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun observeSearchChanges(): Flow<List<User>> {
        return _searchQueryStateFlow.asStateFlow()
            .debounce(500)
            .flatMapLatest { searchQuery -> getAllUsersUseCase.execute(searchQuery) }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun onRetryClicked() {
        retryFlow.value = Event(true)
    }

    fun onUserClicked(userId: Int) {
        openUserFlow.value = Event(userId)
    }

    companion object {
        private const val TAG = "UsersViewModel"
    }
}
