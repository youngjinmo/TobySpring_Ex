package com.bomstart.tobyspring.user.dao;

import com.bomstart.tobyspring.user.domain.User;
import com.bomstart.tobyspring.user.utils.DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("devandy");
        user.setName("youngjinmo");
        user.setPassword("12345678");

        dao.add(user);

        System.out.println(user.getId()+" 등록성공 !!");
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO user(id, name, password) VALUES(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DButils.DRIVER);
        Connection conn = DriverManager.getConnection(DButils.URL, DButils.USERNAME, DButils.PASSWORD);
        return conn;
    }

}
