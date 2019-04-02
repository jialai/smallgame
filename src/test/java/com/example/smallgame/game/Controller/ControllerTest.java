package com.example.smallgame.game.Controller;

import com.example.smallgame.game.Entity.User;
import com.example.smallgame.game.Dao.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Autowired
    private UserRepository userRepository;



    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        User user=new User();
        user.setName("Jia");
        user.setGain(20);
        user.setCost(10);
        userRepository.save(user);


    }
    /**
     * add user information
     */

    @Test
    public void playGame() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/play")
                        .param("name","Jia")
        )
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();

        System.out.print(responseString);
        Assert.assertThat(status, is(200));

    }

    /**
     * check user information
     * @throws Exception
     */

    @Test
    public void check()throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/check")
                        .param("name","Jia")
        )
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();

        Assert.assertThat(status, is(200));
        Assert.assertThat(responseString,is("cost: 10 gain: 20\n"));



    }


    /**
     * check all user information
     * @throws Exception
     */
    @Test
    public void getAllUsers()throws Exception {
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("/all")
                )
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();

        Assert.assertThat(status, is(200));
        Assert.assertThat(responseString,is("[{\"id\":1,\"name\":\"Jia\",\"gain\":20,\"cost\":10}]"));


       
    }

}