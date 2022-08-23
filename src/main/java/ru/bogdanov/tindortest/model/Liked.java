package ru.bogdanov.tindortest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "liked")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_user")
    private long userId;

    @Column(name = "id_liked_user")
    private long userLikedId;

    @Column
    private Boolean status;
}
