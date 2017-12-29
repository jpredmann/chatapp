package com.parliament.chatapp.repository;

import com.parliament.chatapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
   
    List<Message> findByReceiver( String receiver );

}
