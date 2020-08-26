package com.example.kotlindemo5.ui.Home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.kotlindemo5.R
import com.example.kotlindemo5.adapter.StudentAdapter
import com.example.kotlindemo5.databinding.ActivityLoginBinding
import com.example.kotlindemo5.databinding.HomeActivityBinding
import com.example.kotlindemo5.db.roomDB.RomDatabase
import com.example.kotlindemo5.model.Students
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Login.LoginViewModel
import com.example.kotlindemo5.viewmodelfactory.HomeViewModelFactory
import com.example.kotlindemo5.viewmodelfactory.StudentViewModelFactory
import kotlinx.android.synthetic.main.home_activity.*


class HomeActivity : AppCompatActivity(),HomeNavigation {
    private lateinit var binding : HomeActivityBinding
    private lateinit var studentAdapter: StudentAdapter
    lateinit var viewModel: HomeViewModel
    lateinit var context: Context;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity)
        val dao = RomDatabase.getInstance(application).studentsDao
        val repository = StudentRepository(dao)
        val factory = HomeViewModelFactory(repository)
        viewModel  = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        viewModel.presenter = this
        context = this@HomeActivity
        initRecyclerView()
        viewModel.getAllStudent()

    }
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun Toaster(context: Context, message: String) {
        context.toast(message)
    }
    private fun initRecyclerView(){
       binding.recycleView.layoutManager = LinearLayoutManager(this)
    }

    override fun getAllData(data: LiveData<List<Students>>) {
    data.observe(this, Observer {
       binding.recycleView.adapter= StudentAdapter(it)
   })
    }

}
