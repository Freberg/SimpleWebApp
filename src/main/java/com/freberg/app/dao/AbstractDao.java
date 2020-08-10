package com.freberg.app.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {

    protected final Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected CriteriaQuery<T> createCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery.select(root);
        return criteriaQuery;
    }

    protected Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void persist(Collection<T> items) {
        items.forEach(this::persist);
    }

    @Transactional
    public void persist(T item) {
        getSession().saveOrUpdate(item);
    }
}
