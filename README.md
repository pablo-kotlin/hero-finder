# Marvel Superheroes App

<p align="center">
  <img width="300" alt="Logo" src="https://github.com/pablo-kotlin/hero-finder/assets/128930557/c19cbd56-f9e9-4630-864d-c8dc52a36c9b">
</p>

Welcome to the **Hero Finder: Marvel Edition**, a project developed in Kotlin and designed to help you find your favorite Marvel superheroes and learn more about them. This application utilizes the [Marvel API](https://developer.marvel.com/) to fetch superhero data.

## Overview

Marvel Superheroes App is an Android application designed to help users explore and learn more about their favorite Marvel superheroes. The application is developed using modern Android development tools and practices including the Model-View-ViewModel (MVVM) architectural pattern, LiveData, Kotlin, Retrofit and Firebase Authentication.

## Features

1. Firebase Authentication: The application employs Firebase Authentication to manage users. This offers a secure and reliable user authentication method. 

2. Search Marvel superheroes by their names. 

3. View detailed information of individual superheroes, including their biography, abilities, and stats.

4. The application fetches data from an external API, ensuring it is always up-to-date with the latest superhero information.

## Screenshots

<p align="center">
  <img width="300" alt="Screenshot_20220609_211305" src="https://github.com/pablo-kotlin/hero-finder/assets/128930557/46f84576-12e0-4cc8-9b8c-2e5d040f2e90">
</p>
<p align="center">
  <img width="300" alt="Screenshot_20220609_211305" src="https://github.com/pablo-kotlin/hero-finder/assets/128930557/59a1da2a-b2f1-4097-88d6-07c9bd536405">
</p>
<p align="center">
  <img width="300" alt="Screenshot_20220609_211305" src="https://github.com/pablo-kotlin/hero-finder/assets/128930557/025d7ed3-9331-4c0b-a903-1a2202079435">
</p>
<p align="center">
  <img width="600" alt="Screenshot_20220609_211305" src="https://github.com/pablo-kotlin/hero-finder/assets/128930557/fd915c85-c07a-4f92-ab52-51bd5e71aa87">
</p>

## Technical Overview

### Architecture

The app follows the **Model-View-ViewModel (MVVM)** architectural pattern. This ensures a scalable and maintainable codebase by decoupling the components of the application, facilitating unit testing, and providing lifecycle-aware data handling. 

- **Model:** This represents the data and business logic of the app. It includes data classes, repositories, and network calls.
- **View:** These are the visual components of the app, which display the data from the Model to the user. It includes activities, fragments, and adapters.
- **ViewModel:** ViewModel acts as a bridge between Model and View. It handles the data processing and provides data to the View.

### Firebase Authentication

The app uses Firebase Authentication for user login functionality. Firebase provides a full set of authentication options, but this application utilizes email and password login for simplicity.

### Testing

Unit tests and Android Instrumentation tests are written to ensure the functionality of the application and the MVVM architecture. The testing libraries used include JUnit, Espresso and Mockito.

## Future Work

Some features to consider for future development could include:

1. An option for users to mark their favorite superheroes and access these favorites from a dedicated screen.
2. The inclusion of additional superhero data, such as images, videos, or associated merchandise.
3. Push notifications to alert users of new information or updates related to their favorite superheroes.

Thank you for checking out the Marvel Superheroes App! Contributions and feedback are always welcome.
