package com.example.parkingsystemkotlin.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.databinding.ActivityReservationsRecyclerBinding
import com.example.parkingsystemkotlin.mvp.contract.ReservationsRecyclerContract
import com.example.parkingsystemkotlin.mvp.model.ReservationsRecyclerModel
import com.example.parkingsystemkotlin.mvp.model.reservation.ReservationInformationDB
import com.example.parkingsystemkotlin.mvp.presenter.ReservationsRecyclerPresenter
import com.example.parkingsystemkotlin.mvp.view.ReservationsRecyclerView

class ReservationsRecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationsRecyclerBinding
    private lateinit var presenter: ReservationsRecyclerContract.ReservationsRecyclerPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationsRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter =
            ReservationsRecyclerPresenter(ReservationsRecyclerModel(ReservationInformationDB), ReservationsRecyclerView(this, binding))

        presenter.seeAllReservations()
        binding.buttonReservationsRecyclerActivityBack.setOnClickListener { onBackPressed() }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, ReservationsRecyclerActivity::class.java)
    }
}
