package ru.iteco.accountbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.accountbank.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
