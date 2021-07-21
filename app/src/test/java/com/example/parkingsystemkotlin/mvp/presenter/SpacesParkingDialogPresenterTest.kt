package com.example.parkingsystemkotlin.mvp.presenter

import com.example.parkingsystemkotlin.listener.ListenerDialogFragment
import com.example.parkingsystemkotlin.mvp.contract.SpacesParkingDialogContract
import com.example.parkingsystemkotlin.mvp.model.SpacesParkingDialogModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class SpacesParkingDialogPresenterTest {

    private val listener: ListenerDialogFragment = mock()
    private val view: SpacesParkingDialogContract.DialogView = mock()
    private val model: SpacesParkingDialogContract.DialogModel = SpacesParkingDialogModel()
    private val presenter: SpacesParkingDialogContract.DialogPresenter = SpacesParkingDialogPresenter(model, view)

    @Test
    fun `notify activity with spaces test`() {
        //Execute
        presenter.notifyActivity(listener, THREE_STRING)
        //Assert
        verify(view).closeDialog(listener, THREE_INT)
        assertEquals(THREE_INT, model.getSpaces())
    }

    @Test
    fun `notify activity with empty spaces test`() {
        //Execute
        presenter.notifyActivity(listener, EMPTY_STRING)
        //Assert
        verify(view).closeDialog(listener, ZERO_INT)
        assertEquals(ZERO_INT, model.getSpaces())
    }

    companion object {
        private const val THREE_STRING = "3"
        private const val EMPTY_STRING = ""
        private const val THREE_INT = 3
        private const val ZERO_INT = 0
    }
}
