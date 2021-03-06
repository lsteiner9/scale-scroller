## Current implementation state

Currently, all screens are set up, with custom yet intuitive navigation. Data from the app is being stored in a SQLite database, and certain elements (scales, high scores, players) are displayed in the UI. The app uses Google Sign In and Media Player, as external or device-based services. Settings are stored in shared preferences for use in the app. The gameplay is not working (the game screen uses a mockup layout and very simple "gameplay"), but aside from that, the app looks and works generally as intended.

Functional issues:

* The "Continue as guest" button does not yet function. Signing in through Google is currently the only way to access the app's content.

* MediaPlayer audio adjustment is not working completely, though the audio toggle does work. The seekbar seems to be acting like a toggle - if set to 0, there is no sound. If set to anything about 0, there is full volume.

* The function for getting correct notes for each scale is not always working. It works better (though not perfectly) for scales with many accidentals.

* The scale/level information is maintained even after going to the title screen, so challenge mode never displays a new scale when clicking it after the first time. To see the randomness of challenge mode's scale picker, the app must be restarted.

* As mentioned above, gameplay is not functional, and even with the mockup, an end game state cannot be reached.

Aesthetic/cosmetic issues:

* Make new custom sound effects for game elements (starting, picking up coins, winning, etc.).

* Get scale notes to display in order, i.e. always starting and ending with the tonic.

* Change font style to match the music note icon.

* Create a soundtrack to play through MediaPlayer.

* Allow the user to unlock new playable characters (whole note, quarter note, dotted notes, rests, etc.) or different character colors as they progress through the game.

Functional stretch goals:

* Listen mode: allow the user to control the character not by up/down on the screen, but by accessing the microphone. The user can use their instrument or voice to play the notes required to collect the correct coins.

    * Include transposition settings for clarinet, etc.
  
* Allow the user to input their own custom collections (octatonic, wholetone, jazz modal scales, etc.).

* Chord mode: collections of correct notes are smaller (three or four possibilities each), “levels” are much shorter, and transitions between levels are very fast. This trains the user to quickly switch between chords.
  
* Rhythm mode: instead of a pitch collection, the user is shown a time signature. They must collect coins that fill up a measure's worth of rhythmic values, then collect a barline coin, then repeat.

[*Return to previous page*](index.md#current-implementation-state) 