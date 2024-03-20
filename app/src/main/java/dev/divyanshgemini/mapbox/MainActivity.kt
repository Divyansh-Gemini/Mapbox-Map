package dev.divyanshgemini.mapbox

import android.os.Bundle
import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.mapbox.android.gestures.StandardScaleGestureDetector
import com.mapbox.common.MapboxOptions
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnScaleListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.viewport
import dev.divyanshgemini.mapbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView
    private lateinit var viewModel: MainActivityViewModel

    private val CROSSHAIRS_QUESTION = 0
    private val CROSSHAIRS_BLANK = 1
    private val CROSSHAIRS_GPS = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // configuring mapbox public token
        val mapboxPublicAccessToken = BuildConfig.MAPBOX_PUBLIC_TOKEN
        MapboxOptions.accessToken = mapboxPublicAccessToken

        // setting up the view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mapView = binding.mapView

        // setting up the view model
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getMarkers().observe(this) { addMarkersOnMap(it) }

        // going to current location
        goToCurrentLocation()

        // setting up the button for selecting map type
        binding.btnMapType.setOnClickListener {
            val bottomSheetMapType = BottomSheetMapType(mapView)
            bottomSheetMapType.show(supportFragmentManager, "BottomSheetMapType")
        }

        // setting up the button for going to current location
        binding.btnCurrentLocation.setOnClickListener{
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

        // adding marker on click on map
        mapView.mapboxMap.addOnMapClickListener { point ->
            viewModel.addMarker(listOf(point.longitude(), point.latitude()))
            true
        }

        // changing crosshairs icon based scaling
        mapView.gestures.addOnScaleListener(object : OnScaleListener {
            override fun onScale(detector: StandardScaleGestureDetector) {}
            override fun onScaleBegin(detector: StandardScaleGestureDetector) { updateCrossHairsIcon(CROSSHAIRS_BLANK) }
            override fun onScaleEnd(detector: StandardScaleGestureDetector) {}
        })
    }

    // performing actions based on result of permission request
    private val locationPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        permissions -> when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> goToCurrentLocation()
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> goToCurrentLocation()
            else -> Toast.makeText(this, "Location permission needed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToCurrentLocation() {
        if (isLocationOn()) {
            with(mapView) {
                location.locationPuck = createDefault2DPuck(withBearing = true)
                location.enabled = true
                location.pulsingEnabled = true
                location.puckBearing = PuckBearing.COURSE
                viewport.transitionTo(
                    targetState = viewport.makeFollowPuckViewportState(),
                    transition = if (viewModel.isMapLaunched())
                        viewport.makeImmediateViewportTransition()
                    else
                        viewport.makeDefaultViewportTransition()
                )
                updateCrossHairsIcon(CROSSHAIRS_GPS)
                viewModel.setMapLaunched(false)
            }
        }
        else {
            Toast.makeText(this, "Please turn on the location", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLocationOn(): Boolean {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationStatus = manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        updateCrossHairsIcon(if (locationStatus) CROSSHAIRS_BLANK else CROSSHAIRS_QUESTION)
        return locationStatus
    }

    private fun addMarkersOnMap(markers: MutableList<List<Double>>){
        val annotationManager = mapView.annotations.createPointAnnotationManager()

        annotationManager.deleteAll()

        for (marker in markers) {
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(marker[0], marker[1]))
                .withIconImage(AppCompatResources.getDrawable(applicationContext, R.drawable.map_marker)!!.toBitmap())

             annotationManager.create(pointAnnotationOptions)
        }
    }

    private fun updateCrossHairsIcon(requestedIcon: Int) {
        val icons = arrayOf(R.drawable.crosshairs_question, R.drawable.crosshairs, R.drawable.crosshairs_gps)
        binding.btnCurrentLocation.setImageResource(icons[requestedIcon])
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setMapLaunched(true)
    }
}
