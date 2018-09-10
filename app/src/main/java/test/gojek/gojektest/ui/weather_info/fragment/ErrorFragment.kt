package test.gojek.gojektest.ui.weather_info.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.error_screen.*
import test.gojek.gojektest.R
import test.gojek.gojektest.data.NetworkHandler
import test.gojek.gojektest.ui.base.BaseFragment

class ErrorFragment : BaseFragment() {

    var listener: OnRetryListener? = null

    interface OnRetryListener {
        fun onRetryClick()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.error_screen, container, false)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.setStatusBarColor(getResources().getColor(R.color.error_fragment_color));
        }



        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnRetryListener)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(NetworkHandler.KEY == null){
            tvErrorText.text = "Get you api key from https://www.apixu.com/"
        }

        addRetryListener()
    }

    fun addRetryListener() {
        btnRetry.setOnClickListener {
            listener?.onRetryClick()
        }
    }

}