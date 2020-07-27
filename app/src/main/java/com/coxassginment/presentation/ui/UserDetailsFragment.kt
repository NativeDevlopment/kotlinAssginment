package com.coxassginment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.coxassginment.R
import com.coxassginment.BR
import com.coxassginment.databinding.FragmentUserDetailsBinding
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding,UserDetailsViewModel>() {
    companion object {
        fun newInstance() = UserDetailsFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_user_details
    override fun provideViewModelClass(): Class<UserDetailsViewModel> =UserDetailsViewModel::class.java

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserData(arguments?.getLong("Id"))
        viewModel.users.observe(viewLifecycleOwner, Observer {
            val arg=Bundle()
            arg.putString(FirebaseAnalytics.Param.ITEM_NAME, it.login)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT ,arg)
        })
    }

    override fun onStart() {
        super.onStart()

    }
}