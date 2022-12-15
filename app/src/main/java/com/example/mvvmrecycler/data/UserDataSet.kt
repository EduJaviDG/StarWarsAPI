package com.example.mvvmrecycler.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmrecycler.ui.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDataSet {

   fun getUserData(): MutableLiveData<MutableList<User>>{

        val listDataFinal = MutableLiveData<MutableList<User>>()

       CoroutineScope(Dispatchers.IO).launch {
           FirebaseFirestore.getInstance().collection("Users").get()
               .addOnSuccessListener { results ->

                   val mutableListData = mutableListOf<User>()

                   results.forEach{ document ->

                       val name: String? = document.getString("name")
                       val description: String? = document.getString("description")
                       val image: String? = document.getString("image")
                       val user = User(image.toString(),name.toString(),description.toString())

                       mutableListData.add(user)
                   }

                   listDataFinal.value = mutableListData

               }

            }

        return listDataFinal

    }

}