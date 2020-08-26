package com.example.kotlindemo5.ui.Register

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
import com.example.kotlindemo5.databinding.ActivityMainBinding
import com.example.kotlindemo5.db.roomDB.RomDatabase
import com.example.kotlindemo5.repository.StudentRepository
import com.example.kotlindemo5.ui.Login.LoginActivity
import com.example.kotlindemo5.viewmodelfactory.StudentViewModelFactory

class RegisterActivity : AppCompatActivity(), View.OnClickListener ,RegisterNavigation{

    private lateinit var binding: ActivityMainBinding
    lateinit var context: Context;
    lateinit var registerViewModel: RegisterViewModel
    lateinit var stud_num: String
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context=this@RegisterActivity
        val dao = RomDatabase.getInstance(application).studentsDao
        val repository = StudentRepository(dao)
        val factory = StudentViewModelFactory(repository)
        registerViewModel = ViewModelProvider(this,factory).get(RegisterViewModel::class.java)
        registerViewModel.presenter=this
        binding.btnregister.setOnClickListener(this)
        binding.btntologin.setOnClickListener(this)
        displayData()
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun Toaster(context: Context, message: String) {
        context.toast(message)
    }
    fun displayData(){
        registerViewModel.students.observe(this, Observer {
            Log.i("MYTAG-AllStudent",it.toString())
        })
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnregister->{
                stud_num = binding.studNum.text.toString().trim()
                firstname = binding.firstname.text.toString().trim()
                lastname = binding.lastname.text.toString().trim()
                password = binding.password.text.toString().trim()
                if (stud_num.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
                    Toaster(context, "Empty Fields")
                }
                else {
                    registerViewModel.insertData(stud_num,firstname,lastname,password)
                }
            }
            R.id.btntologin->{
               intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSuccessRegister(registerResponse: LiveData<String>) {
       registerResponse.observe(this, Observer {
        toast(it)
       })
    }
}
