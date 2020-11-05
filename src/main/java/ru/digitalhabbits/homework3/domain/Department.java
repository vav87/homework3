package ru.digitalhabbits.homework3.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "persons")
@Accessors(chain = true)
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 80, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "BOOL NOT NULL DEFAULT FALSE")
    private boolean closed;

    @OneToMany(mappedBy = "department")
    private Set<Person> persons = new HashSet<Person>();
}