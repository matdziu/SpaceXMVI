package co.untitledkingdom.spacexmvi

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val mainInteractor: MainInteractor) {

    private val compositeDisposable = CompositeDisposable()

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.emitButtonClick()
                .flatMap { mainInteractor.fetchRocketList().startWith(PartialMainViewState.ProgressState()) }

        val mergedIntentsObservable = Observable.merge(listOf(buttonClickObservable))
        compositeDisposable.add(
                mergedIntentsObservable.scan(MainViewState(), this::reduce).subscribe { mainView.render(it) }
        )
    }

    fun unbind() {
        compositeDisposable.clear()
    }

    private fun reduce(previousState: MainViewState, partialState: PartialMainViewState): MainViewState {
        return when (partialState) {
            is PartialMainViewState.ProgressState -> MainViewState(progress = true)
            is PartialMainViewState.ErrorState -> MainViewState(error = true)
            is PartialMainViewState.ListFetchedState -> MainViewState(rocketList = partialState.rocketList)
        }
    }
}