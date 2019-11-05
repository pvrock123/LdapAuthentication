package com.example.demo.repositories;

import com.example.demo.domain.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LdapRepository<User> {
    User findByUserIdAndPwd(String userId, String pwd);
    User findByUserId(String userId);
}
