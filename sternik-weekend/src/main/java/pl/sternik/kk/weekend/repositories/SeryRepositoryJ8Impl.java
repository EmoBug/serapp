package pl.sternik.kk.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.entities.Status;


@Service
@Qualifier("lista")
public class SeryRepositoryJ8Impl implements SeryRepository {

    private List<Ser> sery = new ArrayList<Ser>() {
        {
            add(Ser.produceSer(1L, "Holandia", "Gouda", "Delikatny ser żółty w plastrach", new Date(), new BigDecimal("19.50"),
                    Status.NOWY));
            add(Ser.produceSer(2L, "Normandia", "Camembert", "Miekki ser podpuszczkowy", new Date(), new BigDecimal("28.90"),
                    Status.PRZETERMINOWANY));
            add(Ser.produceSer(3L, "Szwajcaria", "Ementaler", "Popularny ser z dziurami", new Date(), new BigDecimal("32.00"), Status.DOJRZEWAJACY));
            add(Ser.produceSer(4L, "Polska", "Radamer", "Miękki ser z dziurami", new Date(), new BigDecimal("34.00"),
                    Status.DOJRZEWAJACY));
        }
    };

    @Override
    public List<Ser> findAll() {
        return this.sery;
    }

    @Override
    public Ser readById(Long id) throws NoSuchSerException {
        return this.sery.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchSerException::new);
    }

/*    @Override
    public Ser create(Ser ser) {
        if (!ser.isEmpty()) {
            ser.setNumerKatalogowy(
                    this.sery.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            ser.setNumerKatalogowy(1L);
        }
        this.sery.add(ser);
        return ser;
    }*/

    @Override
    public Ser update(Ser ser) throws NoSuchSerException {
        for (int i = 0; i < this.sery.size(); i++) {
            if (Objects.equals(this.sery.get(i).getNumerKatalogowy(), ser.getNumerKatalogowy())) {
                this.sery.set(i, ser);
                return ser;
            }
        }
        throw new NoSuchSerException("Nie ma takiego Sera: " + ser.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchSerException {
        for (int i = 0; i < this.sery.size(); i++) {
            if (Objects.equals(this.sery.get(i).getNumerKatalogowy(), id)) {
                this.sery.remove(i);
            }
        }
        throw new NoSuchSerException("Nie ma takiego Sera: " + id);
    }

	@Override
	public Ser create(Ser Ser) throws SerAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

}
