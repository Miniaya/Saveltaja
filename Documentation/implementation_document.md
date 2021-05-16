# Implementation Document

## UI

The application has a simple command line based ui. There user can decide how long Markov chains are, how long the melody is, and whether the user wants to 
export created melody to pdf or musicxml form. It also has an option to open the musicxml file in MuseScore, but that is mainly for demonstration of the program.

## Creating the melody

* Finds and saves melody in substrings. Length is decided by user
* Creates the melody by using the substrings to decide, which note comes next
* Translates the melody and saves it to .ly file
* Saves the melody to the file
* If user wanted to, the pdf and/or musicxml is created and MuseScore opens (if musicxml file exists and user decided to open it)

## Data Structures

This project has three different data structures. These are List, Dictionary and Trie. Let's take a closer look on these three.

### List

This data structure mimics Java's ArrayList. It has these public methods:

   **add**: adds item to the List, O(1)  
   **addAll**: adds all specified items, O(n)  
   **get**: returns item from specified index, O(1)  
   **length**: returns how many items are stored  
   **subList**: returns sublist from original list, start or start and end points specified, O(end - start)  
   **find**: returns the index of the item, if found in List, O(n) in worst scenario  
   **put**: replaces the value from specified index with specified value, O(1)  
   **delete**: deletes sublist from given start to given end point, O(1)  
   **toString**: converts List to String  
   **equals**: checks if the two Lists are the same  
   
List also has one private method:

   **expandArray**: expands the List if it becomes full, O(n)

### Dictionary

This data structure vaguely resembles Java's HashMap data structure. Unlike the HashMap, Dictionary saves keys and values in Lists. Key value is always String type. Value can be anything. Dictionary has these methods:

   **put**: adds a new key-value -pair, O(2)  
   **get**: gets a value saved with specified key, O(1)  
   **keySet**: returns all stored keys, O(1)  
   **containsKey**: checks if specified key is saved, O(n) in worst case  
   **isEmpty**: tells if the Dictionary has no saved key-value -pairs, O(1)  
   **size**: returns how many key-value -pairs is saved, O(1)  
   
Dictionary was necessary, because using it makes implementing Trie much easier.

### Trie

Java does not have a trie structure on its own. It is very useful to store letters from certain words, or to save substrings from the melodies. It uses TrieNodes to save the current and the next nodes in the trie. Trie has the following methods:

   **add**: adds a new substring to the trie, O(L) L = length of the substring  
   **search**: checks if the substring is found in the trie, O(L)  
   **getLeafs**: gets next possible nodes with given prefix, O(L) L = length of the prefix  
   **getFirst**: returns one node from the top of the trie, O(1)  
   **getNext**: returns one possible node with given prefix, O(L)  

## Possible improvements

There are a couple things I would like to fix or improve:
* I Found some bug with the getNext method I was unable to fix
* Cleaner code (solid naming etc.)
* User could decide the duration of the bar (e.g. 4/4 or 3/4)
* The duration of the notes is generated separately from the pitch
* More songs to the source material
* Better bar length/size control
* Instead of asking the length of the song in notes, the program could ask the length in bars
