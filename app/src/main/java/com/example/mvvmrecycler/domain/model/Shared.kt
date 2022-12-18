package com.example.mvvmrecycler.domain.model

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class Shared(activity: Activity, key: String): SharedPreferences {

    private val keyShared: String = key

    private val sharedPreferences: SharedPreferences =
        activity.getSharedPreferences(key ,MODE_PRIVATE)

    private val sharedEditor: SharedPreferences.Editor = edit()

    fun putString(data: String){

        with(sharedEditor){

            sharedEditor.putString(keyShared,data)

            apply()

        }

    }

    fun getExtraString(): String? {

        return sharedPreferences.getString(keyShared,"")

    }

    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(p0: String?, p1: String?): String? {
        TODO("Not yet implemented")
    }

    override fun getStringSet(p0: String?, p1: MutableSet<String>?): MutableSet<String>? {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: String?, p1: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: String?, p1: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: String?, p1: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(p0: String?, p1: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit(): SharedPreferences.Editor {

       return sharedPreferences.edit()

    }

    override fun registerOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }


}