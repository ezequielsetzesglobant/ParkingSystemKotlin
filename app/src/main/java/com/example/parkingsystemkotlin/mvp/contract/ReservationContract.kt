package com.example.parkingsystemkotlin.mvp.contract

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation

interface ReservationContract {

    interface ReservationModelContract {
        fun saveDate(year: Int, month: Int, dayOfMonth: Int)
        fun saveTime(hourOfDay: Int, minute: Int)
        fun saveReservation(securityCode: String, place: String)
        fun getReservation(place: String, securityCode: String): Reservation
        fun setStartDateAndTime(startDateAndTime: Boolean)
    }

    interface ReservationPresenterContract {
        fun createDate(onDateSetListener: DatePickerDialog.OnDateSetListener, buttonFlag: Boolean)
        fun saveReservationDate(year: Int, month: Int, dayOfMonth: Int, onTimeSetListener: TimePickerDialog.OnTimeSetListener)
        fun saveReservationTime(hourOfDay: Int, minute: Int)
        fun saveReservationInformation(securityCode: String, place: String)
    }

    interface ReservationViewContract {
        fun showDatePicker(onDateSetListener: DatePickerDialog.OnDateSetListener)
        fun showTimePicker(onTimeSetListener: TimePickerDialog.OnTimeSetListener)
        fun finishActivity(reservation: Reservation)
        fun showError()
        fun showOkDateAndTime()
    }
}
