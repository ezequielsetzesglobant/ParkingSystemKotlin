package com.example.parkingsystemkotlin.listener

import java.io.Serializable

interface ListenerDialogFragment : Serializable {
    fun setAmountSpaces(spaces: Int)
}
