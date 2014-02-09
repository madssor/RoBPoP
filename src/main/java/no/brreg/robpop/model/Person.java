package no.brreg.robpop.model;

import javax.persistence.*;
import java.util.Set;

/**
 * User: Mads Sørhaug (mgs)
 * Date: 06.02.14 20:12
 */
@Entity
public class Person {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    @ManyToMany(mappedBy = "participants")
    private Set<Project> projects;

    public Person(String username, String firstName, String lastName) {
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> project) {
        this.projects = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        return userName.equals(person.userName);

    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }
}
