package ru.atavrel.restserver.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atavrel.restserver.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findById(Long id);
    List<Role> findAll();
}

