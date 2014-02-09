package no.brreg.robpop.dao;

import no.brreg.robpop.HibernateUtil;
import no.brreg.robpop.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 20:57
 */
public class PersonDAO {

    public void persistPerson(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(person);

        session.getTransaction().commit();
        session.close();
    }

    public List<Person> getAllPersons() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query personsQuery = session.createQuery("from Person");

        @SuppressWarnings("unchecked")
        List<Person> persons = personsQuery.list();

        session.getTransaction().commit();
        session.close();

        return persons;
    }

    public void delete(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(person);

        session.getTransaction().commit();
        session.close();
    }
}
