package com.inf251.examen2.functions

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
fun LocacionActual(context: Context, onLocation: (Location?)->Unit){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location:Location?->onLocation(location) }
        .addOnFailureListener { onLocation(null) }
}