package ru.bogdanov.tindortest.likedservice.service;

import ru.bogdanov.tindortest.likedservice.model.Liked;

import java.util.List;

public interface LikedService {
    Liked findById(Long id);
    Liked save(Liked liked);
    void delete(long id);
    List<Liked> findAllByUserId(Long id);
}
