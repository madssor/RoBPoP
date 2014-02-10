package no.brreg.robpop.dao;

import no.brreg.robpop.model.Person;
import no.brreg.robpop.model.Project;
import org.hibernate.LazyInitializationException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;


/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 22:25
 */
public class ProjectDAOTest {

    private ProjectDAO projectDAO = new ProjectDAO();
    private PersonDAO personDAO = new PersonDAO();

    @Test
    public void createProject_listAllProjects() {
        Project project = new Project("OR Klient II", "ORII");
        projectDAO.save(project);

        assertThat(projectDAO.getAllProjects().size(), is(1));

        projectDAO.delete(project);

        assertThat(projectDAO.getAllProjects().size(), is(0));
    }

    @Test
    public void createProjectWithPeople_peopleAreLazyLoaded() {
        Project project = new Project("OR Klient II", "ORII");
        Person person = new Person("mgs", "Mads", "Sørhaug");
        Set<Person> participants = new HashSet<>();
        participants.add(person);
        project.setParticipants(participants);
        projectDAO.save(project);

        Project fetchedProject = projectDAO.getAllProjects().get(0);

        boolean lazyException = false;

        try {
            for (Person participant : fetchedProject.getParticipants()) {
                assertThat(participant, is(person));
            }
        } catch (LazyInitializationException lie) {
            lazyException = true;
        }
        assertThat(lazyException, is(true));

        projectDAO.delete(project);
        personDAO.delete(person);
    }

    @Test
    public void createProjectWithPeople_listAllPeopleInProject() {
        Project project = new Project("OR Klient II", "ORII");
        Person person = new Person("mgs", "Mads", "Sørhaug");
        Set<Person> participants = new HashSet<>();
        participants.add(person);
        project.setParticipants(participants);
        projectDAO.save(project);

        assertThat(projectDAO.getProjectsWithParticipants("OR Klient II").getName(), is(project.getName()));
        assertThat(projectDAO.getProjectsWithParticipants("OR Klient II").getParticipants(), hasItem(person));

        projectDAO.delete(project);

        assertThat(projectDAO.getAllProjects().isEmpty(), is(true));
        assertThat(personDAO.getAllPersons().isEmpty(), is(false));

        personDAO.delete(person);

        assertThat(personDAO.getAllPersons().isEmpty(), is(true));
    }

    @Test
    public void createProject_codeIsUnique() {
        Project project = new Project("OR Klient II", "ORII");
        projectDAO.save(project);

        Project otherProject = new Project("Nytt Områderegister", "ORII");

        assertThat(projectDAO.save(otherProject), is(false));

        projectDAO.delete(project);

    }
}
