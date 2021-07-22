package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.ParkingContract
import com.example.parkingsystemkotlin.mvp.model.ParkingModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ParkingPresenterTest {

    private val listener: ListenerDialogFragment = mock()
    private val view: ParkingContract.View = mock()
    private val model: ParkingContract.Model = ParkingModel()
    private val presenter: ParkingContract.Presenter = ParkingPresenter(model, view)

    @Test
    fun `on set parking places button pressed test`() {
        //Execute
        presenter.onSetParkingPlacesButtonPressed(FIVE_INT)
        //Assert
        verify(view).showPopUp(model.getSpaces())
        assertEquals(FIVE_INT, model.getSpaces())
    }

    @Test
    fun `inflate dialog test`() {
        //Execute
        presenter.inflateDialog(listener)
        //Assert
        verify(view).showDialog(listener)
    }

    companion object {
        private const val FIVE_INT = 5
    }
}
