# Implementation Document

## UI

The application has a simple command line based ui. There user can decide how long Markov chains are, how long the melody is, and whether the user wants to 
export created melody to pdf or musicxml form. It also has an option to open the musicxml file in MuseScore, but that is mainly for demonstration of the program.

## Creating the melody

* Finds and saves melody in substrings. Length is decided by user
* Creates the melody by using the substrings to decide, which note comes next
* Translates the melody for ly file.
* Saves the melody to the file
* If user wanted to, the pdf and/or musicxml is created and MuseScore opens (if musicxml file exists and user decided to open it)
