package com.example.parkingsystemkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingsystemkotlin.databinding.ActivityParkingBinding
import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.ParkingModel
import com.example.parkingsystemkotlin.mvp.presenter.ParkingPresenter
import com.example.parkingsystemkotlin.mvp.view.ParkingView

class ParkingActivity : AppCompatActivity(), ListenerDialogFragment {

    private lateinit var binding: ActivityParkingBinding
    private lateinit var presenter: ParkingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ParkingPresenter(ParkingModel(), ParkingView(this, binding))

        binding.buttonMainSetAmountParkingSpaces.setOnClickListener { presenter.inflateDialog(this) }
    }

    override fun setAmountSpaces(spaces: Int) {
        presenter.onSetParkingPlacesButtonPressed(spaces)
    }
}
