package com.cm.gas_gps.utils.SwipeGesture

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cm.gas_gps.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

abstract class SwipeGestureVehiculos(context: Context) : ItemTouchHelper.SimpleCallback(0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

    val aceptarColor = ContextCompat.getColor(context, R.color.primary_green)
    val editarColor = ContextCompat.getColor(context, R.color.dorado)
    val iconAceptar = R.drawable.ic_check

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return  false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(aceptarColor)
            .addSwipeLeftActionIcon(iconAceptar)
            .addSwipeLeftLabel("ASIGNAR RUTA")
            .setSwipeLeftLabelColor(Color.WHITE)
            .addSwipeRightBackgroundColor(editarColor)
            .addSwipeRightActionIcon(iconAceptar)
            .addSwipeRightLabel("EDITAR")
            .setSwipeRightLabelColor(Color.WHITE)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}