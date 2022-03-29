package ru.iteco.accountbank.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.model.exception.BankBookNotFoundException;
import ru.iteco.accountbank.model.exception.BankBookNumberCannotBeModifiedException;
import ru.iteco.accountbank.model.exception.BankBookWithCurrencyAlreadyHaveException;

@Component
@Slf4j
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        bankBookDtoMap.put(1, BankBookDto.builder()
                .id(1)
                .userId(1)
                .number("num1")
                .amount(BigDecimal.TEN)
                .currency("RUB")
                .build()
        );
    }

    @Override
    public BankBookDto findById(Integer id) {
        BankBookDto bankBookDto = bankBookDtoMap.get(id);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Cчет не найден!");
        }
        return bankBookDto;
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> userId.equals(bankBookDto.getUserId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        boolean hasBankBook = bankBookDtoMap.values().stream()
                .anyMatch(bankBook -> bankBook.getUserId().equals(bankBookDto.getUserId())
                        && bankBook.getNumber().equals(bankBookDto.getNumber())
                        && bankBook.getCurrency().equals(bankBookDto.getCurrency()));
        if (hasBankBook) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }
        int id = sequenceId.getAndIncrement();
        bankBookDto.setId(id);
        bankBookDtoMap.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookDto bankBookDtoFromMap = bankBookDtoMap.get(bankBookDto.getId());
        if (bankBookDtoFromMap == null) {
            throw new BankBookNotFoundException("Лицевой счет не найден!");
        }
        if (!bankBookDtoFromMap.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }
        bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);
        return bankBookDto;
    }

    @Override
    public void delete(Integer id) {
        bankBookDtoMap.remove(id);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        List<Integer> bankBookRemoveId = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
                .map(BankBookDto::getId)
                .collect(Collectors.toList());

        for (Integer removeId : bankBookRemoveId) {
            bankBookDtoMap.remove(removeId);
        }
    }

    public void deleteByUserIdOther(Integer userId) {
        bankBookDtoMap.values().removeIf(bankBookDto -> bankBookDto.getUserId().equals(userId));
    }

}
