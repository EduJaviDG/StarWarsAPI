package com.example.mvvmrecycler.resource

import java.lang.Exception


    sealed class Resource<out R> {

        data class Success <out R> (val result: R): Resource<R>()

        data class Error (val exception: Exception): Resource<Nothing>()

        object inProgress: Resource<Nothing>()

    }
