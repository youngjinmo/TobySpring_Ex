package com.bomstart.tobyspring.user.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    // mysql 정보
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://127.0.0.1:3306/bomstartDB";
    static final String USERNAME = "root";
    static final String PASSWORD = "1q2w3e4r";

    // h2 정보
//    static final String DRIVER = "org.h2.Driver";
//    static final String URL = "jdbc:h2:~/test";
//    static final String USERNAME = "sa";
//    static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
