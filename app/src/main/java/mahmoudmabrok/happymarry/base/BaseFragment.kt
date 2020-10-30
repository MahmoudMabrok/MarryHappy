package mahmoudmabrok.happymarry.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import mahmoudmabrok.happymarry.R

abstract class BaseFragment(layoutID: Int) : Fragment(layoutID) {
    val mTag = this::class.java.simpleName

    val bag = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
    }

    abstract fun initViews()
    open fun loadData() {}

    open fun show(fragment: Fragment) {
        activity?.supportFragmentManager?.let {
            it.beginTransaction()
                .add(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        bag.clear()
    }
}