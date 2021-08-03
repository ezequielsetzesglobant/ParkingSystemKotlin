package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.ReservationsRecyclerContract
import com.example.parkingsystemkotlin.mvp.model.reservation.DataBase
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation

class ReservationsRecyclerModel(private val reservationInformationDB: DataBase) :
    ReservationsRecyclerContract.ReservationsRecyclerModelContract {

    override fun getAllReservations(): MutableList<Reservation> {
        return reservationInformationDB.getAllReservations()
    }
}
