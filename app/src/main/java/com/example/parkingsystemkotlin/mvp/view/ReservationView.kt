package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.ActivityReservationBinding
import com.example.parkingsystemkotlin.mvp.contract.ReservationContract
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView
import com.example.parkingsystemkotlin.utils.snackbar
import com.example.parkingsystemkotlin.utils.toast
import java.util.Calendar
import java.util.Locale

class ReservationView(activity: Activity, private val binding: ActivityReservationBinding) : ActivityView(activity),
    ReservationContract.ReservationViewContract {

    override fun showDatePicker(onDateSetListener: DatePickerDialog.OnDateSetListener) {
        val calendar = Calendar.getInstance(Locale.getDefault())
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)
        context?.let {
            val datePickerDialog = DatePickerDialog(it, onDateSetListener, year, month, dayOfMonth)
            datePickerDialog.show()
            datePickerDialog.setCancelable(false)
        }
    }

    override fun showTimePicker(onTimeSetListener: TimePickerDialog.OnTimeSetListener) {
        val calendar = Calendar.getInstance()
        val hourOfDay: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        context?.let {
            val timePickerDialog = TimePickerDialog(it, onTimeSetListener, hourOfDay, minute, false)
            timePickerDialog.show()
            timePickerDialog.setCancelable(false)
        }
    }

    override fun finishActivity(reservation: Reservation) {
        activity?.let {
            it.finish()
            it.toast(
                it.getString(
                    R.string.toast_save_reservation_information_message_reservation_activity,
                    reservation.getStartDateAndTimeFormated(),
                    reservation.getFinishDateAndTimeFormated(),
                    reservation.securityCode,
                    reservation.place
                )
            )
        }
    }

    override fun showError() {
        context?.let {
            it.snackbar(binding.root, it.getString(R.string.snack_bar_save_reservation_error_message_reservation_activity))
        }
    }

    override fun showOkDateAndTime() {
        activity?.let {
            it.toast(it.getString(R.string.toast_date_and_time_ok_message_reservation_activity))
        }
    }
}
