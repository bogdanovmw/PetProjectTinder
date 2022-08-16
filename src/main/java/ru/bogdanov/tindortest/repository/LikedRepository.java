package ru.bogdanov.tindortest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdanov.tindortest.model.Liked;

@Repository
public interface LikedRepository extends JpaRepository<Liked, Long> {
}
