package hello.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hello.enums.AuthorityName;


@Entity
@Table(name = "authority")
public class Authority {

    @Column(name = "name", length = 50)
    @NotNull
    @Id
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
