package com.unblock.server.controllers;

import com.unblock.proto.Block;
import com.unblock.proto.Block.Bounds;
import com.unblock.proto.Block.Bounds.Point;
import com.unblock.proto.GetNeighborhoodRequest;
import com.unblock.proto.Neighborhood;
import com.unblock.proto.NewUser;
import com.unblock.server.data.storage.User;
import com.unblock.server.exception.EmailAlreadyExistsException;
import com.unblock.server.exception.UsernameAlreadyExistsException;
import com.unblock.server.security.TokenAuthenticationService;
import com.unblock.server.security.password.PasswordManager;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class NeighborhoodController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/neighborhood/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Neighborhood getNeighborhood(@PathVariable String id) throws Exception {
        return Neighborhood.newBuilder()
                .setId(id)
                .setName("East Village")
                .addBlocks(
                        Block.newBuilder()
                                .setBounds(
                                        Bounds.newBuilder()
                                                .addPoints(Point.newBuilder().setX(349).setY(297))
                                                .addPoints(Point.newBuilder().setX(349).setY(330))
                                                .addPoints(Point.newBuilder().setX(468).setY(330))
                                                .addPoints(Point.newBuilder().setX(469).setY(297))))
                .build();
    }
}
