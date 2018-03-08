package co.untitledkingdom.spacexmvi

import io.reactivex.Observable

class MainPresenter(private val mainInteractor: MainInteractor) {

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.emitButtonClick()
                .flatMap { mainInteractor.fetchRocketList().startWith(PartialMainViewState.ProgressState()) }

        val mergedIntentsObservable = Observable.merge(listOf(buttonClickObservable))
        mergedIntentsObservable.scan(MainViewState(), this::reduce).subscribe { mainView.render(it) }
    }

    private fun reduce(previousState: MainViewState, partialState: PartialMainViewState): MainViewState {
        return when (partialState) {
            is PartialMainViewState.ProgressState -> MainViewState(progress = true)
            is PartialMainViewState.ErrorState -> MainViewState(error = true)
            is PartialMainViewState.ListFetchedState -> MainViewState(rocketList = partialState.rocketList)
        }
    }
}