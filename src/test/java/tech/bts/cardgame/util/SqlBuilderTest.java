package tech.bts.cardgame.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SqlBuilderTest {

    @Test
    public void selectFromTable(){

        // 1. prepare objects
        SqlBuilder sqlBuilder = new SqlBuilder();

        sqlBuilder.from("users");
        String sql = sqlBuilder.build();

        assertThat(sql, is ("select * from users"));

    }

    @Test
    public void selectFromTable_Simplified(){

        String sql = new SqlBuilder().from("users").build();

        assertThat(sql, is("select * from users"));
    }


    @Test
    public void whereCondition(){

        String sql = new SqlBuilder().from("meals").where("name = 'pasta'").build();

        assertThat(sql, is("select * from meals where name = 'pasta'"));

    }

    @Test
    public void whereNullCondition(){

        String sql = new SqlBuilder().from("meals").where("null").build();

        assertThat(sql, is("select * from meals"));

    }

    @Test
    public void whereMultipleCondition(){

        String sql = new SqlBuilder().from("meals")
                .where("name = 'pasta'").where("price = 10").build();

        assertThat(sql, is("select * from meals where name = 'pasta' and price = 10"));

    }

    @Test
    public void whereCondition2(){

        String sql = new SqlBuilder().from("meals")
                .where("name", "=","pasta").where("price","=", 10).build();

        assertThat(sql, is("select * from meals where name = 'pasta' and price = 10"));

    }

    @Test
    public void whereEasierConditions() {

        String productType = "TOOLS";

        String sql = new SqlBuilder()
                .from("products")
                .where("type", "=", productType)
                .where("units", ">=", 100)
                .where("price", "<=", 9.95)
                .where("available", "=", true)
                .build();

        assertThat(sql, is("select * from products" +
                " where type = 'TOOLS' and units >= 100" +
                " and price <= 9.95 and available = true"));
    }

    @Test
    public void whereNullValue() {

        String productType = "TOOLS";

        String sql = new SqlBuilder()
                .from("products")
                .where("type", "=", null)
                .where("units", ">=", 100)
                .where("price", "<=", 9.95)
                .where("available", "=", true)
                .build();

        assertThat(sql, is("select * from products" +
                " where units >= 100" +
                " and price <= 9.95 and available = true"));
    }


    @Test
    public void whereNullValueAfterWhere() {

        String productType = "TOOLS";

        String sql = new SqlBuilder()
                .from("products")
                .where("type", "=", productType)
                .where("units", ">=", null)
                .where("price", "<=", 9.95)
                .where("available", "=", true)
                .build();

        assertThat(sql, is("select * from products" +
                " where type = 'TOOLS'" +
                " and price <= 9.95 and available = true"));
    }

    @Test
    public void whereTwoNullValueAfterWhere() {

        String productType = "TOOLS";

        String sql = new SqlBuilder()
                .from("products")
                .where("type", "=", productType)
                .where("units", ">=", null)
                .where("price", "<=", null)
                .where("available", "=", true)
                .build();

        assertThat(sql, is("select * from products" +
                " where type = 'TOOLS'" +
                " and available = true"));
    }



}
