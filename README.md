# ChartIt
Design Document - ChartIt App

Introduction

Scope
Design a friendly app for musicians who write their own songs, to arrange their sheet music easily. 

Terminology
Chord - group of notes that are (usually) play at the same time. The chords are represented by alphabetic symbols, and not always by musical notes.

Chart- chords that align in a certain order, that is the backbone of any musical piece, but is most common on pop/rock current songs.

Software Design Description

General Flow
The users will design their own chart, according to their needs(tempo, chords per line, song structure). Each user will have their own collection of charts.

Software Architecture

The users can choose premade chords, or use their own, choose tempo.

User Interface
The users will have 3 basic screens:
Add chart - new chart that can be designed as they wish.
My charts - all previous charts will be saved on the user’s storage, but they will be able to download it as an image or pdf.
Contact us- I am open to criticism, and what to help users enjoy my app by helping me make it better.

Data Handling
The data that will be saved is chords, barline rehearsal marks tempo.
When the data is read, the app translates them to chords.


The charts and chords will be saved as a JSON and will be saved in the database.

Tests and Monitors(Time Permitting)

Tests
Unit tests on methods of each class. 
Automatic test on app UI- probably not in scope.
Manual testings- Beta users.

Logs
After important actions are completed, logs will be written.
After errors/warnings log will be written.
