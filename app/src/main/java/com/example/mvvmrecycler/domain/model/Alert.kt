package com.example.mvvmrecycler.domain.model

import android.app.AlertDialog
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.mvvmrecycler.R

abstract class Alert {

    companion object {

        fun showWelcome(context: Context) {

            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)

            builder.setTitle(R.string.welcome)

            builder.setMessage(R.string.welcome_message)

            builder.setPositiveButton("Accept", null)

            val dialog = builder.create().apply {

                val backgroundDrawable =
                    ContextCompat.getDrawable(context, R.drawable.alert_welcome)

                window?.setBackgroundDrawable(backgroundDrawable)

            }

            dialog.show()

            val colorText = ContextCompat.getColor(context, R.color.gold)

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorText)

        }

        fun showError(context: Context) {

            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)

            builder.setTitle(R.string.error)

            builder.setMessage(R.string.error_message)

            builder.setPositiveButton("Accept", null)

            val dialog = builder.create().apply {

                val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.alert_error)

                window?.setBackgroundDrawable(backgroundDrawable)

            }


            dialog.show()

            val colorText = ContextCompat.getColor(context, android.R.color.holo_red_light)

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorText)


        }


        fun showConnect(context: Context) {

            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)

            builder.setTitle(R.string.error)

            builder.setMessage(R.string.error_connect)

            builder.setPositiveButton("Accept", null)

            val dialog = builder.create().apply {

                val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.alert_error)

                window?.setBackgroundDrawable(backgroundDrawable)

            }


            dialog.show()

            val colorText = ContextCompat.getColor(context, android.R.color.holo_red_light)

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorText)


        }

    }

}