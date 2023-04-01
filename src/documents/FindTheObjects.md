# model
=========================

WordleOperations
 - contains the wordle dictionary
 - method to play game, take in word as parameter. 6 tries to guess the word, like normal wordle
 - allows user to practice on random words
        random word size // can select how long of a word they want to use
 - daily word will be set length, serialized
 - randomizer for the daily word, seed based on the LocalDate, so it will be the same every day

WordleSerializer
 - saves and loads your score/user + statistics
         statistics contain average guesses per word, amount of daily games played
 - first time, creates the boggle dictionary wordlist
        stored as an array of arraylists, index corresponding to the length of the word
 - serializes the boggle dictionary wordlist
 - serializes map: username -> statistics as ArrayList<String>

WordleSolver
 - automated solver for wordle, can be "AI".
 - better than brute force. should be able to solve in < 6 tries.
 - shows tries each time.


# view/controller
=========================

WordleGUI
 - on startup, ask if they want practice mode or daily mode
 - small area
 - Textarea (????) in center to display word, each letter different color (green, grey, yellow etc)
 - Textfield to enter word
 - Button to submit word
 - button to switch to practice mode (randomizes the word but stays on same screen)

OutputScreen
 - extends BorderPane
 - shows your score, saves on exit
    if logged in, show averages and past.
    if not logged in, show # of guesses and # of words. local save but cannot save until logged in. make an alert
 - shows old scores, asks if you want to play again, on practice mode.

 UsernameLogin
  - extends a pane object, so we can use it inside WordleGUI
  - allows users to login so it saves data
  - no logout button, will reset on exit
  - if no username, no data will be saved


