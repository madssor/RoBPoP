package no.brreg.robpop.dao;

import no.brreg.robpop.model.Person;
import no.brreg.robpop.model.Project;
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

    @Test
    public void createProject_listAllProjects() {
        Project project = new Project("OR Klient II");
        projectDAO.persist(project);

        assertThat(projectDAO.getAllProjects().size(), is(1));
    }

    @Test
    public void createProjectWithPeople_listAllPeopleInProject() {
        Project project = new Project("OR Klient II");
        Person person = new Person("Mads", "Sørhaug");
        Set<Person> participants = new HashSet<>();
        participants.add(person);
        project.setParticipants(participants);
        projectDAO.persist(project);

        assertThat(projectDAO.getProjectsWithParticipants("OR Klient II").getName(), is(project.getName()));
        assertThat(projectDAO.getProjectsWithParticipants("OR Klient II").getParticipants(), hasItem(person));
    }
}
