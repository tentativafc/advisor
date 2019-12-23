package br.com.ortiz.advisor.advices.controllers;

import br.com.ortiz.TestConfig;
import br.com.ortiz.advisor.advices.model.Advice;
import br.com.ortiz.advisor.advices.model.repositories.AdviceDao;
import br.com.ortiz.security.configuration.ConfigProperties;
import br.com.ortiz.security.services.JwtService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class AdvicesControllerTest {


    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private AdvicesController advicesController;

    @MockBean
    private AdviceDao adviceDao;

    @MockBean
    public JwtService jwtService;

    @Test
    public void testController() throws Exception {
        Advice advice = new Advice();
        advice.setDate(LocalDateTime.now());
        advice.setIsToBuy(true);
        advice.setSymbol("PETR4");
        when(adviceDao.getAll()).thenReturn(Arrays.asList(advice));
        final List<Advice> advices = advicesController.geAllAdvices();
        Assert.assertTrue(advices.size() == 1);

    }

}
