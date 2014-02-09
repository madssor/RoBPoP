package no.brreg.robpop.dao;

import no.brreg.robpop.model.Person;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 21:09
 */
public class PersonDAOTest {

    @Test
    public void persistPerson_save2_getAllFetches2() {
        PersonDAO personDAO = new PersonDAO();
        Person person = new Person("Mads", "Sørhaug");
        personDAO.persistPerson(person);
        Person person1 = new Person("Morten", "Jenssen");
        personDAO.persistPerson(person1);

        assertThat(personDAO.getAllPersons(), hasItem(person));
        assertThat(personDAO.getAllPersons(), hasItem(person1));

        personDAO.delete(person);
        personDAO.delete(person1);
    }


}
