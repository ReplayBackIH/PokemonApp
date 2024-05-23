This App was made as a part of android coding challange.
It uses Kotlin as main language.
Tech stack used in app: MVVM architecture,Jetpack Compose, StateFlow for reactive data handling, Coroutines, Retrofir, Room, Coil.

The App allows to search for images by typing words into the search TextField and pressing Button for searching using Pixabay API. 
Result list is showing on main screen right under the TextField.
If user presses on the item of result list then dialog is showing asking the user if he wants to see more details. If positive, a new screen opens with detailed info.

Unfortunately, I was not able to implement all the functionality listed in the task within the allotted time, however, the fundamental and most important functionality of the application has been completed.
Steps for improvenments:
- Make app trigger string "fruits" when it starts.
- Beautify UI.
- Make TextField singlelined.
- Trigger Api call by pressing enter on screen keyboard, not only by pressing in-app button.
- Add Hilt\Dagger for dependency injection.
