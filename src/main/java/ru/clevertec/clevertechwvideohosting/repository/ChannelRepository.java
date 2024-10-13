package ru.clevertec.clevertechwvideohosting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.clevertec.clevertechwvideohosting.model.Channel;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    @Query("""
            select c from Channel c
            left join fetch c.subscribers s
            where (:name is null or c.name like %:name%)
            and (:language is null or c.language = :language)
            and (:categoryId is null or c.categoryId.id = :categoryId)
            """)
    Page<Channel> findAllByFilters(
            @Param("name") String name,
            @Param("language") String language,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

    @Query("""
        select c from Channel c
        left join fetch c.authorId a
        left join fetch c.categoryId cat
        where c.id = :id
        """)
    Optional<Channel> findByIdWithDetails(@Param("id") Long id);

}
