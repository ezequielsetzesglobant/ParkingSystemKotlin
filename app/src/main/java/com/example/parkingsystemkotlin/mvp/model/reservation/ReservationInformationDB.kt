package com.example.parkingsystemkotlin.mvp.model.reservation

import java.util.Calendar

object ReservationInformationDB : DataBase {

    private const val YEAR_START_AND_FINISH = 1970
    private const val MONTH_START_AND_FINISH = 1
    private const val DAY_OF_MONTH_START_AND_FINISH = 1
    private const val HOUR_OF_DAY_START_AND_FINISH = 0
    private const val MINUTE_START_AND_FINISH = 0
    private const val SECURITY_CODE = ""
    private const val PLACE = ""

    private var reservationInfomation: MutableMap<String, MutableList<Reservation>> = mutableMapOf()

    override fun getReservationsDB(place: String): MutableList<Reservation> = reservationInfomation[place] ?: mutableListOf()

    override fun putReservationDB(startDateAndTime: Calendar, finishDateAndTime: Calendar, securityCode: String, place: String) {
        val reservation = Reservation(startDateAndTime, finishDateAndTime, securityCode, place)
        if (reservationInfomation.containsKey(place)) {
            reservationInfomation[place]?.add(reservation)
        } else {
            val reservationsList: MutableList<Reservation> = mutableListOf()
            reservationsList.add(reservation)
            reservationInfomation[reservation.place] = reservationsList
        }
    }

    override fun getReservationDB(place: String, securityCode: String): Reservation {
        reservationInfomation[place]?.forEach {
            if (it.securityCode.equals(securityCode)) {
                return it
            }
        }
        return Reservation(
            getCalendarDate(
                YEAR_START_AND_FINISH,
                MONTH_START_AND_FINISH,
                DAY_OF_MONTH_START_AND_FINISH,
                HOUR_OF_DAY_START_AND_FINISH,
                MINUTE_START_AND_FINISH
            ),
            getCalendarDate(
                YEAR_START_AND_FINISH,
                MONTH_START_AND_FINISH,
                DAY_OF_MONTH_START_AND_FINISH,
                HOUR_OF_DAY_START_AND_FINISH,
                MINUTE_START_AND_FINISH
            ),
            SECURITY_CODE,
            PLACE
        )
    }

    private fun getCalendarDate(year: Int, month: Int, dayOfMonth: Int, hourOfDay: Int, minute: Int): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        return calendar
    }
}
