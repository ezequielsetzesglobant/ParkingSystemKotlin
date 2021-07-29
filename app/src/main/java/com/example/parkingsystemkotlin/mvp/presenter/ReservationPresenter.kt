package com.example.parkingsystemkotlin.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.mvp.contract.ReservationContract
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.example.parkingsystemkotlin.utils.ConstantUtils

class ReservationPresenter(
    private val model: ReservationContract.ReservationModelContract,
    private val view: ReservationContract.ReservationViewContract
) : ReservationContract.ReservationPresenterContract {

    override fun createDate(onDateSetListener: DatePickerDialog.OnDateSetListener, buttonFlag: Boolean) {
        model.setStartDateAndTime(buttonFlag)
        view.showDatePicker(onDateSetListener)
    }

    override fun saveReservationDate(year: Int, month: Int, dayOfMonth: Int, onTimeSetListener: TimePickerDialog.OnTimeSetListener) {
        model.saveDate(year, month, dayOfMonth)
        view.showTimePicker(onTimeSetListener)
    }

    override fun saveReservationTime(hourOfDay: Int, minute: Int) {
        model.saveTime(hourOfDay, minute)
        view.showOkDateAndTime(model.getSavedDateAndTime())
    }

    override fun saveReservationInformation(securityCode: String, place: String) {
        when (model.saveReservation(securityCode, place)) {
            ConstantUtils.INVALID_FIELDS -> view.showError()
            ConstantUtils.SAVE_RESERVATION -> view.finishActivity(model.getReservation(place, securityCode))
            else -> view.showOverlapMessage()
        }
    }
}
