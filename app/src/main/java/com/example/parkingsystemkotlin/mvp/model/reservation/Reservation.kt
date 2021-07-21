package com.example.parkingsystemkotlin.mvp.model.reservation

import java.text.SimpleDateFormat
import java.util.Calendar

class Reservation(
    var startDateAndTime: Calendar,
    var finishDateAndTime: Calendar,
    var securityCode: String,
    var place: String
) {

    fun getStartDateAndTimeFormated(): String = simpleDateFormat.format(startDateAndTime.time)

    fun getFinishDateAndTimeFormated(): String = simpleDateFormat.format(finishDateAndTime.time)

    fun isNotEmpty(): Boolean = securityCode.isNotEmpty()

    companion object {
        private const val FORMAT_DATE: String = "dd/MM/yyyy hh:mm a"

        private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(FORMAT_DATE)
    }
}
