package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract

class ParkingPresenter(private val model: ParkingContract.Model, private val view: ParkingContract.View) : ParkingContract.Presenter {

    override fun onSetParkingPlacesButtonPressed(spaces: Int) {
        model.setSpaces(spaces)
        view.showPopUp(model.getSpaces())
    }

    override fun inflateDialog(listenerDialogFragment: ListenerDialogFragment) {
        view.showDialog(listenerDialogFragment)
    }

    override fun onReservationButtonClicked() {
        view.openReservationScreen()
    }
}
