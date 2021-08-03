package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.mvp.contract.ReservationsRecyclerContract
import com.example.parkingsystemkotlin.mvp.model.ReservationsRecyclerModel
import com.example.parkingsystemkotlin.mvp.model.reservation.DataBase
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import java.util.Calendar
import org.junit.Assert.assertEquals
import org.junit.Test

class ReservationsRecyclerPresenterTest {

    private val reservationInformationDB: DataBase = mock()
    private val view: ReservationsRecyclerContract.ReservationsRecyclerViewContract = mock()
    private val model: ReservationsRecyclerContract.ReservationsRecyclerModelContract = ReservationsRecyclerModel(reservationInformationDB)
    private val presenter: ReservationsRecyclerContract.ReservationsRecyclerPresenterContract = ReservationsRecyclerPresenter(model, view)
    private val reservationsList: MutableList<Reservation> = mock()

    @Test
    fun `see all reservations test`() {
        //Setup
        whenever(reservationInformationDB.getAllReservations()).thenReturn(reservationsList)
        //Execute
        presenter.seeAllReservations()
        //Assert
        verify(view).seeAllReservationsInScreen(model.getAllReservations())
        assertEquals(reservationsList, model.getAllReservations())
    }
}
