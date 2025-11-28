package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * A Generic DAO base class for Hibernate.
 * Provides basic CRUD and query operations using SessionFactory.
 * Extend this class for each entity-specific DAO.
 */
@Repository
public abstract class GenericDao<T, ID extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<T> entityClass;

    /**
     * Constructor to capture the entity type.
     */
    protected GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Helper to get the current Hibernate Session.
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Save or update an entity.
     */
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * Persist a new entity.
     */
    public void persist(T entity) {
        getSession().persist(entity);
    }

    /**
     * Insert and return generated ID.
     */
    public ID save(T entity) {
        return (ID) getSession().save(entity);
    }

    /**
     * Delete an entity.
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * Find entity by primary key ID.
     */
    public T findById(ID id) {
        return getSession().get(entityClass, id);
    }

    /**
     * Get all records for this entity.
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createQuery("from " + entityClass.getSimpleName()).list();
    }

    /**
     * Execute a custom HQL query with positional parameters.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByQuery(String hql, Object... params) {
        Query<T> query = getSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.list();
    }

    /**
     * Execute a custom HQL query expecting a single result.
     */
    @SuppressWarnings("unchecked")
    protected T findUniqueByQuery(String hql, Object... params) {
        Query<T> query = getSession().createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.uniqueResult();
    }

    /**
     * Flush current session (sync with DB).
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * Clear current session (detach all entities).
     */
    public void clear() {
        getSession().clear();
    }

    /**
     * Run manual transaction block (for special cases).
     */
    public void executeWithinTransaction(SessionOperation operation) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            operation.execute(session);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    /**
     * Functional interface for manual transaction blocks.
     */
    @FunctionalInterface
    public interface SessionOperation {
        void execute(Session session);
    }
}
