package com.example.parkingsystemkotlin.mvp.contract

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment

interface SpacesParkingDialogContract {

    interface DialogModel {
        fun checkSpacesInput(spaces: String)
        fun getSpaces(): Int
    }

    interface DialogPresenter {
        fun notifyActivity(listenerDialogFragment: ListenerDialogFragment, spaces: String)
    }

    interface DialogView {
        fun closeDialog(listenerDialogFragment: ListenerDialogFragment, spaces: Int)
    }
}
