package com.github.schaka.native_example


interface ServarrService {

    fun getEntries(): List<Int>

    fun removeEntries(items: List<Int>)

}