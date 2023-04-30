# model
=========================

Wordle
 - contains the wordle dictionary
 - method to play game, take in word as parameter. 6 tries to guess the word, like normal wordle
 - allows user to practice on random words
        random word size // can select how long of a word they want to use
 - daily word will be set length, serialized
 - randomizer for the daily word, seed based on the LocalDate, so it will be the same every day

WordleAccount
 - saves username, password, and statistics
 - knows when daily word has been played

WordleSerializer
 - saves and loads your score/user + statistics
         statistics contain average guesses per word, amount of daily games played
 - first time, creates the boggle dictionary wordlist
        stored as an array of arraylists, index corresponding to the length of the word
 - serializes the boggle dictionary wordlist
 - serializes map: username -> statistics as ArrayList<String>

Leaderboard
 - saves the top users by amount of wordles completed

# view/controller
=========================

WordleGUI
 - start on daily mode, if user completed daily, move to practice mode
 - login pane
 - buttons that flip to display the words
 - keyboard to show guesses
 - menubar to change mode, dark mode, practice mode, leaderboard, scores

Keyboard
 - has buttons to display a keyboard with colored letters, to tell which
 letters have been guessed incorrectly, correctly, or incorrect location

OutputScreen
 - shows as an alert
 - shows distribution of the scores

LeaderboardGUI
 - seperate javafx application to show top users of all time

 UsernameLogin
  - extends a pane object, so we can use it inside WordleGUI
  - allows users to login so it saves data
  - no logout button, will reset on exit
  - if no username, no data will be saved

 WordleConsole
  - console view of wordle. to test before gui is implemented


