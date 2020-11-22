package edu.mahmoud_mabrouk.happy_marry.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.mahmoud_mabrouk.happy_marry.R
import edu.mahmoud_mabrouk.happy_marry.util.Logger
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment(layoutID: Int) : Fragment(layoutID) {

    val bag = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
    }

    abstract fun initViews()
    open fun loadData() {}

    open fun handleError(message: String?) {
        Logger.log("Error $message")
        if (message?.contains("resolve") == true ||
            message?.contains("connect") == true
        ) {
            Toast.makeText(requireContext(), "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(requireContext(), "حدث خطأ", Toast.LENGTH_SHORT).show()

    }


    open fun show(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.mainContainer, fragment)
            ?.addToBackStack(null)?.commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        bag.clear()
    }
}