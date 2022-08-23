package ru.bogdanov.tindortest.service;

import ru.bogdanov.tindortest.model.Liked;

import java.util.List;

public interface LikedService {
    Liked findById(Long id);
    Liked save(Liked liked);
    void delete(long id);
    List<Liked> findAllByUserId(Long id);
}
