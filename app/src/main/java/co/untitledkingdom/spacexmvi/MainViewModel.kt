package co.untitledkingdom.spacexmvi

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel(private val mainInteractor: MainInteractor = MainInteractor()) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val stateSubject = BehaviorSubject.create<PartialMainViewState>()

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.emitButtonClick()
                .flatMap { mainInteractor.fetchRocketList().startWith(PartialMainViewState.ProgressState()) }

        val mergedIntentsObservable = Observable.merge(listOf(buttonClickObservable)).subscribeWith(stateSubject)
        compositeDisposable.add(mergedIntentsObservable.scan(MainViewState(), this::reduce).subscribe { mainView.render(it) })
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