package co.icesi.taskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.icesi.taskManager.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    
}
