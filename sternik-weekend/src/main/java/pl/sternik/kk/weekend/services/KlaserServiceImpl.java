package pl.sternik.kk.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.repositories.SerAlreadyExistsException;
import pl.sternik.kk.weekend.repositories.SeryRepository;
import pl.sternik.kk.weekend.repositories.NoSuchSerException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private SeryRepository bazaDanych;

    @Override
    public List<Ser> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Ser> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Ser> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchSerException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ser> create(Ser ser) {
        try {
            return Optional.of(bazaDanych.create(ser));
        } catch (SerAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ser> edit(Ser ser) {
        try {
            return Optional.of(bazaDanych.update(ser));
        } catch (NoSuchSerException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchSerException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Ser> findLatest3() {
        return Collections.emptyList();
    }

}
