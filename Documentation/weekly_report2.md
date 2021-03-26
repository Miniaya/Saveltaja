# Weekly report 2
## What did I achieve this week?

This week I searched some training data and made a rouch version of the whole program. Every push runs tests automatically (GitHub actions enabled), test coverage can be seen in README.md and some kind of checkstyle is enabled. I also translated the documentation to english, because I did not want to write the code in finnish.

## Progress of the program

The main functionality is ready. The program can read the training data from file, make a trie of substrings of given length k and the note(s) following a certain substring, create melody by generating k random notes and checking the next possible note from the trie, translate notes compatible with .ly format and make a file containing these notes. This is done by using Java libraries. Majority of the code is also unit tested and commented using JavaDoc.

## What did I learn this week?

I learned that the program and logic behind the program was not that as hard as I imagined last week. I also did a lot of googling, so I guess I got better with seeking information too.

## Difficulties

I had some problems with checkstyle, but it is now working correctly. I also noticed that Service class is a little difficult to test, but I think it will become easier when I create my own data structures and test them instead. I did not find convenient training data, so I decided to write my own instead. It currently has only four songs, but it was enough to test the program.

## What next?

Next I should find more notes for my training data. I could also expand the algorithm so that it can also decide the duration of the note based on training data and start doing my own data structures.

### Used hours

I used about 16 hours working on the project
