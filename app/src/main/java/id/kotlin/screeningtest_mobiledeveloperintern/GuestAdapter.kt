package id.kotlin.screeningtest_mobiledeveloperintern

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GuestAdapter(context: Context?, guests: ArrayList<Guest?>?) :
    ArrayAdapter<Guest?>(context!!, 0, guests!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val guest = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_guest, parent, false)
        }
        val namaEvent = convertView!!.findViewById<View>(R.id.guestTextView) as TextView
        namaEvent.text = guest!!.nama
        namaEvent.setBackgroundResource(R.drawable.guest)
        return convertView
    }
}