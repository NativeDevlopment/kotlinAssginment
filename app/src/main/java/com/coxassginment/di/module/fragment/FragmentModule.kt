package com.coxassginment.di.module.fragment


import com.coxassginment.presentation.ui.UserDetailsFragment
import com.coxassginment.presentation.ui.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*
     * We define the name of the Fragment we are going
     * to inject the ViewModelFactory into. i.e. in our case
     * The name of the fragment: LoginFragment
     */
    @ContributesAndroidInjector
    abstract fun contributeIntroFragment(): UserListFragment
    @ContributesAndroidInjector
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment

}