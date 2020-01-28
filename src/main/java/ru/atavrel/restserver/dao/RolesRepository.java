package ru.atavrel.restserver.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atavrel.restserver.model.Role;

import java.util.Optional;

public interface RolesRepository extends CrudRepository<Role, Long> {

    Optional<Role> findById(Long id);

}

