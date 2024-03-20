package dev.divyanshgemini.mapbox

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import dev.divyanshgemini.mapbox.databinding.BottomSheetMapTypeBinding

class BottomSheetMapType(private var mapView: MapView) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetMapTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMapTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapTypes = mapOf(
            binding.mapTypeStreet to Style.STANDARD,
            binding.mapTypeSatellite to Style.SATELLITE,
            binding.mapTypeHybrid to Style.OUTDOORS
        )

        mapTypes.forEach { (view, style) ->
            view.setOnClickListener {
                mapView.mapboxMap.loadStyle(style)
                dismiss()
            }
        }
    }

    // expanding bottom sheet to take its full height
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val bottomSheet = (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }
}