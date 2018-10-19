package com.cdm.depaul.coffeeShop.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//https://docs.spring.io/spring-framework/docs/5.0.9.RELEASE/javadoc-api/org/springframework/web/WebApplicationInitializer.html?is-external=true
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * Tell the Front Controller where the applicationContext/app configuration is.
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ApplicationContext.class };
    }

    /**
     *
     * Tell the Front Controller to handle all requests of this pattern: "/"; all requests...
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
