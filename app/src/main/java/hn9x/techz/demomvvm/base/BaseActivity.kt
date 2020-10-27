package hn9x.techz.demomvvm.base
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootLayoutId())
        setupView(savedInstanceState)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupView(savedInstanceState: Bundle?)
}