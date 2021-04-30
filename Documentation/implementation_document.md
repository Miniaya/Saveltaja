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

## Data Structures

This project has three different data structures. These are List, Dictionary and Trie. Let's take a closer look on these three.

### List

This data structure mimics Java's ArrayList. It has these public methods:

   **add**: adds item to the List  
   **addAll**: adds all specified items  
   **get**: returns item from specified index  
   **length**: returns how many items are stored  
   **subList**: returns sublist from original list, start or start and end points specified  
   **find**: returns the index of the item, if found in List  
   **put**: replaces the value from specified index with specified value  
   **delete**: deletes sublist from given start to given end point  
   **toString**: converts List to String  
   **equals**: checks if the two Lists are the same  
   
List also has one private method:

   **expandArray**: expands the List if it becomes full

### Dictionary

This data structure vaguely resembles Java's HashMap data structure. Unlike the HashMap, Dictionary saves keys and values in Lists. Key value is always String type. Value can be anything. Dictionary has these methods:

   **put**: adds a new key-value -pair  
   **get**: gets a value saved with specified key  
   **keySet**: returns all stored keys  
   **containsKey**: checks if specified key is saved  
   **isEmpty**: tells if the Dictionary has no saved key-value -pairs  
   **size**: returns how many key-value -pairs is saved  
   
Dictionary was necessary, because using it makes implementing Trie much easier.

### Trie
