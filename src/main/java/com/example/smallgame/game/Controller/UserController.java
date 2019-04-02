package com.example.smallgame.game.Controller;

import com.example.smallgame.game.Service.Game;
import com.example.smallgame.game.Entity.User;
import com.example.smallgame.game.Dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    public static double free = 0.10;
    double randomNumberForFreeRound;

    /**
     * add user information
     */
    @GetMapping(path="/play")
    public @ResponseBody String PlayGame (@RequestParam String name) {
        Integer gain;
        String result="Gain: ";
        User user=new User();
        Game game=new Game();
        gain=game.Gambling();
        user.setName(name);
        user.setGain(gain);
        user.setCost(10);
        userRepository.save(user);
        result+=Integer.toString(gain)+"\n";
        while (true) {

            randomNumberForFreeRound = Math.random();
            if (randomNumberForFreeRound >= 0 && randomNumberForFreeRound <= free) {
                User user1=new User();
                user1.setName(name);
                user1.setGain(gain);
                user1.setCost(0);
                userRepository.save(user1);
                result+="free round gain: ";
                result+=Integer.toString(gain)+"\n";

            } else
                break;
        }

        log.info(user.toString()+" saved to the repo");
        return result;
    }

    /**
     * check user information
     */
    @GetMapping(path = "/check")
    public @ResponseBody String check(@RequestParam String name, Model model) {
        String result="";
        List<User> users = userRepository.findByName(name);
        // if does not have this user:
        if (users.size() == 0) {
            log.warn("attempting to find the non-existed account");
            return "this user does not exist";
        } else {
            for(User user:users){
                result+="cost: "+user.getCost()+" gain: "+user.getGain()+"\n";
            }
           return result;


        }

    }


    /**
     * check all user information
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
     }

}