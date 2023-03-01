package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.swing.*;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0] ;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {Spring.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
