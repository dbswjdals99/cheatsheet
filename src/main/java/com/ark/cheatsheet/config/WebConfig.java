package com.ark.cheatsheet.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.*;
import java.util.ArrayList;
import java.util.EnumSet;

public class WebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        Class[] rootContext = new Class[] { com.ark.cheatsheet.config.RootConfig.class};
        Class[] servletContext = new Class[] { ServletConfig.class };

        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();

        rootAppContext.register(rootContext);

        ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);

        context.addListener(listener);

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();

        servletAppContext.register(servletContext);


        FrameworkServlet dispatcherServlet = new DispatcherServlet(servletAppContext);

        ServletRegistration.Dynamic registration = context.addServlet("dispatcher", dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping(new String[] {"/"});
        registration.setAsyncSupported(true);

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);

        ArrayList<String> originList = new ArrayList<String>();
        ArrayList<String> headerList = new ArrayList<String>();
        ArrayList<String> methodList = new ArrayList<String>();

        originList.add("*");

        headerList.add("Content-Type");
        headerList.add("Access-Control-Allow-Origin");
        headerList.add("Access-Control-Allow-Header");
        headerList.add("Access-Control-Allow-Credentials");

        methodList.add("POST");
        methodList.add("GET");
        methodList.add("OPTIONS");

        Long maxAge = Long.valueOf(3600);

        corsConfiguration.setAllowedOrigins(originList);
        corsConfiguration.setAllowedHeaders(headerList);
        corsConfiguration.setAllowedMethods(methodList);

        corsConfiguration.setMaxAge(maxAge);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration("/LostArk/**", corsConfiguration);

        CorsFilter filter = new CorsFilter(source);


        registerCharacterEncodingFilter(context);
    }

    private void registerCharacterEncodingFilter(ServletContext servletContext) {

        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());

        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");

    }
}
