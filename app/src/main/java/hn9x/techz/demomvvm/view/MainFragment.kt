package hn9x.techz.demomvvm.view

import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hn9x.techz.demomvvm.R
import hn9x.techz.demomvvm.base.BaseFragment
import hn9x.techz.demomvvm.model.RepositoriesEntity
import hn9x.techz.demomvvm.model.UserEntity
import hn9x.techz.demomvvm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    private lateinit var viewModel: UserViewModel
    var adapter: Adapter_List? =null
    val repositoriesEntityArrayList:ArrayList<RepositoriesEntity> = ArrayList()
    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    override fun setData() {
        viewModel.getUserInfo("nguyenlinhnttu")
        viewModel.getRepositories("nguyenlinhnttu")
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun setupViewModel() {
        Log.d(TAG, "setupViewModel")
        //0.0 Config UserViewModel
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        setObserveLive(viewModel)

        //1.0  Observe showUserInfo
        val userObserver = Observer<UserEntity> { userEntity ->
            setupUserInfo(userEntity)
        }
        viewModel.showUserInfo().observe(this, userObserver)

        //2.0 Observe showRepositories
        val repoObserver = Observer<ArrayList<RepositoriesEntity>> {
            setupRepositories(it)
        }
        viewModel.showRepositories().observe(this, repoObserver)
    }

    private fun setupRepositories(it: ArrayList<RepositoriesEntity>?) {
        repositoriesEntityArrayList.clear()
        repositoriesEntityArrayList.addAll(it!!)
        adapter?.notifyDataSetChanged()
        Toast.makeText(requireContext(),"Sum :"+repositoriesEntityArrayList.size,Toast.LENGTH_SHORT).show()
    }

    private fun setupUserInfo(entity: UserEntity?) {
        tv_name_user.text = entity!!.name
    }

    override fun setupUI(view: View) {
        Log.d(TAG, getString(R.string.strX))
        adapter =  Adapter_List(activity!!, repositoriesEntityArrayList)
        list_repositories.adapter = adapter
        btn_load_data.setOnClickListener {
            viewModel.getUserInfo("nguyenlinhnttu")
            viewModel.getRepositories("nguyenlinhnttu")
        }
    }
}