package co.untitledkingdom.spacexmvi

class MainPresenter(private val mainInteractor: MainInteractor) {

    private lateinit var mainView: MainView

    fun bind(mainView: MainView) {
        this.mainView = mainView
    }

    fun getRocketList() {
        mainView.showProgressBar(true)
        mainInteractor.fetchRocketList().subscribe(
                {
                    mainView.showProgressBar(false)
                    mainView.showRocketList(it)
                },
                {
                    mainView.showProgressBar(false)
                    mainView.showError(true)
                })
    }
}