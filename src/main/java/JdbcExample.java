import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.repository.DataSourceUtil;
import tech.bts.cardgame.repository.GameRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Program that reads and displays the games from the database
 * using JDBC (Java utilities to access databases).
 */

public class JdbcExample{

    public static void main(String[] args) throws SQLException{
        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from games");

        List<Game> gamesList = new ArrayList<>();

        while (rs.next()){

            Deck deck = new Deck();
            deck.generate();
            deck.shuffle();

            int id = rs.getInt("id");
            String state = rs.getString("state");
            String players = rs.getString("players");

            Game game = new Game(deck);

            game.setId(id);

            game.join(players);

            game.setState(Game.State.valueOf(state));
            gamesList.add(game);

            System.out.println(id + ", " + state + ", " + players);

        }


        System.out.println(gamesList);


        rs.close();
        statement.close();
        connection.close();
    }
}
