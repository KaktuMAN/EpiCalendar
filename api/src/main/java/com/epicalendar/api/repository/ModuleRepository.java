package com.epicalendar.api.repository;

import com.epicalendar.api.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findAllByModuleSubscriptionsMail(String mail);
    Module findByCode(String code);
}
