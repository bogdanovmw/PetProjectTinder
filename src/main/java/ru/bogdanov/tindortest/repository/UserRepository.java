package ru.bogdanov.tindortest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdanov.tindortest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
