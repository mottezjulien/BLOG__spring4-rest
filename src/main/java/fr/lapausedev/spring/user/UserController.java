package fr.lapausedev.spring.user;

import fr.lapausedev.spring.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private List<UserDTO> list = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public List<UserDTO> list() {
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    @ResponseBody
    public UserDTO findById(@PathVariable(value = "userId") int userId) {
        return list.stream()
                .filter(dto -> dto.getId() == userId)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException());
    }


    @RequestMapping(method = RequestMethod.POST, name = "/")
    @ResponseBody
    public UserDTO save(@RequestBody UserDTO input) {
        if (list.contains(input)) {
            return merge(input);
        }
        return doSave(input);
    }

    private UserDTO merge(UserDTO input) {
        list.set(list.indexOf(input), input);
        return input;
    }

    private UserDTO doSave(UserDTO input) {
        input.setId(list.size() + 1);
        list.add(input);
        return input;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{userId}")
    public ResponseEntity<Void> remove(@PathVariable(value = "userId") int userId) {
        list.remove(findById(userId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}