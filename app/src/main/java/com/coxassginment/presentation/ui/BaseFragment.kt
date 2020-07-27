package com.coxassginment.presentation.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {

    abstract fun provideViewModelClass(): Class<VM>
    abstract fun layoutId(): Int
    abstract val bindingVariable: Int
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
     lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var viewBinding: VB
    protected lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[provideViewModelClass()]
        onInitLabels()
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(layoutId(), container, false)
        viewBinding = DataBindingUtil.bind(view)!!
        viewBinding.lifecycleOwner = this
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setVariable(bindingVariable, viewModel)
        viewBinding.executePendingBindings()
    }

    /**
     * Method call to initialize the remote config in View Model
     */
    open fun onInitLabels() {
        viewModel.initConfig()
    }




}