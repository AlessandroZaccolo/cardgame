package tech.bts.cardgame.util;


public class SqlBuilder {

    private String sql;
    private int conditionCounter = 0;

    public SqlBuilder from(String table){
        this.sql = "select * from "+ table;
        return this;
    }

    public SqlBuilder where(String condition) {

        String keywordBeforeCondition;

        if (condition == "null"){
            this.sql += "";
        } else {
            if (conditionCounter == 0){
                keywordBeforeCondition = " where ";
            } else {
                keywordBeforeCondition = " and ";
            }
            this.sql += keywordBeforeCondition + condition;
            conditionCounter++;
        }


        return this;
    }

    public SqlBuilder where(String column, String operator, Object value) {

        String valueStr;
        String condition = column + " " + operator + " ";

        if (value == null) {
            condition = "null";
        } else if (value instanceof String){
            valueStr = "'" + value + "'";
            condition += valueStr;
        } else {
            valueStr = value.toString();
            condition += valueStr;
        }

        return where(condition);
    }


    public String build(){
        return this.sql;
    }
}
