package pl.sternik.kk.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.kk.weekend.entities.Ser;
import pl.sternik.kk.weekend.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements SeryRepository {

    private Ser[] baza;

    public ProstaBazaDanych() {
        baza = new Ser[15];
        Ser m = new Ser();
        m.setNumerKatalogowy(0L);
        m.setKrajPochodzenia("Holandia");
        m.setNazwa("Gouda");
        m.setOpis("Delikatny ser żółty w plastrach");
        m.setDataNabycia(new Date());
        m.setCenaNabycia(new BigDecimal("19.5"));
        m.setStatus(Status.NOWY);
        baza[0] = m;
        m = new Ser();
        m.setNumerKatalogowy(2L);
        m.setKrajPochodzenia("Normandia");
        m.setNazwa("Camembert");
        m.setOpis("Miękki ser podpuszczkowy");
        m.setDataNabycia(new Date());
        m.setCenaNabycia(new BigDecimal("28.90"));
        m.setStatus(Status.DOJRZEWAJACY);
        baza[2] = m;

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Ser[rozmiarBazy];
    }

    @Override
    public Ser create(Ser ser) throws SerAlreadyExistsException {
        if (ser.getNumerKatalogowy() != null && baza[ser.getNumerKatalogowy().intValue()] != null) {
            if (ser.getNumerKatalogowy().equals(baza[ser.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new SerAlreadyExistsException("W basie jest już ser o takim numerze");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = ser;
                ser.setNumerKatalogowy((long) i);
                return ser;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchSerException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchSerException("Niepoprawny numer katologowy");
        }
        baza[numerKatalogowy] = null;
    }

    @Override
    public Ser update(Ser ser) throws NoSuchSerException {
        int numerKatalogowy = ser.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchSerException("Niepoprawny numer katologowy");
        }

        Ser s = baza[ser.getNumerKatalogowy().intValue()];
        if (s == null) {
            throw new NoSuchSerException("Brak takiego sera.");
        } else {
            baza[ser.getNumerKatalogowy().intValue()] = ser;
        }
        return ser;
    }

    @Override
    public Ser readById(Long numerKatalogowy) throws NoSuchSerException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchSerException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Ser> findAll() {
        List<Ser> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
