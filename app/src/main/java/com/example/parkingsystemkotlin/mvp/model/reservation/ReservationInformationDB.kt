package com.example.parkingsystemkotlin.mvp.model.reservation

import java.util.Calendar
import java.util.Locale

object ReservationInformationDB : DataBase {

    private const val YEAR_START_AND_FINISH = 1970
    private const val MONTH_START_AND_FINISH = 1
    private const val DAY_OF_MONTH_START_AND_FINISH = 1
    private const val HOUR_OF_DAY_START_AND_FINISH = 0
    private const val MINUTE_START_AND_FINISH = 0
    private const val SECURITY_CODE = ""
    private const val PLACE = ""
    private const val ZERO_INT = 0

    private var reservations: MutableMap<String, MutableList<Reservation>> = mutableMapOf()

    override fun getReservationsDB(place: String): MutableList<Reservation> = reservations[place] ?: mutableListOf()

    override fun putReservationDB(startDateAndTime: Calendar, finishDateAndTime: Calendar, securityCode: String, place: String) {
        val reservation = Reservation(startDateAndTime, finishDateAndTime, securityCode, place)
        if (reservations.containsKey(place)) {
            reservations[place]?.add(reservation)
        } else {
            val reservationsList: MutableList<Reservation> = mutableListOf()
            reservationsList.add(reservation)
            reservations[reservation.place] = reservationsList
        }
    }

    override fun getReservationDB(place: String, securityCode: String): Reservation {
        reservations[place]?.forEach {
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

    override fun releasePastReservations(): Int {
        var releasedReservations: Int = ZERO_INT
        if (reservations.isNotEmpty()) {
            reservations.forEach { reservation ->
                releasedReservations += deleteReservations(reservation.key)
            }
        }
        return releasedReservations
    }

    override fun isOverlap(startDateAndTime: Calendar, finishDateAndTime: Calendar, place: String) =
        reservations[place]?.filter { (startDateAndTime.after(it.finishDateAndTime) || finishDateAndTime.before(it.startDateAndTime)).not() }?.size ?: 0 > 0

    override fun getAllReservations(): MutableList<Reservation> {
        val reservationsList: MutableList<Reservation> = mutableListOf()
        reservations.forEach { reservation -> reservationsList.addAll(reservation.value) }
        return reservationsList
    }

    private fun deleteReservations(key: String): Int {
        val calendar = Calendar.getInstance(Locale.getDefault())
        val list = reservations[key]
        reservations[key] = list?.filter { it.finishDateAndTime.after(calendar) } as MutableList<Reservation>
        return list.filter { it.finishDateAndTime.before(calendar) }.size
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
