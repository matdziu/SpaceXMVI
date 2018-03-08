package co.untitledkingdom.spacexmvi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.untitledkingdom.spacexmvi.list.RocketsAdapter
import kotlinx.android.synthetic.main.activity_main.rocketsRecyclerView

class MainActivity : AppCompatActivity() {

    private val rocketsAdapter = RocketsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        MainInteractor().fetchRocketList()
                .subscribe { rocketsAdapter.setRocketList(it) }
    }

    private fun initRecyclerView() {
        rocketsRecyclerView.layoutManager = LinearLayoutManager(this)
        rocketsRecyclerView.adapter = rocketsAdapter
    }
}