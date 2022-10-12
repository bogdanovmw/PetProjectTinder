package ru.bogdanov.tindortest.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdanov.tindortest.userservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIdNot(Long id);
    Optional<User> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByEmail(String name);
}
