package com.example.kotlindemo5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo5.R
import com.example.kotlindemo5.model.Students

class StudentAdapter(val studentList: List<Students>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val students: Students = studentList.get(position)
        holder.student_num.setText(students.student_number)
        holder.firstname.setText(students.firstname)
        holder.lastname.setText(students.lastname)

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//      var student_num: TextView = itemView.findViewById(R.id.card_student_num)
        var student_num : TextView = itemView.findViewById(R.id.card_student_num)
        var firstname: TextView = itemView. findViewById(R.id.card_firstname)
        var lastname: TextView = itemView.findViewById(R.id.card_lastname)
//
//        init {
//            username = itemView.findViewById(R.id.username)
//            fullname = itemView.findViewById(R.id.fullname)
//            image_profile = itemView.findViewById(R.id.profile_pic)
//            btnFollow = itemView.findViewById(R.id.btnfollow)
//        }
    }

}