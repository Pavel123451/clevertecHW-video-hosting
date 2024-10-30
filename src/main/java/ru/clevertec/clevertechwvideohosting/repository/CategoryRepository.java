package ru.clevertec.clevertechwvideohosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.clevertechwvideohosting.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
