package co.untitledkingdom.spacexmvi

class MainPresenter(private val mainInteractor: MainInteractor) {

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.emitButtonClick()
                .flatMap { mainInteractor.fetchRocketList().startWith(PartialMainViewState.ProgressState()) }

        buttonClickObservable.subscribe { mainView.render(reduce(it)) }
    }

    private fun reduce(partialState: PartialMainViewState): MainViewState {
        return when (partialState) {
            is PartialMainViewState.ProgressState -> MainViewState(progress = true)
            is PartialMainViewState.ErrorState -> MainViewState(error = true)
            is PartialMainViewState.ListFetchedState -> MainViewState(rocketList = partialState.rocketList)
        }
    }
}