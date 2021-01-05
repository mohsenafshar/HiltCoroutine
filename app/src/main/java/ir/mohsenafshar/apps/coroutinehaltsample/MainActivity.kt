package ir.mohsenafshar.apps.coroutinehaltsample

import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.taps.observe(this, { taps ->
            btn.text = taps
            pb.visibility = INVISIBLE
        })

        viewModel.userLiveData.observe(this, { dataList ->
            Log.d("Logger", dataList.toString())
        })

        btn.setOnClickListener {
            pb.visibility = VISIBLE

            viewModel.updateTaps()
            viewModel.requestData()
        }
    }
}
