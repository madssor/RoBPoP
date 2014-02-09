package no.brreg.robpop.dao;

import no.brreg.robpop.model.Person;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 21:09
 */
public class PersonDAOTest {

    private PersonDAO personDAO = new PersonDAO();


    @Test
    public void persistPerson_save2_getAllFetches2() {
        Person person = new Person("mgs", "Mads", "Sørhaug");
        personDAO.save(person);
        Person person1 = new Person("moj", "Morten", "Jenssen");
        personDAO.save(person1);

        assertThat(personDAO.getAllPersons(), hasItem(person));
        assertThat(personDAO.getAllPersons(), hasItem(person1));

        personDAO.delete(person);
        personDAO.delete(person1);
    }

    @Test
    public void persistPerson_usernameIsUnique() {
        Person mads = new Person("mgs", "Mads", "Sørhaug");
        Person marius = new Person("mgs", "Marius", "Stavseng");

        assertThat(personDAO.save(mads), is(true));
        assertThat(personDAO.save(marius), is(false));

        personDAO.delete(mads);
    }

}
