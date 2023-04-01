# model
=========================

WordleOperations
 - contains all the methods to do the operations that wordle needs.
 - contains the wordle dictionary
 - allows user to
 - random word size // can select how long of a word they want to use
 - daily word will be set length, serialized
 - randomizer for the daily word, seed based on the LocalDate, so it will be the same every day

WordleSerializer
 - saves and loads your score/user + statistics
 - loads the daily word
 - loads random words
 - serializes the boggle dictionary wordlist - stored as an array of arraylists,
        index corresponding to the length of the word

WordleSolver
 - automated solver for wordle, can be "AI"


# view/controller
=========================

WordleGUI
 - displays everything
 - can practice on random words, random sizes

OutputScreen
 - extends BorderPane, shows your score, you can save it

