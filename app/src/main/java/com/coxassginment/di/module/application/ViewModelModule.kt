package com.coxassginment.di.module.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coxassginment.di.module.application.ViewModelFactory
import com.coxassginment.di.qualifier.ViewModelKey
import com.coxassginment.presentation.ui.UserDetailsViewModel
import com.coxassginment.presentation.ui.UserListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /*
    * This method basically says
    * inject this object into a Map using the @IntoMap annotation,
    * with the  AuthViewModel.class as key,
    * and a Provider that will build a AuthViewModel
    * object.
    *
    * */

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    internal abstract fun bindUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel
  @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal abstract fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel




}