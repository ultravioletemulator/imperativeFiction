Imperative Fiction "Space was not the final frontier"
-----------------------------------------------------



Is a Cross Platform Java Interactive Fiction game running engine currently supporting tads2 games using Jetty engine.


Project is available on github:
    https://github.com/ultravioletemulator/imperativeFiction



Project Jetty's website:
    http://inky.org/if/jetty/

    The code downloaded was old and didn't work due to dependencies with already disappeared projects, such as Jakarta regexp project.
    The regexp engine has now been changed to Jdk's own.
    Some work has been done to provide console support instead of jetty's applet support.


Z-code games are supported using Zplet project

GLULX engine provided By Zag



Build
-----

Requirements
    Jdk is needed to be installed and working on your system in order to run the application.
    Maven is used to compile the sources

Program can be built using standard Maven compilation


mvn clean compile assembly:single  package -DskipTests




Game Execution
--------------

java -jar target/imperativeFiction-1.0.alpha-SNAPSHOT.jar path/to/gameFile.gam





Games
-----

To get games I recomend the Interactive Fiction Archive for those who aren't familiar with it.

http://www.ifarchive.org/

Lots of games are available on this site in many different formats, including tads2.



