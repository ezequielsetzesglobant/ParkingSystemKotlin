package com.example.parkingsystemkotlin.mvp.contract

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment

interface ParkingContract {

    interface Model {
        fun setSpaces(spaces: Int)
        fun getSpaces(): Int
    }

    interface Presenter {
        fun onSetParkingPlacesButtonPressed(spaces: Int)
        fun inflateDialog(listenerDialogFragment: ListenerDialogFragment)
        fun onReservationButtonClicked()
    }

    interface View {
        fun showPopUp(spaces: Int)
        fun showDialog(listenerDialogFragment: ListenerDialogFragment)
        fun openReservationScreen()
    }
}
