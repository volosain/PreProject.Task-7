package web.config;


import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.swing.*;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Метод, указывающий на класс конфигурации
    protected Class<?>[] getRootConfigClasses() {
        return null ;
    }

    // Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    /* Данный метод указывает url, на котором будет базироваться приложение */
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException { //запускается при старте. Выполняем приватный метод, который добавляет фильтр
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }
    private void registerHiddenFieldFilter(ServletContext aContext) { // тот самый фильтр, который разрешает выполнять методы PATCH и DELETE
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
