package dev.divyanshgemini.mapbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var markers: MutableLiveData<MutableList<List<Double>>> = MutableLiveData()
    private var isMapLaunched: Boolean = false

    fun isMapLaunched() = isMapLaunched

    fun setMapLaunched(isMapLaunched: Boolean) {
        this.isMapLaunched = isMapLaunched
    }

    fun getMarkers(): MutableLiveData<MutableList<List<Double>>> {
        markers.value = markers.value ?: mutableListOf()
        return markers
    }

    fun addMarker(marker: List<Double>) {
        val updatedMarkers = markers.value ?: mutableListOf()
        updatedMarkers.add(marker)
        markers.postValue(updatedMarkers)
    }

    fun removeMarker(marker: List<Double>) {
        val updatedMarkers = markers.value ?: mutableListOf()
        updatedMarkers.remove(marker)
        markers.postValue(updatedMarkers)
    }

    fun clearMarkers() = markers.postValue(mutableListOf())
}