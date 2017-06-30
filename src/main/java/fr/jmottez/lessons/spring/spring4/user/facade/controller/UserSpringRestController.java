package fr.jmottez.lessons.spring.spring4.user.facade.controller;

import fr.jmottez.lessons.spring.spring4.infrastructure.facade.exception.ResourceNotFoundException;
import fr.jmottez.lessons.spring.spring4.user.facade.transport.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserSpringRestController {

    private List<UserDTO> list = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public List<UserDTO> list() {
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    @ResponseBody
    public UserDTO findById(@PathVariable(value = "userId") int pathId) {
        return list.stream()
                .filter(dto -> dto.getId() == pathId)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException());
    }


    @RequestMapping(method = RequestMethod.POST, name = "/")
    @ResponseBody
    public UserDTO save(@RequestBody UserDTO input) {
        if (list.contains(input)) {
            return doMerge(input);
        }
        return doSave(input);
    }

    private UserDTO doMerge(UserDTO input) {
        list.set(list.indexOf(input), input);
        return input;
    }

    private UserDTO doSave(UserDTO input) {
        input.setId(list.size() + 1);
        list.add(input);
        return input;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/")
    public ResponseEntity<Void> remove(@RequestBody UserDTO input) {
        list.remove(input);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

