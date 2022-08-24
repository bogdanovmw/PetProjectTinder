package ru.bogdanov.tindortest.likedservice.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.likedservice.exeption.LikedNotFoundException;
import ru.bogdanov.tindortest.likedservice.model.Liked;
import ru.bogdanov.tindortest.likedservice.repository.LikedRepository;
import ru.bogdanov.tindortest.likedservice.service.LikedService;

import java.util.List;

@Service
public class LikedServiceImpl implements LikedService {
    private final LikedRepository likedRepository;

    public LikedServiceImpl(LikedRepository likedRepository) {
        this.likedRepository = likedRepository;
    }

    @Override
    public Liked findById(Long id) {
        return likedRepository.findById(id).orElseThrow(() -> new LikedNotFoundException(id));
    }

    @Override
    public Liked save(Liked liked) {
        return likedRepository.save(liked);
    }

    @Override
    public void delete(long id) {
        likedRepository.findById(id).orElseThrow(() -> new LikedNotFoundException(id));
        likedRepository.deleteById(id);
    }

    @Override
    public List<Liked> findAllByUserId(Long id) {
        return likedRepository.findAllByUserId(id);
    }
}
