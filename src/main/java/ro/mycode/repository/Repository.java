package ro.mycode.repository;

import ro.mycode.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Repository {

    // initializare baza de date
    private String JdbcURL = "jdbc:mysql://localhost:3306/exemplujavaimport?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "root";
    private Connection connection = null;
    private Statement statement = null;

    //constructor
    public Repository() {
        try {
            connection = DriverManager.getConnection(JdbcURL, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //statements
    protected void executeStatement(String execute) {
        try {
            statement.execute(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //getter
    public String getJdbcURL() {
        return JdbcURL;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public Statement getStatement() {
        return statement;
    }

    //setter
    public void setJdbcURL(String jdbcURL) {
        JdbcURL = jdbcURL;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}