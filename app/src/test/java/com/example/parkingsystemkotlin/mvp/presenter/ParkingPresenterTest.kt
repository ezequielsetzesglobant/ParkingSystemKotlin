package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.ParkingModel
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.example.parkingsystemkotlin.mvp.model.reservation.ReservationInformationDB
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import java.util.Calendar
import org.junit.Assert.assertEquals
import org.junit.Test

class ParkingPresenterTest {

    private val listener: ListenerDialogFragment = mock()
    private val view: ParkingContract.View = mock()
    private val model: ParkingContract.Model = ParkingModel()
    private val presenter: ParkingContract.Presenter = ParkingPresenter(model, view)
    var reservation: Reservation = Reservation(
        getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_START, MINUTE_START),
        getCalendarDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, HOUR_FINISH, MINUTE_FINISH),
        SECURITY_CODE,
        PLACE
    )

    @Test
    fun `on set parking places button pressed test`() {
        //Execute
        presenter.onSetParkingPlacesButtonPressed(FIVE_INT)
        //Assert
        verify(view).showPopUp(model.getSpaces())
        assertEquals(FIVE_INT, model.getSpaces())
    }

    @Test
    fun `inflate dialog test`() {
        //Execute
        presenter.inflateDialog(listener)
        //Assert
        verify(view).showDialog(listener)
    }

    @Test
    fun `on reservation button clicked test`() {
        //Setup
        makeReservation()
        //Execute
        presenter.onReservationButtonClicked()
        //Assert
        verify(view).openReservationScreen()
        assertEquals(ZERO_INT, model.releaseParking())
    }

    @Test
    fun onReleaseParkingButtonClickedTest() {
        //Setup
        makeReservation()
        //Execute
        presenter.onReleaseParkingButtonClicked()
        //Assert
        verify(view).showAmountOfReservationsReleased(ONE_INT)
        assertEquals(ZERO_INT, model.releaseParking())
    }

    private fun makeReservation() {
        ReservationInformationDB.putReservationDB(
            getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_START, MINUTE_START),
            getCalendarDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, HOUR_FINISH, MINUTE_FINISH),
            SECURITY_CODE,
            PLACE
        )
    }

    private fun getCalendarDate(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        return calendar
    }

    companion object {
        private const val FIVE_INT = 5
        private const val SECURITY_CODE = "111"
        private const val PLACE = "20"
        private const val YEAR_START = 2019
        private const val MONTH_START = 3
        private const val DAY_OF_MONTH_START = 5
        private const val HOUR_START = 4
        private const val MINUTE_START = 20
        private const val YEAR_FINISH = 2020
        private const val MONTH_FINISH = 5
        private const val DAY_OF_MONTH_FINISH = 15
        private const val HOUR_FINISH = 7
        private const val MINUTE_FINISH = 45
        private const val ONE_INT = 1
        private const val ZERO_INT = 0
    }
}
