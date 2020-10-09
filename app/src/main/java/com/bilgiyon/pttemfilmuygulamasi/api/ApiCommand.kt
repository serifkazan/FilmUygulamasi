package com.bilgiyon.pttemfilmuygulamasi.api

interface ApiCommand<T> {
    fun onSuccess(response: T)

    fun onFailure(t: String)
}