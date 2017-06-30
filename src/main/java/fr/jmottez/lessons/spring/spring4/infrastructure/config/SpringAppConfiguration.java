package fr.jmottez.lessons.spring.spring4.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan({ "fr.jmottez.lessons.spring.spring4" })
public class SpringAppConfiguration {


}
