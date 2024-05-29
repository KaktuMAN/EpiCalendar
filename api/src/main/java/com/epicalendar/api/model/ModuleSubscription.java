package com.epicalendar.api.model;

import com.epicalendar.api.model.dto.PostModuleSubscription;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "module_subscriptions")
@Getter
@NoArgsConstructor
public class ModuleSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mail")
    private String mail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private Module module;

    public ModuleSubscription(PostModuleSubscription postModuleSubscription, Module module) {
        this.mail = postModuleSubscription.getMail();
        this.module = module;
    }
}
