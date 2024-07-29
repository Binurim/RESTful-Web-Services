package com.apps.developerblog.app.ws.ui.controller;

import com.apps.developerblog.app.ws.exception.UserServiceException;
import com.apps.developerblog.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.apps.developerblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.apps.developerblog.app.ws.ui.model.response.UserRest;
import com.apps.developerblog.app.ws.userservice.UserService;
import com.apps.developerblog.app.ws.userservice.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController  {

//    @GetMapping()
//    public String getUsers(){
//        return "get user was called";
//    }

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

//        String firstName = null;
//        int firstNameLength = firstName.length();

        if(true) throw new UserServiceException("A User service exception is thrown");

        if (users.containsKey(userId)) {
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping()
    public String getUsersWithPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "50") int limit,
                                         @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort){
        return "get users was called with page = " + page + " and limit = "
                + limit + " and sort = " + sort;
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){

        // direct dependency of serviceImpl - not good for testing
        // UserRest returnValue = new UserServiceImpl().createUser(userDetails);

        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
