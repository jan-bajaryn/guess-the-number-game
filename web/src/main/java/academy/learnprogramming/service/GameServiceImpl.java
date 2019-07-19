package academy.learnprogramming.service;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    private final Game game;
    private final MessageGenerator messageGenerator;

    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @PostConstruct
    public void init() {
        log.info("Main message = {}", getMainMessage());
        log.info("Value to guess = {}", game.getGuess());
    }

    @Override
    public boolean isGameOver() {
        return game.isGameWon() || game.isGameLost();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }
}


//
//-Create an interface named GameService with the following methods:
//        -isGameOver without any parameters and a return type of boolean.
//        -getMainMessage without any parameters and a return type of String.
//        -getResultMessage without any parameters and a return type of String.
//        -checkGuess with parameter int guess, it should not return any value (void).
//        -	reset without any parameters, it should not return any value (void).
//
//        -Create a class GameServiceImpl and annotate it with the @Service annotation.
//
//        -Class GameServiceImpl needs to have two final fields one of type Game and the other of type MessageGenerator. Use constructor injection to autowire the game and message generator fields.
//
//        -For the implemented methodsc
//        The method isGameOver needs to check if the game is over (won or lost).
//
//        -The methods getMainMessage and getResultMessage need to call appropriate methods from messageGenerator.
//
//        The method checkGuess should set the guess in the game instance and then call the method check also on the game instance.
//
//        -The method reset should call the reset method on the game instance.
//
//        -To test it out add an init method with the @PostConstruct annotation.  The method should log mainMessage and the number that we have to guess.
//
//        -Run the project and check that your init method is logging expected results.
