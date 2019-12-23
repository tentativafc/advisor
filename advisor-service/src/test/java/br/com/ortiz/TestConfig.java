package br.com.ortiz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( {"br.com.ortiz.security", "br.com.ortiz.advisor"})
public class TestConfig {
}
