package id.kotlin.screeningtest_mobiledeveloperintern

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class EventActivity : AppCompatActivity() {
    private val resultCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        title = "Event"
        val events: ArrayList<Event?> = ArrayList()
        val adapter = EventAdapter(this, events)

        // Inisialisasi event dengan data dummy
        val event1 = Event(R.drawable.event, "Google Cloud", "23 Juli 2021")
        adapter.add(event1)
        val event2 = Event(R.drawable.event, "Developer Coaching", "26 Juli 2021")
        adapter.add(event2)
        val event3 = Event(R.drawable.event, "Android training", "30 Juli 2021")
        adapter.add(event3)
        val event4 = Event(R.drawable.event, "Internet Security", "31 Juli 2021")
        adapter.add(event4)
        val event5 = Event(R.drawable.event, "Technopreneurship", "31 Juli 2021")
        adapter.add(event5)
        val listView: ListView = findViewById<View>(R.id.eventsList) as ListView
        listView.setAdapter(adapter)
        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val event: Event = listView.getItemAtPosition(position) as Event
            val eventName: String = event.nama
            val resultIntent = Intent()
            resultIntent.putExtra("EVENT-NAME", eventName)
            setResult(resultCode, resultIntent)
            finish()
        })
    }
}