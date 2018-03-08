package co.untitledkingdom.spacexmvi

import co.untitledkingdom.spacexmvi.models.Rocket
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainInteractor {

    fun fetchRocketList(): Observable<PartialMainViewState> {
        val emitError = false
        return Observable
                .timer(2000, TimeUnit.MILLISECONDS)
                .map {
                    if (emitError) PartialMainViewState.ErrorState()
                    PartialMainViewState.ListFetchedState(
                            listOf(
                                    Rocket("Falcon 1", "http://www.spacex.com/files//assets/img/Liftoff_south_FULL_QQ9L7636-480x723.jpg"),
                                    Rocket("Falcon 1e", "http://michaelgr.wordpress.com/files/2008/09/spacex-falcon1.jpg"),
                                    Rocket("Falcon 9 v1.0", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/SpX_CRS-2_launch_-_further_-_cropped.jpg/954px-SpX_CRS-2_launch_-_further_-_cropped.jpg"),
                                    Rocket("Falcon 9 v1.1", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Falcon_9_launch_with_DSCOVR.jpg/1024px-Falcon_9_launch_with_DSCOVR.jpg"),
                                    Rocket("Falcon 9 Full Thrust", "https://upload.wikimedia.org/wikipedia/commons/6/60/ORBCOMM-2_%2823802549782%29.jpg"),
                                    Rocket("Falcon Heavy", "https://i.wpimg.pl/O/593x450/d.wpimg.pl/525385494--961962968/spacex.png"),
                                    Rocket("BFR", "https://upload.wikimedia.org/wikipedia/en/9/90/SpaceX_BFR_launch_vehicle.jpg"))
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())
                as Observable<PartialMainViewState>
    }
}