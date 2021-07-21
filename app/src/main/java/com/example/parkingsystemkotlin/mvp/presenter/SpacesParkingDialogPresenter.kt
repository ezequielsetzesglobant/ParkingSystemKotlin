package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.SpacesParkingDialogContract

class SpacesParkingDialogPresenter(
    private val model: SpacesParkingDialogContract.DialogModel,
    private val view: SpacesParkingDialogContract.DialogView
) : SpacesParkingDialogContract.DialogPresenter {

    override fun notifyActivity(listenerDialogFragment: ListenerDialogFragment, spaces: String) {
        model.checkSpacesInput(spaces)
        view.closeDialog(listenerDialogFragment, model.getSpaces())
    }
}
