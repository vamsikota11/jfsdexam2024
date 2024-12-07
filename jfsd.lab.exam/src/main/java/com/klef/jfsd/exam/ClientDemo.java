package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Update Operation using HQL with Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedEntities = session.createQuery(hql)
                                         .setParameter(1, "Updated Name")
                                         .setParameter(2, "Updated Location")
                                         .setParameter(3, 1) // Example Department ID
                                         .executeUpdate();

            transaction.commit();
            System.out.println(updatedEntities + " record(s) updated.");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
