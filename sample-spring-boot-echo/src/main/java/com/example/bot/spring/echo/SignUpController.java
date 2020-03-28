package com.example.bot.spring.echo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SignUpController {

    private final NewFollowerTemporaryStorage newFollowerTemporaryStorage;

    @Autowired
    public SignUpController(NewFollowerTemporaryStorage newFollowerTemporaryStorage) {
        this.newFollowerTemporaryStorage = newFollowerTemporaryStorage;
    }

    @GetMapping
    @ResponseBody
    public Object ok(@ModelAttribute SignUpDTO signUpDTO) {
        return "OK";
    }

    @PostMapping("signup")
    @ResponseBody
    public Object signUp(@ModelAttribute SignUpDTO signUpDTO) {

        NewFollowerEntity newFollowerEntity = newFollowerTemporaryStorage.get(signUpDTO.getNonce());
        signUpDTO.setNewFollower(newFollowerEntity);

        return signUpDTO;
    }

}
