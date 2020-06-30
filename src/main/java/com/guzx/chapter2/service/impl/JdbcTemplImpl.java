package com.guzx.chapter2.service.impl;

import com.guzx.chapter2.enumeration.SexEnum;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.JdbcTemplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service
public class JdbcTemplImpl implements JdbcTemplUserService {

    @Autowired
    private JdbcTemplate template;

    /**
     * 获取映射关系
     *
     * @return
     */
    private RowMapper<User> getUserMapper() {
        RowMapper<User> userRowMapper = (ResultSet resultSet, int rownum) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("user_name"));
            user.setNote(resultSet.getString("note"));
            int sexId = resultSet.getInt("sex");
            SexEnum sexEnum = SexEnum.getEnumById(sexId);
            user.setSex(sexEnum);
            return user;
        };
        return userRowMapper;
    }

    @Override
    public User getUser(Long id) {
        String sql = "select id,user_name,sex,note from t_user where id = ?";
        Object[] params = new Object[]{id};
        User user = template.queryForObject(sql, params, getUserMapper());
        return user;
    }

    /*public User getUser2(Long id){

    }*/

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select id,user_name,sex,note " +
                "from t_user " +
                "where user_name like concat('%',?'%') " +
                "and note like concat('%',?'%')";
        Object[] params = new Object[]{userName, note};
        List<User> users = template.query(sql, params, getUserMapper());
        return users;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user (user_name,sex,note) values(?,?,?)";
        return template.update(sql, user.getUserName(), user.getSex().getId(), user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user user_name = ?,sex = ?,note = ? where id = ?";
        return template.update(sql, user.getUserName(), user.getSex(), user.getNote(), user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        return template.update(sql, id);
    }

    public User getUser2(int id){
        // Lambda表达式
        User result = this.template.execute((Statement statement)->{
            String sql1 = "select count(*) total from t_user where id= "+id;
            ResultSet set = statement.executeQuery(sql1);
            while (set.next()){
                int total = set.getInt("total");
                System.out.println("总数有："+total);
            }
            String sql2 = "select id,user_name,sex,note from t_user where id="+id;
            ResultSet set2 = statement.executeQuery(sql2);
            User user = null;
            while (set2.next()){
                int rowNum = set2.getRow();
                user = getUserMapper().mapRow(set2,rowNum);
            }
            return user;
        });
        return result;
    }
}
