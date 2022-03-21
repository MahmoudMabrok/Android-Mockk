package com.android.mockkbasics

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        // query database and return matching value
        return "";
    }

    fun doSomethingElse(testParameter: String): String {
        return "I don't want to!"
    }
}