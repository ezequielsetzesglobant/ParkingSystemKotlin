package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.utils.ConstantUtils

class ParkingModel : ParkingContract.Model {

    private var spaces: Int = ConstantUtils.SPACE_DEFAULT

    override fun setSpaces(spaces: Int) {
        this.spaces = spaces
    }

    override fun getSpaces(): Int = spaces
}
