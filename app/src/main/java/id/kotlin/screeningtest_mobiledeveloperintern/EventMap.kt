package id.kotlin.screeningtest_mobiledeveloperintern
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class EventMap : AppCompatActivity(), OnMapReadyCallback {
    // Attribute for Map
    private var mMap: GoogleMap? = null
    private var longitude = 0.0
    private var latitude = 0.0
    private val longitudes = doubleArrayOf(
        107.612335,
        107.612723
    )
    private val latitudes = doubleArrayOf(
        -6.893361,
        -6.897517
    )
    private val m: Marker? = null
    private var mPager: ViewPager? = null
    private var mPagerAdapter: PagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_map)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById<View>(R.id.pager) as ViewPager
        mPagerAdapter = ImageSlidePagerAdapter(this)
        mPager!!.adapter = mPagerAdapter
        mPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                longitude = longitudes[position % longitudes.size]
                latitude = latitudes[position % latitudes.size]
                val location = LatLng(latitude, longitude)
                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(location))
                mMap!!.animateCamera(CameraUpdateFactory.zoomIn())
                mMap!!.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu_add -> {
                val intent = Intent(this, EventMap::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        for (i in longitudes.indices) {
            val location = LatLng(latitudes[i], longitudes[i])
            val markerOptions = MarkerOptions().position(location).title("Event " + (i + 1))
            mMap!!.addMarker(markerOptions)
        }
        val location = LatLng(
            latitudes[mPager!!.currentItem], longitudes[mPager!!.currentItem]
        )
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap!!.animateCamera(CameraUpdateFactory.zoomIn())
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)
    }
}