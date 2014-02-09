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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(project);

        session.getTransaction().commit();
        session.close();
    }

    public List<Project> getAllProjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Project.class);

        @SuppressWarnings("unchecked")
        List<Project> projects = criteria.list();

        session.getTransaction().commit();
        session.close();
        return projects;
    }

    public Project getProjectsWithParticipants(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session
                .createCriteria(Project.class)
                .setFetchMode("participants", FetchMode.JOIN)
                .add(Restrictions.eq("name", name));

        Project project = (Project)criteria.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return project;
    }

    public void delete(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.delete(project);

        session.getTransaction().commit();
        session.close();
    }
}
