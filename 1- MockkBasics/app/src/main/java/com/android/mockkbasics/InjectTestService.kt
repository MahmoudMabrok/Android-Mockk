package com.android.mockkbasics

/*
It is possible to use MockK annotations to create all kind of mocks.
Let’s create a service that requires two instances of our TestableService:
 */
class InjectTestService {
    lateinit var service1: TestableService
    lateinit var service2: TestableService

    fun invokeService1(): String {
        return service1.getDataFromDb("Test Param")
    }
}

/*
InjectTestService contains two fields with the same type.
It won’t be a problem for MockK. MockK tries to match properties by name,
then by class or superclass. It also has no problem with injecting objects into private fields.
 */