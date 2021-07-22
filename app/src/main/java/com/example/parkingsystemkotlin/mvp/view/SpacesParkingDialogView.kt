package com.example.parkingsystemkotlin.mvp.view

import com.example.parkingsystemkotlin.databinding.FragmentSpacesParkingDialogBinding
import com.example.parkingsystemkotlin.fragment.SpacesParkingDialogFragment
import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.SpacesParkingDialogContract
import com.example.parkingsystemkotlin.mvp.view.base.FragmentView

class SpacesParkingDialogView(fragment: SpacesParkingDialogFragment, private val binding: FragmentSpacesParkingDialogBinding) :
    FragmentView(fragment), SpacesParkingDialogContract.DialogView {

    override fun closeDialog(listenerDialogFragment: ListenerDialogFragment, spaces: Int) {
        (fragment as SpacesParkingDialogFragment?)?.dismiss()
        listenerDialogFragment.setAmountSpaces(spaces)
    }
}
