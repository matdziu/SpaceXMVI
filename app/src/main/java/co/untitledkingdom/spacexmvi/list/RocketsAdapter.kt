package co.untitledkingdom.spacexmvi.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.untitledkingdom.spacexmvi.R
import co.untitledkingdom.spacexmvi.models.Rocket

class RocketsAdapter : RecyclerView.Adapter<RocketViewHolder>() {

    private val rocketList = arrayListOf<Rocket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rocket, parent, false)
        return RocketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.bind(rocketList[position])
    }

    override fun getItemCount(): Int = rocketList.size

    fun setRocketList(rocketList: List<Rocket>) {
        if (this.rocketList != rocketList) {
            this.rocketList.clear()
            this.rocketList.addAll(rocketList)
            notifyDataSetChanged()
        }
    }
}