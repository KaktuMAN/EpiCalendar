package com.epicalendar.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "modules")
@Getter
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "year")
    private int year;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    List<Project> projects;

    @Setter
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    List<ModuleSubscription> moduleSubscriptions;

    public Module(String code, int year) {
        this.code = code;
        this.year = year;
    }
}
