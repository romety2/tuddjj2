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

import java.util.ArrayList;
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

    private List<Long>  dodaneKlasztory = new ArrayList<Long>();
    private List<Long>  dodaneReligie = new ArrayList<Long>();

    @Before
         public void sprawdzDodaneElementy() {

        List<Klasztor> klasztory = m.dajWszystkieKlasztory();
        List<Religia> religie = m.dajWszystkieReligie();

        for(Klasztor klasz : klasztory)
            dodaneKlasztory.add(klasz.getId());

        for(Religia rel : religie)
            dodaneReligie.add(rel.getId());
    }

    @After
    public void usunTestowaneDane() {

        List<Klasztor> klasztory = m.dajWszystkieKlasztory();

        List<Religia> religie = m.dajWszystkieReligie();

        boolean usun;

        for(Klasztor klasz : klasztory) {
            usun = true;
            for (Long klasz2 : dodaneKlasztory)
                if (klasz.getId() == klasz2) {
                usun = false;
                break;
                }
            if(usun)
                m.usun(klasz);
        }

        for(Religia rel : religie) {
            usun = true;
            for (Long rel2 : dodaneReligie)
                if (rel.getId() == rel2)
                    {
                        usun = false;
                        break;
                    }
            if(usun)
                m.usun(rel);
        }
    }

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

        List<Klasztor> klasztory = m.dajWszystkieKlasztory();
        List<Religia> religie = m.dajWszystkieReligie();

        m.edytuj(r, religia2, opis2);
        m.edytuj(k, r, klasztor2, kontakt2);


        int i = 0;
        int j = 0;

        List<Klasztor> klasztory2 = m.dajWszystkieKlasztory();
        List<Religia> religie2 = m.dajWszystkieReligie();

        for(Klasztor klasz : klasztory) {
            for (Klasztor klasz2 : klasztory2){
                if (klasz.getId() != kId && klasz.getId() == klasz2.getId()) {
                    assertEquals(klasz2.getReligia().getReligia(), klasz.getReligia().getReligia());
                    assertEquals(klasz2.getReligia().getOpis(), klasz.getReligia().getOpis());
                    assertEquals(klasz2.getNazwa(), klasz.getNazwa());
                    assertEquals(klasz2.getKontakt(), klasz.getKontakt());
                    i++;
                } else if (klasz.getId() == kId) {
                    assertEquals(religia2, klasz.getReligia().getReligia());
                    assertEquals(opis2, klasz.getReligia().getOpis());
                    assertEquals(klasztor2, klasz.getNazwa());
                    assertEquals(kontakt2, klasz.getKontakt());
                    j++;
                }
            }
        }

        assertEquals(j, 1);
        assertEquals(i+j, klasztory2.size());
        assertEquals(klasztory.size(), klasztory2.size());

        i = 0;
        j = 0;

        for(Religia rel : religie) {
            for (Klasztor rel2 : klasztory2){
                if (rel.getId() != rId && rel.getId() == rel2.getId()) {
                    assertEquals(religie2.get(i).getReligia(), rel.getReligia());
                    assertEquals(religie2.get(i).getOpis(), rel.getOpis());
                    i++;
                } else if (rel.getId() == rId) {
                    assertEquals(religia2, rel.getReligia());
                    assertEquals(opis2, rel.getOpis());
                    j++;
                }
            }
        }

        assertEquals(j, 1);
        assertEquals(i+j, klasztory2.size());
        assertEquals(klasztory.size(), klasztory2.size());
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

        List<Klasztor> klasztory = m.dajWszystkieKlasztory();
        List<Religia> religie = m.dajWszystkieReligie();

        m.usun(k);
        m.usun(r);

        int ileK = klasztory.size();
        int ileR = religie.size();

        Klasztor ks = m.pobierzKlasztorPoId(kId);
        Religia rs = m.pobierzReligiePoId(rId);

        assertEquals(ks, null);
        assertEquals(rs, null);

        List<Klasztor> klasztory2 = m.dajWszystkieKlasztory();
        List<Religia> religie2 = m.dajWszystkieReligie();

        assertEquals(klasztory2.size(), ileK-1);
        assertEquals(religie2.size(), ileR-1);

        int i = 0;

        for(Klasztor klasz : klasztory) {
            for(Klasztor klasz2 : klasztory2)
                if(klasz.getId() == klasz2.getId())
                {
                    assertEquals(klasz2.getReligia().getReligia(), klasz.getReligia().getReligia());
                    assertEquals(klasz2.getReligia().getOpis(), klasz.getReligia().getOpis());
                    assertEquals(klasz2.getNazwa(), klasz.getNazwa());
                    assertEquals(klasz2.getKontakt(), klasz.getKontakt());
                    i++;
                }
        }

        assertEquals(klasztory2.size(), i);

        i = 0;

        for(Religia rel : religie) {
            for(Religia rel2 : religie2)
            {
                if(rel.getId() == rel2.getId()) {
                    assertEquals(rel2.getReligia(), rel.getReligia());
                    assertEquals(rel2.getOpis(), rel.getOpis());
                    i++;
                }
            }
        }

        assertEquals(religie2.size(), i);
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
