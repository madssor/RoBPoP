package no.brreg.robpop;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 21:29
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration
                    .getProperties());
            return configuration.buildSessionFactory(serviceRegistryBuilder.build());

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static boolean save(Object o) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(o);
            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean delete(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.delete(o);
        } catch (HibernateException hibernateException) {
            return false;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }

    public static <T> List<T> runQuery(String queryString) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery(queryString);

        @SuppressWarnings("unchecked")
        List<T> results = query.list();

        session.getTransaction().commit();
        session.close();

        return results;
    }

    public static <T> List<T> runCriteria(Criteria criteria, Session session) {
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<T> results = criteria.list();

        //Kortlevd transaksjon
        session.getTransaction().commit();

        return results;
    }

    public static <T> T runCriteriaSingle(Criteria criteria, Session session) {
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        T result = (T)criteria.uniqueResult();

        session.getTransaction().commit();
        return result;
    }


}
