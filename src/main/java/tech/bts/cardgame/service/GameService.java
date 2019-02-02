package tech.bts.cardgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.bts.cardgame.model.Card;
import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameUser;
import tech.bts.cardgame.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game createGame() {

        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();
        Game game = new Game(deck);

        gameRepo.create(game);
        return game;
    }

    public void joinGame(GameUser gameUser){

        Game game = gameRepo.getById(gameUser.getGameId());
        game.join(gameUser.getUsername());
    }

    public Card pickCard(GameUser gameUser) {

        Game game = gameRepo.getById(gameUser.getGameId());
        return game.pickCard(gameUser.getUsername());
    }

    public List<Game> getAllGames() {

        return gameRepo.getAll();
    }

    public Game getGameById(long id) {

        return gameRepo.getById(id);
    }



    // TODO When displaying the game detail, if the game is OPEN,
    // display a link to join the game. You need another end-point (method),
    // e.g. joinGame(), similar to getGameById().
    // For example, use the path "/join/{gameId}".
    // In joinGame(), since you don't have any username
    // (because we can't login in our app yet) just write your name literally,
    // like "Ferran". Then joinGame() should return the game detail
    // like getGameById(), or better, you may do a redirect (see here).
    // So, after joining the game, you will see your name there in the web page.
}