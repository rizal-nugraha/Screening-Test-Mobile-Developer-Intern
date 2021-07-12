package id.kotlin.screeningtest_mobiledeveloperintern

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : Activity() {
    private val eventActvityCode = 1
    private val guestActivityCode = 2
    private var eventButton: Button? = null
    private var guestButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val extras = intent.extras
        if (extras != null) {
            val namaTextView = findViewById<View>(R.id.namaTextView) as TextView
            val nama = extras.getString("NAMA")
            namaTextView.text = nama
        }
        eventButton = findViewById<View>(R.id.eventButton) as Button
        eventButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, EventActivity::class.java)
            startActivityForResult(intent, eventActvityCode)
        }
        guestButton = findViewById<View>(R.id.guestButton) as Button
        guestButton!!.setOnClickListener {
            val intent = Intent(this@MainActivity, GuestActivity::class.java)
            startActivityForResult(intent, guestActivityCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == eventActvityCode) {
            val eventName = data.getStringExtra("EVENT-NAME")
            eventButton!!.text = eventName
        } else if (requestCode == guestActivityCode) {
            val guestName = data.getStringExtra("GUEST-NAME")
            guestButton!!.text = guestName
        }
    }
}