package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Stores games in a database
 */
@Repository
public class GameRepositoryJdbc {

    private DataSource dataSource;

    public GameRepositoryJdbc() {
        this.dataSource = DataSourceUtil.getDataSourceInPath();
    }

    public void create(Game game) {

        try {



            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from games");
            String players = rs.getString("players");

            if (players != null) {
                String[] names = players.split(",");
                for (String name : names) {

                }
            }

            statement.executeUpdate("insert into games (state, players) " +
                    "values("+ game.getState()+", select value from string_split ("+ game.getPlayerNames()+", )");



        } catch (Exception e){
            throw new RuntimeException("Error creating game", e);
        }
    }

    public Game getById(long id) {

        try {

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }

            rs.close();
            statement.close();
            connection.close();

            return game;

        } catch (Exception e) {
            throw new RuntimeException("Error getting the games", e);
        }
    }

    public Collection<Game> getAll() {

        try {

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();
            statement.close();
            connection.close();

            return games;


        } catch (Exception e) {
            throw new RuntimeException("Error getting the games", e);
        }
    }

    private Game getGame(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String players = rs.getString("players");

        Game game = new Game(null);
        game.setId(id);

        if (players != null) {
            String[] names = players.split(",");
            for (String name : names) {
                game.join(name);
            }
        }

        // String state = rs.getString("state");
        // The join() method already updates the game state so we don't need to update it

        return game;
    }
}