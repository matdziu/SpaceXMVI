package co.untitledkingdom.spacexmvi

import co.untitledkingdom.spacexmvi.models.Rocket

sealed class PartialMainViewState {

    class ProgressState : PartialMainViewState()

    class ErrorState : PartialMainViewState()

    class ListFetchedState(val rocketList: List<Rocket>) : PartialMainViewState()
}