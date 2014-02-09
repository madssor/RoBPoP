package no.brreg.robpop.dao;

import no.brreg.robpop.HibernateUtil;
import no.brreg.robpop.model.Person;

import java.util.List;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 20:57
 */
public class PersonDAO {

    public boolean save(Person person) {
        return HibernateUtil.save(person);
    }

    public List<Person> getAllPersons() {
        return HibernateUtil.runQuery("from Person");
    }

    public boolean delete(Person person) {
        return HibernateUtil.delete(person);
    }
}
