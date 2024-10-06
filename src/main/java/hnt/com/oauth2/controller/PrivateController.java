package hnt.com.oauth2.controller;

import hnt.com.oauth2.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/private/messages")
    public ResponseEntity<MessageDto> messages() {
        return ResponseEntity.ok(new MessageDto("This is a private message"));
    }
}
