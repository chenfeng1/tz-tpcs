package com.tz.tpcs;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
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
    public void onStartup(ServletContext container) throws ServletException {
        LOGGER.debug("onStartup() run...");
        // Create the 'root' Spring application context
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("classpath:applicationContext.xml");

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        XmlWebApplicationContext dispatcherContext = new XmlWebApplicationContext();
//        dispatcherContext.setConfigLocation("classpath:dispatcher-mvc.xml");
        dispatcherContext.setConfigLocation("classpath:spring/web-config.xml");

        //spring security Filter
        container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.setInitParameter("contextConfigLocation", "classpath:dispatcher-mvc.xml");
        dispatcher.addMapping("/");
    }

}
