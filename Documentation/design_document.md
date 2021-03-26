# Design document

### Tietojenkäsittelytieteen kandidaattiohjelma (TKT), kieli: english

The idea of Säveltäjä (composer) is to create new melodies using given notes and Markov chains.

## Data structures and algorithms to be accomplished
### Data structures

* Trie
  * Substrings and their next tones according to source material

### Algorithms

* Search algorithm
  * Finding substrings from material / trie
* Algorithm needed to construct a new melody
  * The next note depends on what the last few notes are.
  * Maybe the duration of the note

## Time- and space complexity (goal)

* Trie: 
  * Time complexity: O(log n) (Search algorithm)
  * Space complexity: O(n)
* Constructiong a melody:
  * Time complexity: O(n)?
  
 ## Inputs / Outputs
 
 ### Inputs
 
 * Melody duration (how many notes)
 * Markov chain rate
 * (key)
 
 ### Outputs
 
 * Notes in .ly format

## Sources

* [Aurora Tulilaulu - Datamusikalisaatio (Pro gradu - tutkielma](https://helda.helsinki.fi/bitstream/handle/10138/229070/Datamusikalisaatio.pdf?sequence=3&isAllowed=y)
* [Roger B. Dannenberg - Music Generation and Algorithmic Composition](https://www.cs.cmu.edu/~music/cmsip/slides/05-algo-comp.pdf)
* [David Temperley - A Probabilistic Model of Melody Perception](https://onlinelibrary.wiley.com/doi/full/10.1080/03640210701864089)
