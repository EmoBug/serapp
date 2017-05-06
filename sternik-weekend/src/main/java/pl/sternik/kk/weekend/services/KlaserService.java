package pl.sternik.kk.weekend.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.kk.weekend.entities.Moneta;


public interface KlaserService {
    List<Moneta> findAll();

    List<Moneta> findAllToSell();

    Optional<Moneta> findById(Long id);

    Optional<Moneta> create(Moneta moneta);

    Optional<Moneta> edit(Moneta moneta);

    Optional<Boolean> deleteById(Long id);

    List<Moneta> findLatest3();
}