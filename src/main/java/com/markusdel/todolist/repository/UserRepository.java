package com.markusdel.todolist.repository;

import com.markusdel.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
