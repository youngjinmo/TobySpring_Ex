package com.bomstart.tobyspring.user.dao;

import com.bomstart.tobyspring.user.domain.User;
import com.bomstart.tobyspring.user.domain.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao{
    JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 유저 조회
     * @param id
     * @return User - 유저 정보
     */
    @Override
    public User selectUser(String id) {
        String query = "SELECT id, name, password FROM user WHERE id = "+id;
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(query, new UserMapper());
        } catch (EmptyResultDataAccessException e){
            System.out.println("id = "+id+" 의 회원이 존재하지 않습니다.");
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 모든 유저 조회
     * @return List<User> - user 테이블의 모든 유저정보
     */
    @Override
    public List<User> selectUsers() {
        String query = "SELECT id, name, password FROM user";
        return jdbcTemplate.query(query,new UserMapper());
    }

    /**
     *  유저 등록
     * @param user
     * @return
     */
    @Override
    public void createUser(User user) {
        String query = "INSERT INTO user(id, name, password) VALUES ('"+user.getId()+"','"+user.getName()+"','"+user.getPassword()+"')";
        jdbcTemplate.update(query);
    }

    /**
     * 유저 정보 수정
     * @param user
     * @return
     */
    @Override
    public void updateUser(User user) {
        String query =
                "UPDATE user SET name = '"+user.getName()+"', password = '"+user.getPassword()+"' WHERE id = '"+user.getId()+"'";
        try {
            jdbcTemplate.update(query);
        } catch(EmptyResultDataAccessException e){
            System.out.println("수정하려고 하는 id = "+user.getId()+"의 회원이 존재하지 않습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String id) {
        String query = "DELETE user WHERE id = "+id;
        try {
            jdbcTemplate.update(query);
        } catch(EmptyResultDataAccessException e){
            System.out.println("삭제하고자 하는 id = "+id+"의 회원이 존재하지 않습니다.");
            e.printStackTrace();
        }
    }

    /**
     * Statement, ResultSet close 중복 로직 리팩토링 (메소드 추출)
     * @param ps
     */
    private void closeAll(PreparedStatement ps) {
        this.closeAll(ps, null);
    }

    private void closeAll(PreparedStatement ps, ResultSet rs) {
        if (rs != null) try{rs.close();} catch(Exception e){}
    }
}
