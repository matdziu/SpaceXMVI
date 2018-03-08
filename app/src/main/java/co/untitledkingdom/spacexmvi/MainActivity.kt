package co.untitledkingdom.spacexmvi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.untitledkingdom.spacexmvi.list.RocketsAdapter
import co.untitledkingdom.spacexmvi.models.Rocket
import kotlinx.android.synthetic.main.activity_main.errorTextView
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.rocketsRecyclerView
import kotlinx.android.synthetic.main.activity_main.showMeRocketsButton

class MainActivity : AppCompatActivity(), MainView {

    private val rocketsAdapter = RocketsAdapter()

    private val mainPresenter: MainPresenter = MainPresenter(MainInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        mainPresenter.bind(this)

        showMeRocketsButton.setOnClickListener {
            mainPresenter.getRocketList()
        }
    }

    private fun initRecyclerView() {
        rocketsRecyclerView.layoutManager = LinearLayoutManager(this)
        rocketsRecyclerView.adapter = rocketsAdapter
    }

    override fun showProgressBar(show: Boolean) {
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

    override fun showError(show: Boolean) {
        if (show) {
            rocketsRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.VISIBLE
        } else {
            errorTextView.visibility = View.GONE
        }
    }

    override fun showRocketList(rocketList: List<Rocket>) {
        rocketsAdapter.setRocketList(rocketList)
    }
}