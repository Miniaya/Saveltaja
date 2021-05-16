# Testing Document

The project can be tested with command `./gradlew test`. You can also generate a test report with command `./gradlew test jacocoTestReport` and open file *build/reports/jacoco/test/html/index.html*.

## What can be tested?

Almost every class has its own unit tests. Some classes are tested more	thoroughly than others.

## Manual testing & analysis

### Markov chain length

First I tested, how changing the length of the Markov chain affects on the generated melody, and how long does it take to generate one. I did that by making a loop which creates ten melodies and counted the average time it took to generate one song. The song length was 30 notes in all melodies. I tested three different situations, when only the .ly file is created, when .ly and .pdf files are created, and when the program creates .ly, .pdf and .musicxml files.

ensimmäiseen iteraatioon menee aina eniten aikaa

#### Chain length: 1

Generated melodies were very different from each other. They did not follow any kind of pattern and they did not have any similarities with source material. Pitches did vary a lot.

#### Chain length: 2

There were some a few familiar-suonding parts on the melodies, but it is most likely from randomness of the note generating. Melodies were still very random.

#### Chain length: 3

One melody was straight from the source material, which can be explained with lucky note choices. Melodies had a good flow in them, and short parts from the source material could be heard. Pitches were not going up and down on the scale anymore.

#### Chain length: 4

Similar observations than before. Recognizable parts were about three bars long and they were from songs Itsy Bitsy Spider and Tarantella Napolitana.

### Chain length: 5

Two of the melodies were straight from the source material, both from songs made for accordion, so they contain specific note combination that cannot be found in any other song from the source material. Other melodies had some similarities with the source material, but not straight copies.

#### Chain length: 6

Most of the songs were familiar and could be recognized.

#### Chain length: 7 - 8

Two of the melodies had fragments from the song Ilta Oulunjoella as well as from Pikkuetana. Almost every melody was a part of the song from the source material

#### Chain length: 9 - 10

All of the melodies were fragments from the source material. No more randomness.

Here is the chart showing the correlation between Markov chain length and time.

### Song length

Second test I did was how the length of the song affects on time it takes to generate one song. Testing method was similar to the one I did with Markov chains. Difference was that now I manipulated the song length instead of Markov chain length. In these tests the length was 3. I tested three different situations, when only the .ly file is created, when .ly and .pdf files are created, and when the program creates .ly, .pdf and .musicxml files.
