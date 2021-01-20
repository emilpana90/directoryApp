# directoryApp

Architecture used has four main layers:
1. UI layer
  Contains the view related classes: main activity, fragments, adapters
  Has a dependency only on presentation layer.
  Classes contain UI related logic and observe the data from LiveData 
  published by ViewModels.
  
  The navigation architecture uses Jetpack navigation and is based on a 
  single Activity that loads all the fragments.
  There are two nav graphs, one for activity and one for bottom navigation.
  
2. Presentation layer
  Contains view models based on Jetpack components.
  The VMs communicate only with domain layer UseCases, that return an Rx 
  flow. Then, the Rx flow received from UseCases is transformed to LiveData 
  using LiveDataReactiveStreams, that handle the lifecycle.
  This layer has no dependencies on UI or data layers.

3. Domain layer
  Agnostic to android related classes, has no dependencies on other layers.
  Contains UseCase classes that communicate with the data layer, through repositories, in order to retrieve data from remote or local storage.
  The UseCases get the Rx data flow from repositories and then handle the flow in order to obtain the desired business logic.

4. Data layer
  Is split in two by the remote and local packages.
  The remote package handles retrofit2 API service.
  The local package handles the room database.
