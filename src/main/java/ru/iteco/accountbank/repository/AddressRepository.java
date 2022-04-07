package ru.iteco.accountbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.accountbank.model.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
