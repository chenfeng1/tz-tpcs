package com.tz.tpcs.util;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 项目路径 监听器
 */
@WebListener
public class PathListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(PathListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("contextInitialized...");
        ServletContext application = event.getServletContext();
        String path = application.getContextPath();
        logger.debug("contextPath["+path+"]save to application scope...");
        application.setAttribute(IConstant.PATH, path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.debug("contextDestroyed...");
    }
}
