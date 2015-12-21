package com.mielewczykl.hibernate.model.service;

import com.mielewczykl.hibernate.model.domain.Klasztor;
import com.mielewczykl.hibernate.model.domain.Religia;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Transactional
public class ManagerImpl implements Manager {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Klasztor pobierzKlasztorPoId(Long id) {
        return (Klasztor) sf.getCurrentSession().get(Klasztor.class, id);
    }

    @Override
    public Religia pobierzReligiePoId(Long id) {
        return (Religia) sf.getCurrentSession().get(Religia.class, id);
    }

    @Override
    public Long dodaj(Klasztor klasztor) {
        Long id = (Long) sf.getCurrentSession().save(klasztor);
        klasztor.setId(id);
        Religia religia = (Religia) sf.getCurrentSession().get(Religia.class, klasztor.getReligia().getId());
        religia.getKlasztory().add(klasztor);
        return id;
    }

    @Override
    public Long dodaj(Religia religia) {
        Long id = (Long) sf.getCurrentSession().save(religia);
        religia.setId(id);
        return id;
    }

    @Override
    public void edytuj(Klasztor k, Religia religia, String nazwa, String kontakt) {
        k = (Klasztor) sf.getCurrentSession().get(Klasztor.class, k.getId());
        Religia r = (Religia) sf.getCurrentSession().get(Religia.class, k.getReligia().getId());
        int i = 0;
        for(Klasztor klasz : r.getKlasztory()) {
            if (klasz == k)
                break;
            i++;
        }
        k.setReligia(religia);
        k.setNazwa(nazwa);
        k.setKontakt(kontakt);
        r.getKlasztory().set(i, k);
        sf.getCurrentSession().update(k);
    }

    @Override
    public void edytuj(Religia r, String religia, String opis) {
        r = (Religia) sf.getCurrentSession().get(Religia.class, r.getId());
        r.setReligia(religia);
        r.setOpis(opis);
        sf.getCurrentSession().update(r);
    }

    @Override
    public void usun(Klasztor k) {
        k = (Klasztor) sf.getCurrentSession().get(Klasztor.class, k.getId());
        Religia r = (Religia) sf.getCurrentSession().get(Religia.class, k.getReligia().getId());
        r.getKlasztory().remove(k);
        sf.getCurrentSession().delete(k);
    }

    @Override
    public void usun(Religia r) {
        r = (Religia) sf.getCurrentSession().get(Religia.class, r.getId());
        sf.getCurrentSession().delete(r);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Klasztor> dajWszystkieKlasztory() {
        return sf.getCurrentSession().getNamedQuery("klasztor.wszystkie").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Religia> dajWszystkieReligie() {
        return sf.getCurrentSession().getNamedQuery("religia.wszystkie").list();
    }

    @Override
    public List<Klasztor> wyszukajKlasztoryWgWzorcaReligii(String wzorzec){
        List<Klasztor> lk = new ArrayList<Klasztor>();
        Pattern p = Pattern.compile(".*"+wzorzec+".*");
        Matcher m;
        for(Klasztor k : dajWszystkieKlasztory())
        {
            m = p.matcher(k.getReligia().getReligia());
            if(m.matches())
                lk.add(k);
        }
        return lk;
    }

    @Override
    public List<Klasztor> wyszukajKlasztory(Religia r){
        List<Klasztor> kw = dajWszystkieKlasztory();
        List<Klasztor> k = new ArrayList<Klasztor>();
        for (Klasztor klasz : kw)
            if(klasz.getReligia().getId() == r.getId())
                k.add(klasz);
        return k;
    }

    @Override
    public void usunZaleznosci(Religia r){
        List<Klasztor> klasztory = dajWszystkieKlasztory();
        for (Klasztor klasz : klasztory)
        {
            if(klasz.getReligia().getId() == r.getId())
                usun(klasz);
        }
    }
}
