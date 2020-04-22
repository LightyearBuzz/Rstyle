package com.github.lightyear;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ResumeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    //create connection if it is absent or close
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    //gets data from the base
    public Map getData(String name) throws SQLException {
        int id = 0;
        String profession = null;
        String phone = null;
        String email = null;
        String repository = null;
        String region = null;
        String universityTitle = null;
        String faculty = null;
        String company = null;
        String time = null;
        List<String> skillsList = new ArrayList<String>();
        Map<String, Object> dataMap = new HashMap<String, Object>();

        String candidateQuery = "SELECT * FROM candidates WHERE name = ?;";
        String contactsQuery = "SELECT * FROM contacts WHERE id = ?;";
        String educationQuery = "SELECT * FROM education WHERE id = ?;";
        String experienceQuery = "SELECT * FROM experience WHERE id = ?;";
        String skillsQuery = "SELECT * FROM skills WHERE id = ?;";

        connect();
        //get candidates table
        PreparedStatement statement = jdbcConnection.prepareStatement(candidateQuery);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        id = resultSet.getInt("id");
        profession = resultSet.getString("profession");

        //get contacts table
        statement = jdbcConnection.prepareStatement(contactsQuery);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        resultSet.next();
        phone = resultSet.getString("phone");
        email = resultSet.getString("email");
        repository = resultSet.getString("repository");
        region = resultSet.getString("region");

        //get education table
        statement = jdbcConnection.prepareStatement(educationQuery);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        resultSet.next();
        universityTitle = resultSet.getString("title");
        faculty = resultSet.getString("faculty");

        //get experience table
        statement = jdbcConnection.prepareStatement(experienceQuery);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        resultSet.next();
        company = resultSet.getString("company");
        time = resultSet.getString("time");

        //get skills table
        statement = jdbcConnection.prepareStatement(skillsQuery);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        while(resultSet.next()){
            skillsList.add(resultSet.getString("skill"));
        }

        resultSet.close();
        statement.close();
        disconnect();

        dataMap.put("profession", profession);
        dataMap.put("phone", phone);
        dataMap.put("email", email);
        dataMap.put("repository", repository);
        dataMap.put("region", region);
        dataMap.put("universityTitle", universityTitle);
        dataMap.put("faculty", faculty);
        dataMap.put("company", company);
        dataMap.put("time", time);
        dataMap.put("time", time);
        dataMap.put("skillsList", skillsList);

        return dataMap;
    }
}