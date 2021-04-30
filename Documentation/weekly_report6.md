# Weekly report 6
## What did I achieve this week?

I did some finetuning on the project. I also tried a couple of ways to separate note durations from actual notes, but could not quite finish it, so I did not push it to the GitHub. Luckily there is still some time to do it. I have found that the durations are approximately correct how it is now, but it could make some variation, if the durations are generated separately from the notes themselfs.

## Progress of the program

I added the file *config.properties* to the program root, so the file containing the notes used in the program is not hard-coded and it can be changed outside of the program. I also changed the place where the notes will be generated, and made some variation to the naming. Now it is possible to save notes, and they are not overwritten. I remembered that I do not have any kind of error handling for user inputs. So I added some. Now a user can not set the duration or the length of the markov chain to be negative or non numeric. Options also do loop, so if the input is invalid, it asks for it util the input is valid. I also started working on the note durations, but could not add it to GitHub yet, because it would have destroyed the program. 

## What did I learn this week?

I learned how to save files to another location rather than the root of the project.

## Difficulties

I think that I have a pretty good idea of what my project still needs to be ready for demonstration. 

## What next?

Once I get the generating of the durations ready and working, I should finish the testing. Documentation is also incompelte, I should finish that. Before demonstration I have to try to make the jar file and hopefully generate a good song, which I can play in my demo.

### Used hours

I used about 11 hours working on the project
