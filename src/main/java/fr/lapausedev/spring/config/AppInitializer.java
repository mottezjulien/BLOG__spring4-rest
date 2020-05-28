package fr.lapausedev.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringAppConfiguration.class);
        springContext.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("restServletRegistration", new DispatcherServlet(springContext));
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/rest/*");
    }

}