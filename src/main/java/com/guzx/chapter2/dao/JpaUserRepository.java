package com.guzx.chapter2.dao;

import com.guzx.chapter2.pojo.User_JPA;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User_JPA, Long> {

    @Query("from user_jpa where user_name like concat('%',?1,'%') " + "and note like concat('%',?2,'%') ")
    public List<User_JPA> findUser_JPAs(String userName, String note);

    /**
     * 按用户名模糊查询
     * @param userName
     * @return
     */
    List<User_JPA> findByUserNameLike(String userName);

    User_JPA getUser_JPAById(Long id);

    List<User_JPA> findByUserNameLikeOrNoteLike(String userName,String note);

}
