# Movie App
A simple movie application that list all the details with latest android technologies

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.20-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-4.1.1-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5.1-blue?style=flat)](https://gradle.org)

## Project characteristics

This project brings to table set of best practices, tools, and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* Koin
* Reactive UI
* Testing (Unit)
* Material design
* CI with GitHub Actions

## Tech-stack
Min API level is set to [`21`](https://android-arsenal.com/api?level=21), so the presented approach is suitable for over
[85% of devices](https://developer.android.com/about/dashboards) running Android. This project takes advantage of many
popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version unless there is a
good reason to use non-stable dependency.

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)  - For synchronous and reactive programming.
    * [Retrofit](https://square.github.io/retrofit/) - networking. Known to be lighter than Retrofit.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - For Storing of data
  *   [Glide](https://github.com/bumptech/glide) - image loading library with better memory management for pretty large list
* Architecture
    * Clean Architecture (at module level)
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
    * [Mockk](https://mockk.io/)
* Dependency Injection
    *Koin


## What this project does not cover?

The interface of the app utilizes some of the modern material design components, however, is deliberately kept simple to
focus on application architecture.

## Getting started


