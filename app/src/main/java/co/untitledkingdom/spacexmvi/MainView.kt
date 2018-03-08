package co.untitledkingdom.spacexmvi

import co.untitledkingdom.spacexmvi.models.Rocket

interface MainView {

    fun showProgressBar(show: Boolean)

    fun showError(show: Boolean)

    fun setRocketList(rocketList: List<Rocket>)
}