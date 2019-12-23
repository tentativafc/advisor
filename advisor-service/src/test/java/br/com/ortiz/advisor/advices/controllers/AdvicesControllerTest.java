package br.com.ortiz.advisor.advices.controllers;

import br.com.ortiz.TestConfig;
import br.com.ortiz.advisor.advices.model.repositories.AdviceDao;
import br.com.ortiz.security.configuration.ConfigProperties;
import br.com.ortiz.security.model.User;
import br.com.ortiz.security.services.JwtService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
//@WebMvcTest
//@AutoConfigureMockMvc
public class AdvicesControllerTest {

//    @Autowired
//    private MockMvc mvc;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private AdvicesController advicesController;

    @MockBean
    private AdviceDao adviceDao;

    @MockBean
    public JwtService jwtService;

    @Test
    public void testController2() {
        advicesController.geAllAdvices();
    }


    @Test
    public void testController() throws Exception {
        User user = new User();
        user.setEmail("teste@teste.com");
        user.setFirstName("Teste");
        user.setLastName("Teste LN");
        user.setId(UUID.randomUUID());

        when(jwtService.getTokenFromUser(any(User.class))).thenReturn("123456");
        when(jwtService.getUserFromToken("123456")).thenReturn(Optional.of(user));

        final String token = jwtService.getTokenFromUser(user);

//        mvc.perform(get("/advices")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
    }

}
