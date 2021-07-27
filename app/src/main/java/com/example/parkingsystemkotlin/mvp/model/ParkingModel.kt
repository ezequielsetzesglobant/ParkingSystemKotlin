package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.reservation.DataBase
import com.example.parkingsystemkotlin.utils.ConstantUtils

class ParkingModel(private val reservationInformationDB: DataBase) : ParkingContract.Model {

    private var spaces: Int = ConstantUtils.SPACE_DEFAULT

    override fun setSpaces(spaces: Int) {
        this.spaces = spaces
    }

    override fun getSpaces(): Int = spaces

    override fun releaseParking(): Int = reservationInformationDB.releasePastReservations()
}
