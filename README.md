# Android-Mockk
## Table of contents
* [1- Some useful resources related to Mockk](#1-Some-useful-resources-related-to-Mockk)
* [2- MockK](#2-MockK)

### 1-Some useful resources related to Mockk
* Documentation. https://mockk.io/
* Basic features of the MockK library. https://www.baeldung.com/kotlin/mockk
* Mockk with MVP https://marco-cattaneo.medium.com/kotlin-unit-testing-with-mockk-91d52aea2852
* Argument matchers , expected behaviour and behaviour verification <br> https://blog.kotlin-academy.com/mocking-is-not-rocket-science-expected-behavior-and-behavior-verification-3862dd0e0f03
* Captured arguments, relaxed mocks, spies and annotations. <br> https://blog.kotlin-academy.com/mocking-is-not-rocket-science-mockk-features-e5d55d735a98
* Test Kotlin coroutines with MockK <br> https://marco-cattaneo.medium.com/how-use-and-test-kotlin-coroutines-with-mockk-library-49ddb2c9ee5f
<br> https://blog.logrocket.com/better-kotlin-coroutine-unit-testing/
 
### 2-MockK
* In Kotlin, all classes and methods are final. While this helps us write immutable code, it also causes some problems during testing.

* Most JVM mock libraries have problems with mocking or stubbing final classes. Of course, we can add the “open” keyword to classes and methods that we want to mock. But changing classes only for mocking some code doesn’t feel like the best approach.

* Here comes the MockK library, which offers support for Kotlin language features and constructs. MockK builds proxies for mocked classes. This causes some performance degradation, but the overall benefits that MockK gives us are worth it.

Why is it better than a well known Mockito library for Kotlin?<br>
Mockk supports some important language features within Kotlin.

#### 1- Final by default(Classes and methods in kotlin) :
  Concerning Java, Kotlin classes (and methods) are final by default. That means Mockito requires some extra things to make it to work, whereas Mockk can    do this efficiently without any extra things.
####  2- Object mocking : 
Kotlin objects mean Java statics. Mockito alone doesn’t support mocking of statics. There are the same other frameworks required with Mockito to write tests, but again Mockk provides this without any extra things. <br>
Example : <br>
 mockObject(MyObject) <br>
 every { MyObject.someMethod() } returns "Something"
 #### 3- Extension functions :
 Since extension functions map to Java statics, again, Mockito doesn’t support mocking them. With Mockk, you can mock them without any extra configuration.
  #### 4- Chained mocking :
  With Mockk you can chain your mocking, with this we can mock statements quite easily like you can see in the example below. We are using every{} block to mock methods.<br>
  Example : <br>
  val mockedClass = mockk()<br>
 every { mockedClass.someMethod().someOtherMethod() } returns "Something"
  #### 5- Mocking static methods
   Mocking static methods is not easy in mockito but using mockK java static methods can easily be mocked using  mockStatic.<br>
   Example : <br>
   mockkStatic(TextUtils::class)<br>
   @Test<br>
   fun validateString() {<br>
   every { TextUtils.isEmpty(param } returns true<br>
   }<br> <br>
   This is how you can mock static method isEmpty(param) of TextUtils class easily.
   
 
