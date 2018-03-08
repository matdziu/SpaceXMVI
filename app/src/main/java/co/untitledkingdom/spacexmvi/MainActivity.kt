package co.untitledkingdom.spacexmvi

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.untitledkingdom.spacexmvi.list.RocketsAdapter
import co.untitledkingdom.spacexmvi.models.Rocket
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.errorTextView
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.rocketsRecyclerView
import kotlinx.android.synthetic.main.activity_main.showMeRocketsButton

class MainActivity : AppCompatActivity(), MainView {

    private val rocketsAdapter = RocketsAdapter()

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        mainViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.bind(this)
    }

    override fun onStop() {
        mainViewModel.unbind()
        super.onStop()
    }

    private fun initRecyclerView() {
        rocketsRecyclerView.layoutManager = LinearLayoutManager(this)
        rocketsRecyclerView.adapter = rocketsAdapter
    }

    private fun showProgressBar(show: Boolean) {
        if (show) {
            rocketsRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            rocketsRecyclerView.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            rocketsRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.VISIBLE
        } else {
            errorTextView.visibility = View.GONE
        }
    }

    private fun showRocketList(rocketList: List<Rocket>) {
        rocketsAdapter.setRocketList(rocketList)
    }

    override fun render(mainViewState: MainViewState) {
        with(mainViewState) {
            showProgressBar(progress)
            showError(error)
            showRocketList(rocketList)
        }
    }

    override fun emitButtonClick(): Observable<Boolean> {
        return RxView.clicks(showMeRocketsButton).map { true }
    }
}