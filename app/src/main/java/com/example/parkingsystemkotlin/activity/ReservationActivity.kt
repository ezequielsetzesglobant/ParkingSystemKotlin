package com.example.parkingsystemkotlin.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.databinding.ActivityReservationBinding
import com.example.parkingsystemkotlin.mvp.contract.ReservationContract
import com.example.parkingsystemkotlin.mvp.model.ReservationModel
import com.example.parkingsystemkotlin.mvp.model.reservation.ReservationInformationDB
import com.example.parkingsystemkotlin.mvp.presenter.ReservationPresenter
import com.example.parkingsystemkotlin.mvp.view.ReservationView

class ReservationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityReservationBinding
    private lateinit var presenter: ReservationContract.ReservationPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ReservationPresenter(ReservationModel(ReservationInformationDB), ReservationView(this, binding))

        binding.buttonReservationActivityStartDate.setOnClickListener { presenter.createDate(this, true) }
        binding.buttonReservationActivityFinishDate.setOnClickListener { presenter.createDate(this, false) }

        binding.buttonReservationActivitySave.setOnClickListener {
            presenter.saveReservationInformation(
                binding.editTextReservationActivitySecurityCode.text.toString(),
                binding.editTextReservationActivityPlace.text.toString()
            )
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        presenter.saveReservationDate(year, month, dayOfMonth, this)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        presenter.saveReservationTime(hourOfDay, minute)
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, ReservationActivity::class.java)
    }
}
