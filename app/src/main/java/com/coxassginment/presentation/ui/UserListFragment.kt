package com.coxassginment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.coxassginment.BR
import com.coxassginment.R
import com.coxassginment.data.network.Resource
import com.coxassginment.databinding.FragmentUserListBinding
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserListFragment:  BaseFragment<FragmentUserListBinding,UserListViewModel>() {
    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_user_list
    override fun provideViewModelClass(): Class<UserListViewModel> = UserListViewModel::class.java

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUserListData()
        viewModel.id.observe(viewLifecycleOwner, Observer {
            navigateToUserDetails(it)

        })
viewModel.responseData.observe(viewLifecycleOwner, Observer {
    it?.let {
        viewModel.uiHandler(it)

    }


})
    }

 private fun  navigateToUserDetails(id:Long){
        val arg=Bundle()
        arg.putLong("Id",id)
        val fragment=UserDetailsFragment.newInstance()
        fragment.arguments=arg
        activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
            ?.add(R.id.container, fragment)?.commit()
    }
    override fun onStart() {
        super.onStart()
        val arg=Bundle()
        arg.putString(FirebaseAnalytics.Param.CONTENT, UserListFragment::class.simpleName)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT ,arg)
    }
}