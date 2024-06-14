package com.lcwd.UserServic.Repository;

import com.lcwd.UserServic.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}