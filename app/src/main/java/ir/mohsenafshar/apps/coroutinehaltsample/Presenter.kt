package ir.mohsenafshar.apps.coroutinehaltsample

import android.util.Log
import javax.inject.Inject

class Presenter @Inject constructor(private var view: MyView): SuperPresenter {
    override fun prepareView() {
        view.createViewWithType()
    }
}

interface SuperPresenter{
    fun prepareView()
}

class MyView @Inject constructor(private var type: MyType) {
    fun createViewWithType() {
        Log.d(this::class.java.simpleName, type.getName())
    }
}

class MyType @Inject constructor(){
    fun getName(): String {
        return "SimpleViewType"
    }
}