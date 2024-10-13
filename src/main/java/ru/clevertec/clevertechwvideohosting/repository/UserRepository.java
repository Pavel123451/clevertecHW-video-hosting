package ru.clevertec.clevertechwvideohosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.clevertechwvideohosting.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
