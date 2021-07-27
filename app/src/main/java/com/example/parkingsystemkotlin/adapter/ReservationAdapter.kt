package com.example.parkingsystemkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystemkotlin.R
import com.example.parkingsystemkotlin.databinding.ItemRecyclerViewBinding
import com.example.parkingsystemkotlin.mvp.model.reservation.Reservation

class ReservationAdapter(private val reservations: List<Reservation>) : RecyclerView.Adapter<ReservationAdapter.ReservationHolder>() {

    class ReservationHolder(private val binding: ItemRecyclerViewBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(reservation: Reservation) {
            binding.textViewItemRecyclerViewStartDate.text = context.getString(
                R.string.text_view_reservation_start_date_message_item_recycler_view,
                reservation.getStartDateAndTimeFormated()
            )
            binding.textViewItemRecyclerViewFinishDate.text = context.getString(
                R.string.text_view_reservation_finish_date_message_item_recycler_view,
                reservation.getFinishDateAndTimeFormated()
            )
            binding.textViewItemRecyclerViewSecurityCode.text =
                context.getString(R.string.text_view_reservation_security_code_message_item_recycler_view, reservation.securityCode)
            binding.textViewItemRecyclerViewPlace.text =
                context.getString(R.string.text_view_reservation_place_message_item_recycler_view, reservation.place)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder =
        ReservationHolder(ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {
        holder.render(reservations[position])
    }

    override fun getItemCount(): Int = reservations.size
}
