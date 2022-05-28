package com.genielogiciel.gestiondevente.listener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

public class HibernateServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
        sf.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory sessionFactory = createSessionFactory();
        sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
    }

    private static SessionFactory createSessionFactory() {
        URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
        Configuration config = new Configuration();
        config.configure(url);
        SessionFactory sf = config.buildSessionFactory();
        return sf;
    }
}