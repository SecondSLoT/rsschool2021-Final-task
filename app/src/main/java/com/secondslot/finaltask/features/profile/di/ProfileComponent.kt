package com.secondslot.finaltask.features.profile.di

import com.secondslot.finaltask.di.AppComponent
import com.secondslot.finaltask.features.profile.ui.ProfileFragment
import dagger.Component

@ProfileScope
@Component(
    modules = [ProfileModule::class],
    dependencies = [AppComponent::class]
)
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): ProfileComponent
    }
}
