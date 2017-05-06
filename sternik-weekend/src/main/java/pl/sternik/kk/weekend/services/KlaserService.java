package pl.sternik.kk.weekend.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.kk.weekend.entities.Ser;


public interface KlaserService {
    List<Ser> findAll();

    List<Ser> findAllToSell();

    Optional<Ser> findById(Long id);

    Optional<Ser> create(Ser ser);

    Optional<Ser> edit(Ser ser);

    Optional<Boolean> deleteById(Long id);

    List<Ser> findLatest3();
}