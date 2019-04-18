package com.jwielens.grocery.repositories;

import com.jwielens.grocery.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
