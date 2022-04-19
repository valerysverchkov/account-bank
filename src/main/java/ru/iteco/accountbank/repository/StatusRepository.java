package ru.iteco.accountbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.accountbank.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByName(String name);

}
