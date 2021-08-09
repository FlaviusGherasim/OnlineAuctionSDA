package com.example.onlineAuction.repository;

import com.example.onlineAuction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
