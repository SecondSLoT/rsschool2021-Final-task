package com.secondslot.finaltask.features.chat.di

import com.secondslot.finaltask.di.AppComponent
import com.secondslot.finaltask.features.chat.ui.ChatFragment
import dagger.Component

@ChatScope
@Component(
    modules = [ChatModule::class],
    dependencies = [AppComponent::class]
)
interface ChatComponent {

    fun inject(chatFragment: ChatFragment)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent
        ): ChatComponent
    }
}
