package com.parliament.chatapp.controller;

import com.parliament.chatapp.model.Message;
import com.parliament.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/messages")
    public List<Message> getAllMessages( HttpServletRequest request ) {
        return messageRepository.findByReceiver( request.getUserPrincipal().getName() );
    }

    @PostMapping("/messages")
    public Message create(HttpServletRequest request, @Valid @RequestBody Message message) {
        message.setSender( request.getUserPrincipal().getName() ); 
        return messageRepository.save(message);
    }
}
