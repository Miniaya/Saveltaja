# User Manual

## Running the program

The program can be run from command line using command `./gradlew run --console=plain` in its root folder. The program assumes the file *confic.properties* can be found in the root containing information of the file containing the source material.
In my case, that file is *notes.csv*, which is also located in root folder. The program can also be run with command `java -jar Saveltaja.jar` if the .jar file is in the root folder.

## Using the program (inputs and outputs)

First the program asks for the length of the Markov chain. It cannot be negative or zero. The greater the length, more the created melody represents the source material

Next the user is asked to give the length of the melody. This cannot be negative or zero either. However, this can be lesser than the length of the Markov chain.

Then comes three yes or no -questions. Each of them requires some pre-downloaded software to work. First one is whether the user wants to create a .pdf file from the created .ly file. This requires Lilypond in order to work.
The second one is about creating a .musicxml file from the .ly file. This needs Python -ly in order to work. If the user did want to create the .musicxml file, there is an option to open the file in MuseScore. This needs MuseScore to be installed.

The program then tells the user the name of the file it just created. All created files can be found from the folder created_files
