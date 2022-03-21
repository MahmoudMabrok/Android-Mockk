# Android-Mockk
## Table of contents
[1- Some useful resources related to Mockk](#1-Some-useful-resources-related-to-Mockk)
[2- MockK](#2-MockK)

### 1-Some useful resources related to Mockk
* Documentation https://mockk.io/
* https://www.baeldung.com/kotlin/mockk


### MockK
* In Kotlin, all classes and methods are final. While this helps us write immutable code, it also causes some problems during testing.

* Most JVM mock libraries have problems with mocking or stubbing final classes. Of course, we can add the “open” keyword to classes and methods that we want to mock. But changing classes only for mocking some code doesn’t feel like the best approach.

* Here comes the MockK library, which offers support for Kotlin language features and constructs. MockK builds proxies for mocked classes. This causes some performance degradation, but the overall benefits that MockK gives us are worth it.
