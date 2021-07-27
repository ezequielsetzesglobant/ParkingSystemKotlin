package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.activity.ReservationActivity
import com.example.parkingsystemkotlin.activity.ReservationsRecyclerActivity
import com.example.parkingsystemkotlin.databinding.ActivityParkingBinding
import com.example.parkingsystemkotlin.fragment.SpacesParkingDialogFragment
import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView
import com.example.parkingsystemkotlin.utils.snackbar

class ParkingView(activity: Activity, private val binding: ActivityParkingBinding) : ActivityView(activity), ParkingContract.View {

    override fun showPopUp(spaces: Int) {
        context?.let {
            it.snackbar(binding.root, it.getString(R.string.snack_bar_button_click_message_main_activity, spaces.toString()))
        }
    }

    override fun showDialog(listenerDialogFragment: ListenerDialogFragment) {
        fragmentManager?.let {
            val dialogFragment = SpacesParkingDialogFragment.newInstance(listenerDialogFragment)
            dialogFragment.show(it, TAG)
            dialogFragment.isCancelable = false
        }
    }

    override fun openReservationScreen() {
        activity?.startActivity(context?.let { ReservationActivity.getIntent(it) })
    }

    override fun showAmountOfReservationsReleased(releasedReservations: Int) {
        context?.let {
            it.snackbar(
                binding.root,
                it.getString(R.string.snack_bar_released_reservations_message_main_activity, releasedReservations.toString())
            )
        }
    }

    override fun openAllReservationsScreen() {
        activity?.startActivity(context?.let { ReservationsRecyclerActivity.getIntent(it) })
    }

    companion object {
        private const val TAG: String = "SPACES_PARKING_FRAGMENT"
    }
}
