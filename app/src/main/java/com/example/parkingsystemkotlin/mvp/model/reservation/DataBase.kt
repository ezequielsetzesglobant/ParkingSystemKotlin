package com.example.parkingsystemkotlin.mvp.model.reservation

import java.util.Calendar

interface DataBase {

    fun getReservationsDB(place: String): MutableList<Reservation>
    fun putReservationDB(startDateAndTime: Calendar, finishDateAndTime: Calendar, securityCode: String, place: String)
    fun getReservationDB(place: String, securityCode: String): Reservation
    fun releasePastReservations(): Int
    fun isOverlap(startDateAndTime: Calendar, finishDateAndTime: Calendar, place: String): Boolean
    fun getAllReservations(): MutableList<Reservation>
}
