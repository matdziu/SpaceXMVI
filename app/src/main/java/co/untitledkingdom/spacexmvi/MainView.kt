package co.untitledkingdom.spacexmvi

import io.reactivex.Observable

interface MainView {

    fun emitButtonClick(): Observable<Boolean>

    fun render(mainViewState: MainViewState)
}