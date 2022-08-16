package ru.bogdanov.tindortest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdanov.tindortest.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
