package com.example.smallgame.game;

import com.example.smallgame.game.Dao.UserRepository;
import com.example.smallgame.game.Entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUser() {
        User user = new User();
        List<User> userFromRepository= new ArrayList<>();

        user.setName("Jia");
        user.setGain(20);
        user.setCost(10);

        //test user
        Assert.assertThat(user.getName(),is("Jia"));
        Assert.assertThat(user.getGain(),is(20));
        Assert.assertThat(user.getCost(),is(10));

        //test userRepository
        userRepository.save(user);
        userFromRepository=userRepository.findByName("Jia");
        for(User users:userFromRepository) {
            Assert.assertThat(users.getName(), is("Jia"));
            Assert.assertThat(users.getGain(),is(20));
            Assert.assertThat(users.getCost(),is(10));
        }
    }
}




