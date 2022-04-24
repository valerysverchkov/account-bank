package ru.iteco.accountbank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.accountbank.model.entity.UserAuthEntity;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    Optional<UserAuthEntity> findByUsername(String username);

}
