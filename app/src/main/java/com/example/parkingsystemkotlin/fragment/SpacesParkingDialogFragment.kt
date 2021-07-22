package com.example.parkingsystemkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.parkingsystemkotlin.databinding.FragmentSpacesParkingDialogBinding
import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.SpacesParkingDialogContract
import com.example.parkingsystemkotlin.mvp.model.SpacesParkingDialogModel
import com.example.parkingsystemkotlin.mvp.presenter.SpacesParkingDialogPresenter
import com.example.parkingsystemkotlin.mvp.view.SpacesParkingDialogView

class SpacesParkingDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSpacesParkingDialogBinding
    private lateinit var presenter: SpacesParkingDialogContract.DialogPresenter
    private lateinit var listenerDialogFragment: ListenerDialogFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSpacesParkingDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SpacesParkingDialogPresenter(SpacesParkingDialogModel(), SpacesParkingDialogView(this, binding))
        listenerDialogFragment = arguments?.getSerializable(LISTENER_KEY) as ListenerDialogFragment
        binding.buttonDialogFragmentClose.setOnClickListener { presenter.notifyActivity(listenerDialogFragment, binding.editTextDialogFragmentAmountSpaces.text.toString()) }
    }

    companion object {
        private const val LISTENER_KEY = "LISTENER_KEY"

        fun newInstance(listenerDialogFragment: ListenerDialogFragment): SpacesParkingDialogFragment {
            val dialogFragment = SpacesParkingDialogFragment()
            val bundle = Bundle()
            bundle.putSerializable(LISTENER_KEY, listenerDialogFragment)
            dialogFragment.arguments = bundle
            return dialogFragment
        }
    }
}
