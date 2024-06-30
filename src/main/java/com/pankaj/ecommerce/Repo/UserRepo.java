package com.pankaj.ecommerce.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankaj.ecommerce.Models.User;
import com.pankaj.ecommerce.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findFirstByEmail(String email);
    User findByRole(UserRole role);
}
