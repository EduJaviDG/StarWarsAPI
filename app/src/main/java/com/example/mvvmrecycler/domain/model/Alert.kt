package com.example.mvvmrecycler.domain.model

import android.app.AlertDialog
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.mvvmrecycler.R

abstract class Alert {

    companion object{

        fun showWelcome(context: Context){

            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)

            builder.setTitle("Welcome")

            builder.setMessage("Let's start, enter a valid name from the Star Wars " +
                    "trilogy in the search engine.")

            builder.setPositiveButton("Accept",null)

            val dialog = builder.create().apply {

                val backgroundDrawable = ContextCompat.getDrawable(context,R.drawable.alert_background)

                window?.setBackgroundDrawable(backgroundDrawable)

            }

            dialog.show()

            val colorText = ContextCompat.getColor(context,R.color.gold)

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorText)

        }

        fun showError(context: Context){

            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)

            builder.setTitle("Error")

            builder.setMessage("Please, enter a valid name from the Star Wars trilogy")

            builder.setPositiveButton("Accept",null)

            val dialog = builder.create().apply {

                val backgroundDrawable = ContextCompat.getDrawable(context,R.drawable.alert_background)

                window?.setBackgroundDrawable(backgroundDrawable)

            }

            dialog.show()

            val colorText = ContextCompat.getColor(context,R.color.gold)

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorText)



        }

    }

}