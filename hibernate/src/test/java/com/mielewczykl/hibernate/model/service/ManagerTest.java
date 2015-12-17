package com.mielewczykl.hibernate.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mielewczykl.hibernate.model.domain.Klasztor;
import com.mielewczykl.hibernate.model.domain.Religia;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerTest {

    @Autowired
    Manager m;

    private final String religia1 = "religia1";
    private final String opis1 = "opis1";

    private final String religia2 = "religia2";
    private final String opis2 = "opis2";

    private final String klasztor1 = "klasztor1";
    private final String kontakt1 = "kontakt1";

    private final String klasztor2 = "klasztor2";
    private final String kontakt2 = "kontakt2";

    @Test
    public void sprawdzPobierzPoId() {

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        Long rId = m.dodaj(r);
        Long kId = m.dodaj(k);

        Klasztor ks = m.pobierzKlasztorPoId(kId);
        Religia rs = m.pobierzReligiePoId(rId);

        assertEquals(rId, rs.getId());
        assertEquals(religia1, rs.getReligia());
        assertEquals(opis1, rs.getOpis());

        assertEquals(kId, ks.getId());
        assertEquals(r.getReligia(), ks.getReligia().getReligia());
        assertEquals(r.getOpis(), ks.getReligia().getOpis());
        assertEquals(klasztor1, ks.getNazwa());
        assertEquals(kontakt1, ks.getKontakt());

    }

    @Test
    public void sprawdzDodaj() {

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        Long rId = m.dodaj(r);
        Long kId = m.dodaj(k);

        Klasztor ks = m.pobierzKlasztorPoId(kId);
        Religia rs = m.pobierzReligiePoId(rId);

        assertEquals(religia1, rs.getReligia());
        assertEquals(opis1, rs.getOpis());

        assertEquals(religia1, ks.getReligia().getReligia());
        assertEquals(opis1, ks.getReligia().getOpis());
        assertEquals(klasztor1, ks.getNazwa());
        assertEquals(kontakt1, ks.getKontakt());

    }

    @Test
    public void sprawdzEdytuj() {

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        Long rId = m.dodaj(r);
        Long kId = m.dodaj(k);

        m.edytuj(r, religia2, opis2);
        m.edytuj(k, r, klasztor2, kontakt2);

        Klasztor ks = m.pobierzKlasztorPoId(kId);
        Religia rs = m.pobierzReligiePoId(rId);

        assertEquals(religia2, rs.getReligia());
        assertEquals(opis2, rs.getOpis());

        assertEquals(religia2, ks.getReligia().getReligia());
        assertEquals(opis2, ks.getReligia().getOpis());
        assertEquals(klasztor2, ks.getNazwa());
        assertEquals(kontakt2, ks.getKontakt());
    }

    @Test
    public void sprawdzUsun() {

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        Long rId = m.dodaj(r);
        Long kId = m.dodaj(k);

        m.usun(r);
        m.usun(k);

        Klasztor ks = m.pobierzKlasztorPoId(kId);
        Religia rs = m.pobierzReligiePoId(rId);

        assertEquals(ks, null);

        assertEquals(rs, null);
    }

    @Test
    public void sprawdzDajWszystkie() {

        List<Klasztor> klasztory = m.dajWszystkieKlasztory();
        List<Religia> religie = m.dajWszystkieReligie();

        int ileK = klasztory.size();
        int ileR = religie.size();

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        m.dodaj(r);
        m.dodaj(k);

        klasztory = m.dajWszystkieKlasztory();
        religie = m.dajWszystkieReligie();

        assertEquals(ileK+1, klasztory.size());
        assertEquals(ileR+1, religie.size());

        for(Klasztor klasz : klasztory) {
            k = m.pobierzKlasztorPoId(klasz.getId());
            assertNotNull(k);
        }
        for(Religia rel : religie) {
            r = m.pobierzReligiePoId(rel.getId());
            assertNotNull(r);
        }
    }
}
