package id.kotlin.screeningtest_mobiledeveloperintern

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class ImageSlidePagerAdapter(var mContext: Context) : PagerAdapter() {
    var mLayoutInflater: LayoutInflater
    var mResources = intArrayOf(
        R.drawable.first,
        R.drawable.second
    )
    var position = 0
        private set

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater.inflate(R.layout.image_slide, container, false)
        val imageView = itemView.findViewById<View>(R.id.event_image_view) as ImageView
        imageView.setImageResource(mResources[this.position])
        val eventName = itemView.findViewById<View>(R.id.event_name) as TextView
        eventName.text = "Event-" + (this.position + 1)
        container.addView(itemView)
        if (this.position >= mResources.size - 1) this.position = 0 else ++this.position
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

    init {
        mLayoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}