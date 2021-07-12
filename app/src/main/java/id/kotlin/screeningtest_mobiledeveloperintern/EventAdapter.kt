package id.kotlin.screeningtest_mobiledeveloperintern
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class EventAdapter(
    private val events: ArrayList<Event>,
    private val activity: Activity,
    applicationContext: Context
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.nama
        holder.eventDate.text = event.tanggal
        holder.eventImage.setImageResource(event.image)
        holder.tag1.text = event.tags[0]
        if (event.tags.size == 1) {
            holder.tag2.visibility = View.GONE
        } else if (event.tags.size == 2) {
            holder.tag2.text = event.tags[1]
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class EventViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById<View>(R.id.card_view) as CardView
        var eventName: TextView = itemView.findViewById<View>(R.id.namaEventTextView) as TextView
        var eventDate: TextView = itemView.findViewById<View>(R.id.tanggalEventTextView) as TextView
        var tag1: TextView = itemView.findViewById<View>(R.id.tag1) as TextView
        var tag2: TextView = itemView.findViewById<View>(R.id.tag2) as TextView
        var eventImage: ImageView = itemView.findViewById<View>(R.id.eventImageView) as ImageView
        private val resultCode = 1

        init {
            cardView.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("EVENT-NAME", eventName.text)
                activity.setResult(resultCode, resultIntent)
                activity.finish()
            }
        }
    }
}