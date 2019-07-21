package academy.learnprogramming.controller;

import academy.learnprogramming.service.GameService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.GameMappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.info("model={}",model);
        if (gameService.isGameOver()){
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess){
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart(Model model){
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        gameService.reset();
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.HOME)
    public String home(){
        gameService.reset();
        return GameMappings.HOME;
    }

}
//          -Create a Thymeleaf template for game-over view (name it game-over.html)
//        -In the html file add a centered div tag that contains
//        -game title in h1 tag e.g. text gGuess The Number Gameh.
//        -text gGame Overh in a h2 tag.
//        -Result message in a h3 tag (model attribute).
//        -A h3 tag with a tag (anchor) with text gPlay Againh. Clicking on Play Again link should call the controller restart method to restart the game.
//        -A h3 tag with a tag (anchor) with text Home. Clicking on the Home link should open the home page (home.html).
//        -The footer tag - same as in the play.html file (You can copy paste it).
//
//        -Add the method restart to GameController
//        -Method restart() should call gameService reset() method to reset the game and redirect to play view. It needs to be mapped to /restart.
//
//        -Check the logic is working correctly for the game
//        -After game is won or lost game-over view should be displayed.
//        -Clicking on Home link should show the home view.
//
//        TIPS:
//        -Use constants
//        -Use template preprocessing tags