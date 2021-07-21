package com.example.parkingsystemkotlin.mvp.model

import com.example.parkingsystemkotlin.mvp.contract.SpacesParkingDialogContract
import com.example.parkingsystemkotlin.utils.ConstantUtils

class SpacesParkingDialogModel : SpacesParkingDialogContract.DialogModel {

    private var spaces: Int = ConstantUtils.SPACE_DEFAULT

    override fun checkSpacesInput(spaces: String) {
        this.spaces = if (spaces.isEmpty()) {
            ConstantUtils.SPACE_DEFAULT
        } else {
            spaces.toInt()
        }
    }

    override fun getSpaces(): Int = spaces
}
