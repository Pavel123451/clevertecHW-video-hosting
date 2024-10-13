package ru.clevertec.clevertechwvideohosting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 10)
    private String language;

    @Column(columnDefinition = "CLOB")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

    @ManyToMany(mappedBy = "subscribedChannels", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> subscribers;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;
}
