package ir.mohsenafshar.apps.coroutinehaltsample

import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerPresenterFactory.create().inject(this)

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
