package co.untitledkingdom.spacexmvi

class MainPresenter(private val mainInteractor: MainInteractor) {

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.emitButtonClick()
                .flatMap { mainInteractor.fetchRocketList() }
    }
}