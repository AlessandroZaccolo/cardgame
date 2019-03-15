package tech.bts.cardgame.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    private JdbcTemplate jdbcTemplate;

    public GameRepositoryJdbc() {
        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Game game) {

        jdbcTemplate.update("insert into games (state, players)" +
                " values ('" + game.getState() + "', NULL)");

    }

    // Implement an update method. It should update the game in the database by ID
    public void update(long id, String name) {

        Game game = new Game(null);

        if (name.contains(",")){
            game.setState(Game.State.PLAYING);
        } else {
            game.setState(Game.State.OPEN);
        }

        jdbcTemplate.update("update games set state ='"
                + game.getState()+"', players ='"
                + name +"' where id = "+ id);

    }

    // When you have the create and the update methods, implement another method createOrUpdate(game).
    //- If the game doesn't have an ID (it's null), it will create a game
    //- If the game has an ID (it's not null), it will update the game.

    public void createOrUpdate(long id, String name){

        GameRepositoryJdbc jdbc = new GameRepositoryJdbc();

        Game game = new Game(null);

        if (jdbc.getById(id) == null){
            create(game);
        } else {
            update(id, name);
        }
    }


    public Game getById(long id) {

        return jdbcTemplate.queryForObject(
                "select * from games where id = " + id,
                (rs, rowNum) -> getGame(rs));

    }

    public Collection<Game> getAll() {

        return jdbcTemplate.query("select * from games",
                (rs, rowNum) -> getGame(rs) );

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

    /*
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
    */
}