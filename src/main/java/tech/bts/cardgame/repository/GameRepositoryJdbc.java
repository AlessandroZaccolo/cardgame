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

        doWithStatement((statement) -> {

            return statement.executeUpdate("insert into games (state, players)" +
                    " values ('" + game.getState() + "', NULL)");
        });
    }

    public Game getById(long id) {



        return doWithStatement((statement -> {


            ResultSet rs = statement.executeQuery("select * from games where id = " + id);

            Game game = null;

            if (rs.next()) {
                game = getGame(rs);
            }

            rs.close();

            return game;

        }));


    }

    public Collection<Game> getAll() {


        return doWithStatement((statement) -> {

            ResultSet rs = statement.executeQuery("select * from games");

            List<Game> games = new ArrayList<>();

            while (rs.next()) {

                Game game = getGame(rs);
                games.add(game);
            }

            rs.close();

            return games;
        });

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


    // Function --> function that takes an argument and returns a value
    // Supplier --> function that doesn't take any argument and returns a value
    // Consumer --> function that takes an argument and doesn't return a value

    // This is similar to Consumer, but allows exception.
    interface MyFunction<P, R>{
        R apply(P t) throws Exception;
    }

    private <T> T doWithStatement(MyFunction<Statement, T> useStatement) {

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            T result = useStatement.apply(statement); // part that you will specify

            statement.close();
            connection.close();

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}