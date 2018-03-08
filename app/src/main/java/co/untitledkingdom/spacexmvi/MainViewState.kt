package co.untitledkingdom.spacexmvi

import co.untitledkingdom.spacexmvi.models.Rocket

data class MainViewState(val progress: Boolean = false,
                         val error: Boolean = false,
                         val rocketList: List<Rocket> = listOf())