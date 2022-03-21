# Mocking with Every and Verify

This is the most common scenario, in this case we want the test a class with a repository passed as argument, our goal is to verify the different behaviour of the test class with the different repository outputs.
In these samples we will use a MVP pattern divided in three parts:
* The Presenter that do the business logic (we will test it)
* The View, it’s a bridge between the producer (Presenter) and the consumer (who implements that View). For example in Android the View is implemented by an Activity where we can use the Android API to render the UI.
* A Repository class, it’s the data source, in our sample it produces an array of objects,but for example it could fetch data from an API.

## What scenarios we are testing 
1- Fetch data with return type <br>
2- Fetch data via callback<br>

