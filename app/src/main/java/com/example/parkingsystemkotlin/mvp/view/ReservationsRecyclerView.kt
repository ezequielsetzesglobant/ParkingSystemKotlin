package com.example.parkingsystemkotlin.mvp.view

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingsystemkotlin.adapter.ReservationAdapter
import com.example.parkingsystemkotlin.databinding.ActivityReservationsRecyclerBinding
import com.example.parkingsystemkotlin.mvp.contract.ReservationsRecyclerContract
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation
import com.example.parkingsystemkotlin.mvp.view.base.ActivityView

class ReservationsRecyclerView(activity: Activity, private val binding: ActivityReservationsRecyclerBinding) : ActivityView(activity),
    ReservationsRecyclerContract.ReservationsRecyclerViewContract {

    override fun seeAllReservationsInScreen(listReservations: MutableList<Reservation>) {
        binding.recyclerViewReservationsRecyclerActivityList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewReservationsRecyclerActivityList.adapter = ReservationAdapter(listReservations)
    }
}
