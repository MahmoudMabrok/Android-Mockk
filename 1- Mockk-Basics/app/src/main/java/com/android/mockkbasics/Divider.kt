package com.android.mockkbasics

class Divider {
    fun divide(a: Int, b: Int) = a / b

    fun call1(a: Int, b: Int) : Divider{
        return this
    }

    fun call2(a: Int, b: Int) = a / b

}