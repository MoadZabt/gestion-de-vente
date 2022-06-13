package com.genielogiciel.gestiondevente.listener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;

public class HibernateServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactoryGV");
        sf.close();
        SessionFactory sfgs = (SessionFactory) sce.getServletContext().getAttribute("SessionFactoryGS");
        sfgs.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory sessionFactory = createSessionFactory();
        sce.getServletContext().setAttribute("SessionFactoryGV", sessionFactory);
        SessionFactory sessionFactoryGS = createGStockSessionFactory();
        sce.getServletContext().setAttribute("SessionFactoryGS", sessionFactoryGS);
    }

    private static SessionFactory createSessionFactory() {
        URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
        Configuration config = new Configuration();
        config.configure(url);
        SessionFactory sf = config.buildSessionFactory();
        return sf;
    }
    private static SessionFactory createGStockSessionFactory() {
        URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.g_stock.xml");
        Configuration config = new Configuration();
        config.configure(url);
        SessionFactory sf = config.buildSessionFactory();
        return sf;
    }
}
