import tech.bts.cardgame.model.Deck;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.repository.DataSourceUtil;
import tech.bts.cardgame.repository.GameRepository;
import tech.bts.cardgame.repository.GameRepositoryJdbc;

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


        List<Game> games = new ArrayList<>();

        while (rs.next()){

            int id = rs.getInt("id");
            String state = rs.getString("state");
            String players = rs.getString("players");

            System.out.println(id + ", " + state + ", " + players);

        }



        GameRepositoryJdbc jdcb = new GameRepositoryJdbc();
        Game game = new Game(null);

        game.setId(15);

        jdcb.createOrUpdate(game);

        jdcb.createOrUpdate(game);



    }
}
