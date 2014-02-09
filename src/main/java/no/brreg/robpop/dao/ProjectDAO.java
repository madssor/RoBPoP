package no.brreg.robpop.dao;

import no.brreg.robpop.HibernateUtil;
import no.brreg.robpop.model.Project;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 22:28
 */
public class ProjectDAO {
    public void persist(Project project) {
        HibernateUtil.persist(project);
    }

    public List<Project> getAllProjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Må ha session for å lage criteria
        Criteria criteria = session.createCriteria(Project.class);

        List<Project> projects = HibernateUtil.runCriteria(criteria, session);

        //Sessionen har et litt lengre scope enn ved runQuery
        session.close();
        return projects;
    }

    public Project getProjectsWithParticipants(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session
                .createCriteria(Project.class)
                .setFetchMode("participants", FetchMode.JOIN)
                .add(Restrictions.eq("name", name));

        Project project = HibernateUtil.runCriteriaSingle(criteria, session);

        session.close();
        return project;
    }

    public void delete(Project project) {
        HibernateUtil.delete(project);
    }
}
