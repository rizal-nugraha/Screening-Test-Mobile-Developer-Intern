package id.kotlin.screeningtest_mobiledeveloperintern

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class EventAdapter(context: Context?, events: ArrayList<Event?>?) :
    ArrayAdapter<Event?>(
        context!!, 0,
        events!!
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val event = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        }
        val imageEvent = convertView!!.findViewById<View>(R.id.eventImageView) as ImageView
        val namaEvent = convertView.findViewById<View>(R.id.namaEventTextView) as TextView
        val tanggalEvent = convertView.findViewById<View>(R.id.tanggalEventTextView) as TextView
        imageEvent.setImageResource(event!!.image)
        namaEvent.text = event.nama
        tanggalEvent.text = event.tanggal
        return convertView
    }
}