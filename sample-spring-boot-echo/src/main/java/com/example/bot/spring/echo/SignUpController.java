package com.example.bot.spring.echo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SignUpController {

    private final NewFollowerTemporaryStorage newFollowerTemporaryStorage;

    @Autowired
    public SignUpController(NewFollowerTemporaryStorage newFollowerTemporaryStorage) {
        this.newFollowerTemporaryStorage = newFollowerTemporaryStorage;
    }

    @PostMapping("signup")
    @ResponseBody
    public Object greetingSubmit(@ModelAttribute SignUpDTO signUpDTO) {

        NewFollowerEntity newFollowerEntity = newFollowerTemporaryStorage.get(signUpDTO.getNonce());
        signUpDTO.setNewFollower(newFollowerEntity);

        return signUpDTO;
    }

}
