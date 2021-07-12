package id.kotlin.screeningtest_mobiledeveloperintern

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GuestActivity : AppCompatActivity() {
    private val resultCode = 2
    private var adapter: GuestAdapter? = null
    private var gridView: GridView? = null
    private var realm: Realm? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        title = "Guest"
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/596dec7f0f000023032b8017")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiInterface::class.java)
        val call: Call<List<Guest?>?>? = service.guests
        call?.enqueue(object : Callback<List<Guest?>?> {
            override fun onResponse(call: Call<List<Guest?>?>?, response: Response<List<Guest?>?>) {
                try {
                    val results: List<Guest> = response.body() as List<Guest>
                    for (i in results.indices) {
                        adapter!!.add(Guest(results[i].id, results[i].nama, results[i].birthDate))
                        realm?.executeTransaction { realm ->
                            val guest: Guest = realm.createObject(
                                Guest::class.java,
                                results[i].id
                            )
                            guest.nama = results[i].nama
                            guest.birthDate = results[i].birthDate
                        }
                    }
                } catch (e: Exception) {
                    Log.d("onResponse", "There is an error")
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<List<Guest?>?>?, t: Throwable) {
                Log.d("onFailure", t.toString())
                val results: RealmResults<Guest> = realm?.where(Guest::class.java)!!.findAll()
                for (i in results.indices) {
                    adapter!!.add(Guest(results[i]!!.id, results[i]!!.nama, results[i]!!.birthDate))
                }
            }
        })
        val guests: ArrayList<Guest?> = ArrayList()
        adapter = GuestAdapter(this, guests)
        gridView = findViewById<View>(R.id.guestGridView) as GridView
        gridView!!.adapter = adapter
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

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
    }
}