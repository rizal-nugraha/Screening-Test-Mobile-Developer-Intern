package id.kotlin.screeningtest_mobiledeveloperintern

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class EventActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        mRecyclerView = findViewById<View>(R.id.eventsList) as RecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        val events: ArrayList<Event> = ArrayList()
        events.add(
            Event(
                R.drawable.event,
                "Google Cloud",
                "23 Juli 2021",
                ArrayList(listOf("Programming", "web"))
            )
        )
        events.add(
            Event(
                R.drawable.event,
                "Developer Coaching",
                "26 Juli 2021",
                ArrayList(listOf("Programming", "Unity"))
            )
        )
        events.add(
            Event(
                R.drawable.event,
                "Android training",
                "30 Juli 2021",
                ArrayList(listOf("Programming", "Android"))
            )
        )
        events.add(
            Event(
                R.drawable.event,
                "Internet Security",
                "31 Juli 2021",
                ArrayList(listOf("Docker"))
            )
        )
        events.add(
            Event(
                R.drawable.event,
                "Technopreneurship",
                "31 Juli 2021",
                ArrayList(listOf("PM"))
            )
        )
        mAdapter = EventAdapter(events, this, applicationContext)
        mRecyclerView!!.adapter = mAdapter
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.custom_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
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
}