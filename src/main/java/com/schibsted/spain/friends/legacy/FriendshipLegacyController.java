package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.application.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendship")
public class FriendshipLegacyController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/request")
    ResponseEntity requestFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        try {
            friendshipService.requestFriendship(usernameFrom, usernameTo);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/accept")
    void acceptFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        throw new RuntimeException("not implemented yet!");
    }

    @PostMapping("/decline")
    void declineFriendship(
            @RequestParam("usernameFrom") String usernameFrom,
            @RequestParam("usernameTo") String usernameTo,
            @RequestHeader("X-Password") String password
    ) {
        throw new RuntimeException("not implemented yet!");
    }

    @GetMapping("/list")
    Object listFriends(
            @RequestParam("username") String username,
            @RequestHeader("X-Password") String password
    ) {
        throw new RuntimeException("not implemented yet!");
    }
}
