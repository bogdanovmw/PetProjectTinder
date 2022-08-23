package ru.bogdanov.tindortest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikedResponse {
    private Long id;
    private Long userId;
    private Long userLikedId;
    private boolean status;
    private String name;
    private String photo;
}
