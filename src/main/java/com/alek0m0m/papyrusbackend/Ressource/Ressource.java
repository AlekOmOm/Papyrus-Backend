package com.alek0m0m.papyrusbackend.Ressource;
import com.Alek0m0m.library.jpa.BaseEntity;
import com.alek0m0m.papyrusbackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ressource extends BaseEntity {

    private String name;
    private String author;
    private LocalDate fromDate;
    private LocalDate toDate;

    @ManyToMany
    @JoinTable(
            name = "user_ressource",
            joinColumns = @JoinColumn(name = "ressource_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    public Ressource(String name, String author, LocalDate fromDate, LocalDate toDate) {
        this.name = name;
        this.author = author;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

}