# Rick And Morty app

A self-study app.

## Architecture
The project is really small and doesn't have so many features. So it's created
with a single app module.

There are 3 main layers as suggested by Google:
>- Data Layer: To abstract accessing the data and fetch it from the remote service. 
Contains repository implementations and DTOs and mappers to map from DTO to Domain Model.

>- Domain Layer: Contains usecases, which is generally there to handle 
functionalities that is specific to our business models. Unfortunately 
this project doesn't have much functionality so the usecases are really simple.
Also contains domain models for ui and repository interfaces. Independent from 
any platform framework.

>- UI Layer: Contains the single activity, navigation component to handle 2 screens.
Using Jetpack Compose to create views, and viewmodels to keep the state of the views.

## Testing

>- Unit tests are created to cover logic inside viewmodels. 
>- Android tests are created to verify that the composables create the ui and displays 
as expected.