package com.monkeygang.weatherstatistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;


    private DatabaseConnection(){
        try {
            String serverName = "EASV-DB4";
            String databaseName = "CSe2022t_t_2_DMI_Vejrdata_Projekt";
            String userName = "CSe2022t_t_2";
            String password = "CSe2022tT2#";

            String url = "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + databaseName + ";user=" + userName + ";password="+ password + ";encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("can not create connection");
            System.out.println(e.getMessage());
        }

        System.out.println("connected to DB");

    }

    public Connection getConnection(){
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {

        if (instance == null){
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()){
            instance = new DatabaseConnection();
        }

        return instance;
    }


}
