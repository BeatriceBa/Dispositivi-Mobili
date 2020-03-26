
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dispositivimobili.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * A simple [Fragment] subclass.
 */
class NotificationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val actualPos = LatLng(39.36561037798836,16.22534392869258)
        val cinemaZ = LatLng(39.3149306,16.2615252)
        val cinemaC = LatLng(39.2965409,16.2518997)

        mMap.addMarker(MarkerOptions().position(actualPos).title("Sei qui"))
        mMap.addMarker(MarkerOptions().position(cinemaZ).title("Cinema più vicino"))
        mMap.addMarker(MarkerOptions().position(cinemaC).title("Cinema"))

        val zoom = CameraUpdateFactory.zoomTo(13f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(actualPos))
        mMap.animateCamera(zoom)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_notification, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        return view
    }



}
