package co.untitledkingdom.spacexmvi

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertEquals

class MainViewRobot(private val mainViewModel: MainViewModel) {

    private val renderedStates = arrayListOf<MainViewState>()

    private val buttonClickSubject = PublishSubject.create<Boolean>()

    private val mainView = object : MainView {

        override fun emitButtonClick(): Observable<Boolean> = buttonClickSubject

        override fun render(mainViewState: MainViewState) {
            renderedStates.add(mainViewState)
        }
    }

    fun assertViewStates(vararg expectedStates: MainViewState) {
        assertEquals(expectedStates.toList(), renderedStates)
    }

    fun startView() {
        mainViewModel.bind(mainView)
    }

    fun stopView() {
        mainViewModel.unbind()
    }

    fun emitButtonClick() {
        buttonClickSubject.onNext(true)
    }
}