package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.mvp.contract.ReservationsRecyclerContract

class ReservationsRecyclerPresenter(
    private val model: ReservationsRecyclerContract.ReservationsRecyclerModelContract,
    private val view: ReservationsRecyclerContract.ReservationsRecyclerViewContract
) : ReservationsRecyclerContract.ReservationsRecyclerPresenterContract {

    override fun seeAllReservations() {
        view.seeAllReservationsInScreen(model.getAllReservations())
    }
}
