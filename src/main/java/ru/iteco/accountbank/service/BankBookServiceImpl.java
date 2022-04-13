package ru.iteco.accountbank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.iteco.accountbank.mapper.BankBookMapper;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.model.entity.BankBookEntity;
import ru.iteco.accountbank.model.entity.CurrencyEntity;
import ru.iteco.accountbank.model.exception.BankBookNotFoundException;
import ru.iteco.accountbank.model.exception.BankBookNumberCannotBeModifiedException;
import ru.iteco.accountbank.model.exception.BankBookWithCurrencyAlreadyHaveException;
import ru.iteco.accountbank.repository.BankBookRepository;
import ru.iteco.accountbank.repository.CurrencyRepository;

@Component
@Slf4j
public class BankBookServiceImpl implements BankBookService {

    private final BankBookRepository bankBookRepository;
    private final CurrencyRepository currencyRepository;
    private final BankBookMapper bankBookMapper;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, CurrencyRepository currencyRepository,
                               BankBookMapper bankBookMapper) {
        this.bankBookRepository = bankBookRepository;
        this.currencyRepository = currencyRepository;
        this.bankBookMapper = bankBookMapper;
    }

    @Override
    public BankBookDto findById(Integer id) {
        return bankBookRepository.findById(id)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new BankBookNotFoundException("Cчет не найден!"));
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());
        Optional<BankBookEntity> bankBookEntityOpt = bankBookRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currency
        );
        if (bankBookEntityOpt.isPresent()) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Лицевой счет не найден!"));

        if (!bankBookEntity.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }

        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());

        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public void delete(Integer id) {
        bankBookRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        bankBookRepository.deleteAllByUserId(userId);
    }

}
