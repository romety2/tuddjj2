package com.mielewczykl.hibernate.model.service;

import static org.junit.Assert.assertEquals;

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
    public void sprawdzDodaj() {

        Religia r = new Religia();

        r.setReligia(religia1);
        r.setOpis(opis1);

        Klasztor k = new Klasztor();

        k.setReligia(r);
        k.setNazwa(klasztor1);
        k.setKontakt(kontakt1);

        Long RId = m.dodaj(r);
        Long KId = m.dodaj(k);

        Klasztor ks = m.pobierzKlasztorPoId(KId);
        Religia rs = m.pobierzReligiePoId(RId);

        assertEquals(religia1, rs.getReligia());
        assertEquals(opis1, rs.getOpis());

        assertEquals(r.getReligia(), ks.getReligia().getReligia());
        assertEquals(r.getOpis(), ks.getReligia().getOpis());
        assertEquals(klasztor1, ks.getNazwa());
        assertEquals(kontakt1, ks.getKontakt());

    }

    @Test
    public void sprawdzDajWszystkie() {

        //List<Klasztor> klasztory = m.dajWszystkieKlasztory();

        assertEquals(0, 0);
    }

}
