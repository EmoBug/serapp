package pl.sternik.kk.weekend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.entities.Status;
import pl.sternik.kk.weekend.repositories.SerAlreadyExistsException;
import pl.sternik.kk.weekend.repositories.SeryRepository;
import pl.sternik.kk.weekend.repositories.NoSuchSerException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private SeryRepository sery;

    @Override
    public List<Ser> findAll() {
        return sery.findAll();
    }

    @Override
    public List<Ser> findLatest3() {
        return sery.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ser> findById(Long id) {
        try {
            return Optional.of(sery.readById(id));
        } catch (NoSuchSerException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ser> create(Ser ser) {
        try {
            return Optional.of(sery.create(ser));
        } catch (SerAlreadyExistsException e) {
            try {
                return Optional.of(sery.readById(ser.getNumerKatalogowy()));
            } catch (NoSuchSerException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Ser> edit(Ser ser) {
        try {
            return Optional.of(sery.update(ser));
        } catch (NoSuchSerException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            sery.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchSerException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Ser> findAllToSell() {
        return sery.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.PRZETERMINOWANY))
                .collect(Collectors.toList());
    }
}
