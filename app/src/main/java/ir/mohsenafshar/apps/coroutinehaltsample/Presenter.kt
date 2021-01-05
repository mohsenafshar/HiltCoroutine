package ir.mohsenafshar.apps.coroutinehaltsample

import android.util.Log

class Presenter(private var view: MyView) : SuperPresenter {
    override fun prepareView() {
        view.createViewWithType()
    }
}

interface SuperPresenter {
    fun prepareView()
}

class MyView(private var type: MyType) {
    fun createViewWithType() {
        Log.d(this::class.java.simpleName, type.getName())
    }
}

class MyType {
    fun getName(): String {
        return "SimpleViewType"
    }
}