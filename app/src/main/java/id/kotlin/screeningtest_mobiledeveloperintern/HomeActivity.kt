package id.kotlin.screeningtest_mobiledeveloperintern

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val nextButton = findViewById<View>(R.id.nextButton) as Button
        nextButton.setOnClickListener { v -> sendNama(v) }
    }

    fun sendNama(view: View?) {
        val namaEditText = findViewById<View>(R.id.namaEditText) as EditText
        val nama = namaEditText.text.toString()
        val intent = Intent(this@HomeActivity, MainActivity::class.java)
        intent.putExtra("NAMA", nama)
        startActivity(intent)
    }
}
