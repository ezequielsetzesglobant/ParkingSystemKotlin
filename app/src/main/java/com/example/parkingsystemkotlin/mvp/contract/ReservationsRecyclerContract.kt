package com.example.parkingsystemkotlin.mvp.contract

import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation

interface ReservationsRecyclerContract {

    interface ReservationsRecyclerModelContract {
        fun getAllReservations(): MutableList<Reservation>
    }

    interface ReservationsRecyclerPresenterContract {
        fun seeAllReservations()
    }

    interface ReservationsRecyclerViewContract {
        fun seeAllReservationsInScreen(listReservations: MutableList<Reservation>)
    }
}
