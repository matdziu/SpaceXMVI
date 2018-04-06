package co.untitledkingdom.spacexmvi

import co.untitledkingdom.spacexmvi.models.Rocket
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class MainViewModelTest {

    private val mainInteractor: MainInteractor = mock()

    init {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testListFetchingSuccess() {
        val fetchedRocketList = listOf(Rocket("testRocketName", "url/to/pic"))
        whenever(mainInteractor.fetchRocketList()).thenReturn(
                Observable.just(PartialMainViewState.ListFetchedState(fetchedRocketList))
        )

        val mainViewModel = MainViewModel(mainInteractor)
        val mainViewRobot = MainViewRobot(mainViewModel)

        mainViewRobot.startView()
        mainViewRobot.emitButtonClick()
        mainViewRobot.stopView()
        mainViewRobot.startView()

        mainViewRobot.assertViewStates(
                MainViewState(),
                MainViewState(progress = true),
                MainViewState(rocketList = fetchedRocketList),
                MainViewState(),
                MainViewState(rocketList = fetchedRocketList)
        )
    }

    @Test
    fun testListFetchingError() {
        whenever(mainInteractor.fetchRocketList()).thenReturn(
                Observable.just(PartialMainViewState.ErrorState())
        )

        val mainViewModel = MainViewModel(mainInteractor)
        val mainViewRobot = MainViewRobot(mainViewModel)

        mainViewRobot.startView()
        mainViewRobot.emitButtonClick()
        mainViewRobot.stopView()
        mainViewRobot.startView()

        mainViewRobot.assertViewStates(
                MainViewState(),
                MainViewState(progress = true),
                MainViewState(error = true),
                MainViewState(),
                MainViewState(error = true)
        )
    }
}