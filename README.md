# CAT API DEMO


"A sample app to showcase the CAT API, built in Kotlin using Jetpack Compose and architecture components, following Clean Code principles and modularization."


![download (2)](https://github.com/user-attachments/assets/5a91a029-e5da-47d5-9da5-ae95b4313fa9)

![download (1) (1)](https://github.com/user-attachments/assets/90d52518-0035-4188-8324-2c7717ee4fb9)


## PROJECT SPECIFICATION

### TECH STACK
* Kotlin
* Jetpack Compose for the UI
* Hilt for DI
* Coroutines and Flow - for making asynchronous calls
* Retrofit - for networking
* Room -for local caching
* mockk - for unit testing
* Coil - Image Loader library.

### API
* [https://hp-api.onrender.com/](https://thecatapi.com/)
  
### FEATURES
* Cat Breed List- Lists all the cats breeds with image and breed name and option to add favourite
* Favourite List - Lists all the cats breeds added to favourite by the user
* Cat Breed  Details - Displays the details of a selected cat including its breed name,country of orgin , life span etc
* Add/Remove Favourite  - Breeds can be added/removed to favourites from Breed List or Breed details 
  
  
### ARCHITECTURE & DESIGN PATTERN
* SOLID PRINCIPLE - The app follows SOLID design principles to ensure scalability and maintainability.
* CLEAN CODE ARCHITECTURE -The app's architecture is designed to be clean, separating concerns into distinct layers (e.g., UI, Domain, Data).
* MODULAR - The app codebase is modularized by layers (UI, Data, Domain), with the Domain module being independent of the other modules.
* DESIGN PATTERN - Application is developed using Test-Driven Development (TDD) and follows the Model-View-ViewModel (MVVM) design pattern










