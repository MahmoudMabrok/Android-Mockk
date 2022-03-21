# Android-Mockk
## Table of contents
* [1- Some useful resources related to Mockk](#1-Some-useful-resources-related-to-Mockk)
* [2- MockK](#2-MockK)

### 1-Some useful resources related to Mockk
* Documentation. https://mockk.io/
* basic features of the MockK library. https://www.baeldung.com/kotlin/mockk


### MockK
* In Kotlin, all classes and methods are final. While this helps us write immutable code, it also causes some problems during testing.

* Most JVM mock libraries have problems with mocking or stubbing final classes. Of course, we can add the “open” keyword to classes and methods that we want to mock. But changing classes only for mocking some code doesn’t feel like the best approach.

* Here comes the MockK library, which offers support for Kotlin language features and constructs. MockK builds proxies for mocked classes. This causes some performance degradation, but the overall benefits that MockK gives us are worth it.

#### Why is it better than a well known Mockito library for Kotlin?
Mockk supports some important language features within Kotlin.

##### Final by default(Classes and methods in kotlin) :
  Concerning Java, Kotlin classes (and methods) are final by default. That means Mockito requires some extra things to make it to work, whereas Mockk can    do this efficiently without any extra things.
#####  Object mocking : 
Kotlin objects mean Java statics. Mockito alone doesn’t support mocking of statics. There are the same other frameworks required with Mockito to write tests, but again Mockk provides this without any extra things. <br>
Example : <br>
 mockObject(MyObject) <br>
 every { MyObject.someMethod() } returns "Something"
 ##### Extension functions :
 Since extension functions map to Java statics, again, Mockito doesn’t support mocking them. With Mockk, you can mock them without any extra configuration.
  ##### Chained mocking :
  With Mockk you can chain your mocking, with this we can mock statements quite easily like you can see in the example below. We are using every{} block to mock methods.<br>
  val mockedClass = mockk()<br>
 every { mockedClass.someMethod().someOtherMethod() } returns "Something"
 
