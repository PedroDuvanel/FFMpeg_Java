package com.clipicate.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clipicate.server.classes.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
