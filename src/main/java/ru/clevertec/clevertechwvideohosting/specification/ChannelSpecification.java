package ru.clevertec.clevertechwvideohosting.specification;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.clevertechwvideohosting.model.Channel;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ChannelSpecification implements Specification<Channel> {
    private final String name;
    private final String language;
    private final Long categoryId;

    @Override
    public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (language != null && !language.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("language"), language));
        }

        if (categoryId != null) {
            predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

