package ru.clevertec.clevertechwvideohosting.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import ru.clevertec.clevertechwvideohosting.model.Channel;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long>, JpaSpecificationExecutor<Channel> {

    @EntityGraph(attributePaths = {"subscribers"})
    Page<Channel> findAll(Specification<Channel> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "category"})
    Optional<Channel> findById(@Param("id") Long id);

}
