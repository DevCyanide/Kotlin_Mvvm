package com.example.kotlindemo5.ui.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo5.R
import com.example.kotlindemo5.databinding.ActivityLoginBinding
import com.example.kotlindemo5.db.roomDB.RomDatabase
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Home.HomeActivity
import com.example.kotlindemo5.ui.Register.RegisterActivity
import com.example.kotlindemo5.viewmodelfactory.LoginViewModelFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener,LoginNavigation {

    private lateinit var binding :ActivityLoginBinding

     lateinit var stud_num:String
     lateinit var password:String
    lateinit var viewModel: LoginViewModel
    lateinit var context: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        context = this@LoginActivity
        val dao = RomDatabase.getInstance(application).studentsDao
        val repository = StudentRepository(dao)
        val factory = LoginViewModelFactory(repository)
        viewModel  = ViewModelProvider(this,factory).get(LoginViewModel::class.java)
        viewModel.presenter = this
        binding.btnlogin.setOnClickListener(this)
        binding.btntoregister.setOnClickListener(this)
        displayLoggedIn()
    }
    fun displayLoggedIn(){
        viewModel.isLogin?.observe(this, Observer {
            Log.i("MYTAG-isLogin",it.toString())
        })
    }
    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun Toaster(context: Context, message: String) {
        context.toast(message)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnlogin->{
                stud_num = binding.studentNum.text.toString().trim()
                password = binding.password.text.toString().trim()
                viewModel.login(stud_num,password)
            }
            R.id.btntoregister->{
                intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun loginSuccessfully(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            toast(it)
        })
        intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }


}
