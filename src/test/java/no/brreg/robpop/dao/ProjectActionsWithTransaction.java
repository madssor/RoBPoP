package no.brreg.robpop.dao;

import no.brreg.robpop.HibernateUtil;
import no.brreg.robpop.model.Person;
import no.brreg.robpop.model.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: Mads
 * Date: 10.02.14 00:40
 */
public class ProjectActionsWithTransaction {

    private static Session session;
    private Transaction transaction;

    @Before
    public void setup() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() {
        transaction.rollback();
        session.close();
    }


    @Test
    public void createProject_listAllProjects() {
        Project project = new Project("OR Klient II", "ORII");
        session.save(project);

        assertThat(session.createCriteria(Project.class).list().size(), is(1));
    }

    @Test
    public void createProjectWithPeople_peopleAreLazyLoaded() {
        Project project = new Project("OR Klient II", "ORII");
        Person person = new Person("mgs", "Mads", "Sørhaug");
        Set<Person> participants = new HashSet<>();
        participants.add(person);
        project.setParticipants(participants);
        session.save(project);

        Project fetchedProject = (Project)session.createCriteria(Project.class).uniqueResult();

        for (Person participant : fetchedProject.getParticipants()) {
            assertThat(participant, is(person));
        }
    }

    @Test(expected = HibernateException.class)
    public void createProject_codeIsUnique() {
        Project project = new Project("OR Klient II", "ORII");
        session.save(project);

        Project otherProject = new Project("Nytt Områderegister", "ORII");

        session.save(otherProject);
    }
}
