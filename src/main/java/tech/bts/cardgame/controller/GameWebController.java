package tech.bts.cardgame.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.service.GameService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = "/games")
public class GameWebController {


    private GameService gameService;

    public GameWebController(GameService gameService) {
        this.gameService = gameService;
    }


    @RequestMapping(method = GET)
    public String getAllGames(){

        return buildGameList();
    }



    @RequestMapping(method = GET, path = "/{gameId}")
    public String getGameById(@PathVariable long gameId){

        Game game = gameService.getGameById(gameId);

        String result = "<a href=\"/games\">Go back to the games</a>";


        result += "<h1> Game "+ game.getId()+ "</h1>";
        result += "<p> State: "+ game.getState() + "</p>";
        result += "<p> Players: "+ game.getPlayerNames() + "</p>";

        if (game.getState() == Game.State.OPEN){
            result += "<a href=\"/join/{gameId}\">Join the game</a>";
        }

        return result;
    }

    /*
    @RequestMapping(method = GET, path = "/create")
    public String createGame(){

        Game game = gameService.createGame();

        return buildGameList();
    }
    */


    @RequestMapping(method = GET, path = "/create")
    void createGame(HttpServletResponse response) throws IOException {

        Game game = gameService.createGame();

        response.sendRedirect("/games");

    }


    private String buildGameList(){

        String result = "<h1>List of games</h1>";

        result += "<h2><a href=\"/games/create\">Create game</button></h2>";

        List<Game> games = gameService.getAllGames();



        for (Game game : games) {

            result += "<ul style=\"list-style-type:none\"><a target=\"_blank\"  href=\"/games/"
                    + game.getId()+ "\">Game "+ game.getId()+ "</a> is " +
                    game.getState()+ ": " + "</ul>";
        }

        return result;
    }

    @RequestMapping(method = GET, path = "/join/{gameId}")
    public void joinGame(@PathVariable long gameId, HttpServletResponse response) throws IOException {


        gameService.joinGame(new GameUser(gameId,"ferran"));

        response.sendRedirect("/games/{gameId}");

    }

}
