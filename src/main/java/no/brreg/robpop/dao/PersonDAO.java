package no.brreg.robpop.dao;

import no.brreg.robpop.HibernateUtil;
import no.brreg.robpop.model.Person;

import java.util.List;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 20:57
 */
public class PersonDAO {

    public void persistPerson(Person person) {
        HibernateUtil.persist(person);
    }

    public List<Person> getAllPersons() {
        return HibernateUtil.runQuery("from Person");
    }

    public void delete(Person person) {
        HibernateUtil.delete(person);
    }
}
