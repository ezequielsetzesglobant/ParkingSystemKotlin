package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.ActivityParkingBinding
import com.example.parkingsystemkotlin.fragment.SpacesParkingDialogFragment
import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView
import com.google.android.material.snackbar.Snackbar

class ParkingView(activity: Activity, private val binding: ActivityParkingBinding) : ActivityView(activity), ParkingContract.View {

    override fun showPopUp(spaces: Int) {
        getContext()?.let {
            Snackbar.make(
                binding.root,
                it.resources.getString(R.string.snack_bar_button_click_message_main_activity, spaces.toString()),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun showDialog(listenerDialogFragment: ListenerDialogFragment) {
        getFragmentManager()?.let {
            val dialogFragment = SpacesParkingDialogFragment.newInstance(listenerDialogFragment)
            dialogFragment.show(it, TAG)
        }
    }

    companion object {
        private const val TAG: String = "SPACES_PARKING_FRAGMENT"
    }
}
