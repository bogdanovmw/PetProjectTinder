package ru.bogdanov.tindortest.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.exeptions.LikedNotFoundException;
import ru.bogdanov.tindortest.model.Liked;
import ru.bogdanov.tindortest.repository.LikedRepository;
import ru.bogdanov.tindortest.service.LikedService;

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
}
