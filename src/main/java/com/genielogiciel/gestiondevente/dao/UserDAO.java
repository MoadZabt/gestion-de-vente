package com.genielogiciel.gestiondevente.dao;

import com.genielogiciel.gestiondevente.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;
import java.util.List;

public class UserDAO implements UserDAOInterface{

    private Session currentSession;
    private SessionFactory sessionFactory;

    private Transaction currentTransaction;

    public UserDAO() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        this.sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
        this.currentSession = sessionFactory.getCurrentSession();
    }

    @Override
    public Long create(User user) {
        return (Long) currentSession.save(user);
    }

    @Override
    public void update(User admin) {
        currentSession.saveOrUpdate(admin);
    }

    @Override
    public User findById(Long id) {
        return currentSession.find(User.class, id);
    }

    @Override
    public void delete(User admin) {

    }

    @Override
    public List<User> findAll() {
        String hql = "FROM User";
        Query query = getCurrentSession().createQuery(hql);
        List<User> allUsers = (List<User>) query.getResultList();
        return allUsers;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String hql = "FROM User U WHERE U.username=:username AND U.password=:password";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = (User) query.uniqueResult();
        return user;
    }

    public Session openCurrentSession() {
        currentSession = this.sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = this.sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
