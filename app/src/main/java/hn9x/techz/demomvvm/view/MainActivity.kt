package hn9x.techz.demomvvm.view

import android.os.Bundle
import hn9x.techz.demomvvm.R
import hn9x.techz.demomvvm.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}