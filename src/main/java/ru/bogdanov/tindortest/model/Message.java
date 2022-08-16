package ru.bogdanov.tindortest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sender;
    private int recipient;

    @Column(name = "added_at")
    private String addedAt;

    @Column(columnDefinition = "TEXT")
    private String message;
}
