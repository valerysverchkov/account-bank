package ru.iteco.accountbank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.accountbank.model.entity.BankBookEntity;
import ru.iteco.accountbank.model.entity.CurrencyEntity;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {

    List<BankBookEntity> findAllByUserId(Integer userId);

    Optional<BankBookEntity> findByUserIdAndNumberAndCurrency(Integer userId, String number, CurrencyEntity currency);

    void deleteAllByUserId(Integer userId);
}
