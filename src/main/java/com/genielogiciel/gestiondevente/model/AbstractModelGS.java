package com.genielogiciel.gestiondevente.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.List;


public abstract class AbstractModelGS<T> implements Serializable {

    private Class<T> entity;
    protected ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    protected SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactoryGS");

    public AbstractModelGS(Class<T> entity) {
        this.entity = entity;
    }

    public List<T> findAll() {
        List<T> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = session.createQuery("from " + entity.getName()).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public T find(Long id) {
        T result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hql = "FROM " + entity.getName() +" where id=:id" ;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            result = (T) query.uniqueResult();
            System.out.println(result);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean create(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean update(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean delete(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public List<T> findAllPaginated(int fromRow, int toRow) {
        List<T> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from " + entity.getName());
            query.setFirstResult(fromRow);
            query.setMaxResults(toRow);
            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public Long size() {
        Long result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("select count(*) from " + entity.getName());
            result = (Long) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

}