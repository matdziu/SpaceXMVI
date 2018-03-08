package co.untitledkingdom.spacexmvi.list

import android.support.v7.widget.RecyclerView
import android.view.View
import co.untitledkingdom.spacexmvi.models.Rocket
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_rocket.view.rocketImageView
import kotlinx.android.synthetic.main.item_rocket.view.rocketNameTextView

class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(rocket: Rocket) {
        with(itemView) {
            rocketNameTextView.text = rocket.name
            Glide.with(this).load(rocket.photoUrl).into(rocketImageView)
        }
    }
}