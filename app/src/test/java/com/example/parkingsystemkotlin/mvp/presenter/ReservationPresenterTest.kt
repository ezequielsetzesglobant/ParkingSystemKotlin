package com.example.parkingsystemkotlin.mvp.presenter

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import com.example.parkingsystemkotlin.mvp.contract.ReservationContract
import com.example.parkingsystemkotlin.mvp.model.ReservationModel
import com.example.parkingsystemkotlin.mvp.model.reservation.DataBase
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import java.util.Calendar
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ReservationPresenterTest {

    private val reservationInformationDB: DataBase = mock()
    private val listenerTime: TimePickerDialog.OnTimeSetListener = mock()
    private val listenerDate: DatePickerDialog.OnDateSetListener = mock()
    private val view: ReservationContract.ReservationViewContract = mock()
    private lateinit var model: ReservationContract.ReservationModelContract
    private lateinit var presenter: ReservationContract.ReservationPresenterContract
    private val reservation: Reservation = Reservation(
        getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_OF_DAY_START, MINUTE_START),
        getCalendarDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, HOUR_OF_DAY_FINISH, MINUTE_FINISH),
        SECURITY_CODE,
        PLACE
    )
    private val reservationEmpty: Reservation = Reservation(
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
        EMPTY_STRING,
        EMPTY_STRING
    )
    private val reservationsToPlace: MutableList<Reservation> = mutableListOf()

    @Before
    fun setup() {
        model = ReservationModel(reservationInformationDB)
        presenter = ReservationPresenter(model, view)
    }

    @Test
    fun `create date test`() {
        //Execute
        presenter.createDate(listenerDate, true)
        //Assert
        verify(view).showDatePicker(listenerDate)
    }

    @Test
    fun `save reservation date test`() {
        //Setup
        presenter.createDate(listenerDate, true)
        //Execute
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime)
        //Assert
        verify(view).showTimePicker(listenerTime)
    }

    @Test
    fun `save reservation time test`() {
        //Setup
        presenter.createDate(listenerDate, true)
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime)
        //Execute
        presenter.saveReservationTime(HOUR_OF_DAY_START, MINUTE_START)
        //Assert
        verify(view).showOkDateAndTime(model.getSavedDateAndTime())
        val reservation = Reservation(
            getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_OF_DAY_START, MINUTE_START),
            Calendar.getInstance(),
            EMPTY_STRING,
            EMPTY_STRING
        )
        assertEquals(reservation.getStartDateAndTimeFormated(), model.getSavedDateAndTime())
    }

    @Test
    fun `save reservation information with incomplete reservation test`() {
        //Setup
        whenever(reservationInformationDB.getReservationDB(EMPTY_STRING, EMPTY_STRING)).thenReturn(reservationEmpty)
        //Execute
        presenter.saveReservationInformation(EMPTY_STRING, EMPTY_STRING)
        //Assert
        verify(view).showError()
        assertEqualsReservationFields(
            YEAR_START_AND_FINISH,
            MONTH_START_AND_FINISH,
            DAY_OF_MONTH_START_AND_FINISH,
            HOUR_OF_DAY_START_AND_FINISH,
            MINUTE_START_AND_FINISH,
            YEAR_START_AND_FINISH,
            MONTH_START_AND_FINISH,
            DAY_OF_MONTH_START_AND_FINISH,
            HOUR_OF_DAY_START_AND_FINISH,
            MINUTE_START_AND_FINISH,
            EMPTY_STRING,
            EMPTY_STRING,
            model.getReservation(EMPTY_STRING, EMPTY_STRING)
        )
    }

    @Test
    fun `save reservation information with reservation ok test`() {
        //Setup
        presenter.createDate(listenerDate, true)
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime)
        presenter.saveReservationTime(HOUR_OF_DAY_START, MINUTE_START)
        presenter.createDate(listenerDate, false)
        presenter.saveReservationDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, listenerTime)
        presenter.saveReservationTime(HOUR_OF_DAY_FINISH, MINUTE_FINISH)
        whenever(reservationInformationDB.getReservationDB(PLACE, SECURITY_CODE)).thenReturn(reservation)
        //Execute
        presenter.saveReservationInformation(SECURITY_CODE, PLACE)
        //Assert
        verify(view).finishActivity(model.getReservation(PLACE, SECURITY_CODE))
        assertEqualsReservationFields(
            YEAR_START,
            MONTH_START,
            DAY_OF_MONTH_START,
            HOUR_OF_DAY_START,
            MINUTE_START,
            YEAR_FINISH,
            MONTH_FINISH,
            DAY_OF_MONTH_FINISH,
            HOUR_OF_DAY_FINISH,
            MINUTE_FINISH,
            SECURITY_CODE,
            PLACE,
            model.getReservation(PLACE, SECURITY_CODE)
        )
    }

    @Test
    fun saveReservationInformationWithOverlapReservationTest() {
        //Setup
        whenever(reservationInformationDB.getReservationDB(PLACE, SECURITY_CODE)).thenReturn(reservation)
        reservationsToPlace.add(reservation)
        whenever(reservationInformationDB.getReservationsDB(PLACE)).thenReturn(reservationsToPlace)
        saveReservationDateTime()
        //Execute
        presenter.saveReservationInformation(SECURITY_CODE, PLACE)
        //Assert
        verify(view).showOverlapMessage()
        assertEqualsReservationFields(
            YEAR_START,
            MONTH_START,
            DAY_OF_MONTH_START,
            HOUR_OF_DAY_START,
            MINUTE_START,
            YEAR_FINISH,
            MONTH_FINISH,
            DAY_OF_MONTH_FINISH,
            HOUR_OF_DAY_FINISH,
            MINUTE_FINISH,
            SECURITY_CODE,
            PLACE,
            model.getReservation(PLACE, SECURITY_CODE)
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

    private fun saveReservationDateTime() {
        presenter.createDate(listenerDate, true)
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime)
        presenter.saveReservationTime(HOUR_OF_DAY_START, MINUTE_START)
        presenter.createDate(listenerDate, false)
        presenter.saveReservationDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, listenerTime)
        presenter.saveReservationTime(ReservationPresenterTest.HOUR_OF_DAY_FINISH, MINUTE_FINISH)
    }

    private fun assertEqualsReservationFields(
        yearStart: Int,
        monthStart: Int,
        dayOfMonthStart: Int,
        hourOfDayStart: Int,
        minuteStart: Int,
        yearFinsh: Int,
        monthFinish: Int,
        dayOfMonthFinish: Int,
        hourOfDayFinish: Int,
        minuteFinish: Int,
        securityCode: String,
        place: String,
        reservation: Reservation
    ) {
        assertEquals(yearStart, reservation.startDateAndTime.get(Calendar.YEAR))
        assertEquals(monthStart, reservation.startDateAndTime.get(Calendar.MONTH))
        assertEquals(dayOfMonthStart, reservation.startDateAndTime.get(Calendar.DAY_OF_MONTH))
        assertEquals(hourOfDayStart, reservation.startDateAndTime.get(Calendar.HOUR))
        assertEquals(minuteStart, reservation.startDateAndTime.get(Calendar.MINUTE))
        assertEquals(yearFinsh, reservation.finishDateAndTime.get(Calendar.YEAR))
        assertEquals(monthFinish, reservation.finishDateAndTime.get(Calendar.MONTH))
        assertEquals(dayOfMonthFinish, reservation.finishDateAndTime.get(Calendar.DAY_OF_MONTH))
        assertEquals(hourOfDayFinish, reservation.finishDateAndTime.get(Calendar.HOUR))
        assertEquals(minuteFinish, reservation.finishDateAndTime.get(Calendar.MINUTE))
        assertEquals(securityCode, reservation.securityCode)
        assertEquals(place, reservation.place)
    }

    companion object {
        private const val SECURITY_CODE = "1234"
        private const val PLACE = "10"
        private const val EMPTY_STRING = ""

        private const val YEAR_START = 2021
        private const val MONTH_START = 7
        private const val DAY_OF_MONTH_START = 8
        private const val HOUR_OF_DAY_START = 3
        private const val MINUTE_START = 17

        private const val YEAR_FINISH = 2022
        private const val MONTH_FINISH = 9
        private const val DAY_OF_MONTH_FINISH = 10
        private const val HOUR_OF_DAY_FINISH = 6
        private const val MINUTE_FINISH = 25

        private const val YEAR_START_AND_FINISH = 1970
        private const val MONTH_START_AND_FINISH = 1
        private const val DAY_OF_MONTH_START_AND_FINISH = 1
        private const val HOUR_OF_DAY_START_AND_FINISH = 0
        private const val MINUTE_START_AND_FINISH = 0
    }
}
