### What is what?

### Understanding the code

### Start

This is where the application starts (you can see the `main` function is here)

Here, we setup quite a few things:

* [IOManager](#iomanager) to read the files and set up our model
* [Various Model View Controllers](#model-view-controller) this is the core meat of our application.
* [MainWindow](#mainwindow) this is the overarching JFrame
* As well as some other configurations:
    * `Runtime.getRuntime().addShutdownHook` The thread here is triggered when the application shuts down. This allows
      us to save the model before closing the app
    * Registering the panels
        * We let the window know which JPanels are available for it to display
        * Note that NOT all views must be registered - these are only the JPanels that show up as "main windows" of the
          application
    * Showing the actual window.

### IOManager

This class is in charge of managing the 'persistence' aka reading and writing data to the hard drive so that the
application can remember data even after it is closed. It uses two helper classes - MyReader and MyWriter to do the
actual reading and writing It uses GSON to help with converting Java Objects to Strings and vice versa.

### Model View Controller

Using the MVC pattern, we segregate the view (what the user sees) and the model (the data)

Here, the implementation follows the following logic:

* The models are created from read info from the disk (there are also default values). They are created initially by
  [IOManager](#iomanager) and loaded in [Start](#start)
* The views have any models that they need in order to display their data.
    * They extend `BaseView`, which has two core functions:
        * onLoad - this function is called by [MainWindow](#mainwindow) whenever the view is loaded. It is a good idea
          to refresh the model if anything has changed
        * initializeView - This should be run manually to create the view for the first time.
            * Honestly this doesn't really belong in the abstract class, but I put this here as a reminder to have a
              function to setup the view initially
* The controllers handle any actions done on the view. They register listeners to various events that might happen and
  do the appropriate actions.
    * These actions can include: editing the model, modifying the view directly (ex making various elements show up or
      disappear), and/or app-wide changes (modifying the window entirely)
        * It has access to both the view and the model in order to do its actions.
    * Here I've also implemented a `BaseController` class which all Controllers extend, just as a reminder to pass the
      main window's controller to the controller in case we need to switch views.

### MainWindow

* The main JFrame of the application.
* MainWindowController helps switch to the different views.
* These views are all registered at by [start](#start)

### Maven

So you see a `pom.xml` file. That is for managing what libraries we are using as well as a few other configurations in
the program. You only really need to know that it's used to help us import the libraries that we want to utilize such as
GSON (for converting Java Objects to strings so we can save them) and FlatLAF (the nicer looking Swing UI).

What's nice is that instead of downloading and setting up the libraries manually, by defining it in `pom.xml`, IntelliJ
or other IDEs will automatically do the configuration for you and have the libraries ready for your app when you run
them!

