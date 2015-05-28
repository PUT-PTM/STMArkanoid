# STMArkanoid

## Overview
This project combines arkanoid with micro-controller STM32f4 discovery.
This means that we are able to control our 'pipe' with accelerometer builded in micro-controller. How cool is this! :)
But, if you don't have in your garage exacly the same or none of micro-controller models- don't worry!
You can easily compile,run and play with keyboard arrows.

### Arkanoid
Arkanoid game is the most known( and old) game, after Pong, but not all of you may recognize it by the name.
So check this [Arkanoid Screenshot](http://bit.ly/ArkanoidScreenshot)
We've chosen this game, because it is simple and gives a lot of fun. 

### STM32f4
I suggest refering to [Wiki](http://en.wikipedia.org/wiki/STM32#STM32_F4)

##Instalation

If you know how, don't read this :)
Download .zip file from github and extract it somewhere..

If your IDE has build-in GRADLE, then step over next few paragraphs.
If NOT you can google for example "gradle plugin Eclipse". 
And then easily install GRADLE. 

Eclipse: You have to drag plugin from website and drop it on your already opened eclipse workspace. It will install and restart automatically.
         Note: Eclipse version has to be adequate to plugin.
         
         Then.. File -> Import -> Gradle -> Gradle Project -> (choose directory where the .zip was extracted) -> Build Model -> Press Finish or whatever to end.
         
IntelliJ: Basicly the same, but less things to do.

NetBeans: Firstly plugin, if you don't have already. ( Tools -> Plugin -> Available Plugins -> Gradle)
          Check checkbox and install, agree - easy stuff.
          File -> Open Project ->(choose directory where the file was extracted) [ It might take a while ]
          Wrong dependecies RELOAD PROJECT
           
NOTE!!!! You won't have any imports .jpg .gif .mp3, so compilation will fail!! At least until you load new or comment code that makes trouble.
         But game without texture and music is nothing, so better import.
         
         You can find solution. Just google "Couldn't load file: gradle" and then follow instructions- as you probably discovered it is only for, or mostly for 
         Eclipse IDE. What about NetBeans?! The easiest way is to do it on Eclipse and then in NetBeans "import Eclipse project".
         
         IntelliJ IDE is smart enough, you won't have such problems as mentioned before.




 

