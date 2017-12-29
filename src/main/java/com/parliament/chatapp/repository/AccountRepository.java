package com.parliament.chatapp.repository;

import com.parliament.chatapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByUsername( String username );

    @Query("select user.id, user.username from Account user")
    List<Object> getUsers();

   // @Query("select user.id, user.username from Account user where user.first_name=name or user.last_name=name")
   // List<Object> searchUserByName(@Param("name") String name );
}
