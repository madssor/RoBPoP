package no.brreg.robpop.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 22:09
 */
@Entity
public class Project {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToMany
    @Cascade(SAVE_UPDATE)
    @JoinTable(name = "person_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private Set<Person> participants;

    public Project(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Project() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Person> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;

        return code.equals(project.code);

    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
