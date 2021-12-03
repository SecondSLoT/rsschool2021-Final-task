package com.secondslot.finaltask.features.people.di

import com.secondslot.finaltask.di.AppComponent
import com.secondslot.finaltask.features.people.ui.UsersFragment
import dagger.Component

@UsersScope
@Component(
    modules = [UsersModule::class],
    dependencies = [AppComponent::class]
)
interface UsersComponent {

    fun inject(usersFragment: UsersFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): UsersComponent
    }
}
