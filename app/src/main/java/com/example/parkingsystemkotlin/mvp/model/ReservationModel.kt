package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.ReservationContract
import com.example.parkingsystemkotlin.mvp.model.reservation.DataBase
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import java.util.Calendar

class ReservationModel(private val reservationInformationDB: DataBase) : ReservationContract.ReservationModelContract {

    private var isStartDateAndTime: Boolean = true
    private lateinit var startDateAndTime: Calendar
    private lateinit var finishDateAndTime: Calendar

    override fun saveDate(year: Int, month: Int, dayOfMonth: Int) {
        if (isStartDateAndTime) {
            startDateAndTime = Calendar.getInstance()
            startDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            startDateAndTime.set(Calendar.MONTH, month)
            startDateAndTime.set(Calendar.YEAR, year)
        } else {
            finishDateAndTime = Calendar.getInstance()
            finishDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            finishDateAndTime.set(Calendar.MONTH, month)
            finishDateAndTime.set(Calendar.YEAR, year)
        }
    }

    override fun saveTime(hourOfDay: Int, minute: Int) {
        if (isStartDateAndTime) {
            startDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            startDateAndTime.set(Calendar.MINUTE, minute)
        } else {
            finishDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            finishDateAndTime.set(Calendar.MINUTE, minute)
        }
    }

    override fun saveReservation(securityCode: String, place: String) {
        if (::startDateAndTime.isInitialized && ::startDateAndTime.isInitialized && securityCode.isNotEmpty() && place.isNotEmpty()) {
            reservationInformationDB.putReservationDB(startDateAndTime, finishDateAndTime, securityCode, place)
        }
    }

    override fun getReservation(place: String, securityCode: String): Reservation =
        reservationInformationDB.getReservationDB(place, securityCode)

    override fun setStartDateAndTime(startDateAndTime: Boolean) {
        this.isStartDateAndTime = startDateAndTime
    }
}
