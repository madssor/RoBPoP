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

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @Cascade(SAVE_UPDATE)
    @JoinTable(name = "person_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")})
    private Set<Person> participants;

    public Project(String name) {
        this.name = name;
    }

    public Project() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Person> participants) {
        this.participants = participants;
    }
}
