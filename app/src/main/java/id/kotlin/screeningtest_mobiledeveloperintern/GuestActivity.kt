package id.kotlin.screeningtest_mobiledeveloperintern

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class GuestActivity : AppCompatActivity() {
    private val resultCode = 2
    private var adapter: GuestAdapter? = null
    private var gridView: GridView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        title = "Guest"
        HttpAsyncTask().execute("http://www.mocky.io/v2/596dec7f0f000023032b8017")
        val guests: ArrayList<Guest?> = ArrayList()
        adapter = GuestAdapter(this, guests)
        gridView = findViewById<View>(R.id.guestGridView) as GridView
        gridView!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val guest = gridView!!.getItemAtPosition(position) as Guest
                val guestName = guest.nama
                val guestBirthDate = guest.birthDate
                val date = guestBirthDate!!.split("-").toTypedArray()
                val birthDate = date[2].toInt()
                if (birthDate % 2 == 0 && birthDate % 3 == 0) {
                    Toast.makeText(this@GuestActivity, "iOS", Toast.LENGTH_LONG).show()
                } else if (birthDate % 2 == 0) {
                    Toast.makeText(this@GuestActivity, "blackberry", Toast.LENGTH_LONG).show()
                } else if (birthDate % 3 == 0) {
                    Toast.makeText(this@GuestActivity, "android", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@GuestActivity, "feature phone", Toast.LENGTH_LONG).show()
                }
                val resultIntent = Intent()
                resultIntent.putExtra("GUEST-NAME", guestName)
                setResult(resultCode, resultIntent)
                finish()
            }
    }


    @SuppressLint("StaticFieldLeak")
    private inner class HttpAsyncTask :
        AsyncTask<String?, Void?, Void?>() {
        var response = ""
        override fun doInBackground(vararg params: String?): Void? {
            var url: URL? = null
            try {
                val location = 1
                url = URL(location)
            } catch (e: MalformedURLException) {
                Log.e("ERROR", "Can't connect to the url")
            }
            if (url != null) {
                try {
                    val urlconn = url.openConnection() as HttpURLConnection
                    val `in` = BufferedReader(InputStreamReader(urlconn.inputStream))
                    var inputline: String
                    while (`in`.readLine().also { inputline = it } != null) {
                        response += inputline
                    }
                    `in`.close()
                    urlconn.disconnect()
                } catch (e: IOException) {
                    Log.e("ERROR", "Can't receive data")
                }
            } else {
                Log.e("ERROR", "Can't connect to the url")
            }
            return null
        }

        private fun URL(location: Int): URL? {
          HttpAsyncTask().execute()
            return null
        }

        override fun onPostExecute(result: Void?) {
            Log.d("response", response)
            try {
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val guestName = jsonObject.getString("name")
                    val guestBirthDate = jsonObject.getString("birthdate")
                    val guest = Guest(R.drawable.guest, guestName, guestBirthDate)
                    adapter!!.add(guest)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            gridView!!.adapter = adapter
        }
    }
}