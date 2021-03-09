package com.bomstart.tobyspring.user.service;

import com.bomstart.tobyspring.user.domain.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public void add(User user) {
        try{
            UserServiceImpl userService = new UserServiceImpl();
            Connection conn = userService.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user(id, name, password) VALUES(?,?,?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(String id) {
        try {
            UserServiceImpl userService = new UserServiceImpl();
            Connection conn = userService.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setString(1,id);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        final String URL = "jdbc:mysql://127.0.0.1:3306/bomstartDB";
        final String USERNAME = "root";
        final String PASSWORD = "1q2w3e4r";

        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
