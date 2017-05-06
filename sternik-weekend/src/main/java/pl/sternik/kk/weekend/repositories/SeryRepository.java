package pl.sternik.kk.weekend.repositories;

import java.util.List;

import pl.sternik.kk.weekend.entities.Ser;


public interface SeryRepository {
    Ser create(Ser Ser) throws SerAlreadyExistsException;
    Ser readById(Long id) throws NoSuchSerException;
    Ser update(Ser Ser) throws NoSuchSerException;
    void deleteById(Long id) throws NoSuchSerException;
    List<Ser> findAll();
}