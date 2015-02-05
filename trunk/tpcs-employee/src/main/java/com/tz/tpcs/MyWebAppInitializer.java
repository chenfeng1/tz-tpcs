package com.tz.tpcs;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/5 14:28
 */
public class MyWebAppInitializer implements WebApplicationInitializer {

    private static final Logger LOGGER = Logger.getLogger(MyWebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.debug("onStartup() run...");
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
//        rootContext.setConfigLocations(new String[]{"classpath:applicationContext.xml"});
        rootContext.setConfigLocation("com.tz.tpcs.config");
        servletContext.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
//        dispatcher.setInitParameter("contextConfigLocation", "classpath:dispatcher-mvc.xml");
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
