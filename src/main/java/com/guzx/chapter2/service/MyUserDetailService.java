package com.guzx.chapter2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 这里可以通过userService去数据库中查询数据
        return new MyUserDetail(userName);
//        Optional<User> user = userService.getUserByUserName(userName);
//        user.orElseThrow(() -> new UsernameNotFoundException("Not Found "+userName));
//        return user.map(MyUserDetail::new).get();
    }
}
