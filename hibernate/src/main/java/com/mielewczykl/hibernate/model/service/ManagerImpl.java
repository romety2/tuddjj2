package com.mielewczykl.hibernate.model.service;

import com.mielewczykl.hibernate.model.domain.Klasztor;
import com.mielewczykl.hibernate.model.domain.Religia;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        klasztor.setId(null);
        return (Long) sf.getCurrentSession().save(klasztor);
    }

    @Override
    public Long dodaj(Religia religia) {
        religia.setId(null);
        return (Long) sf.getCurrentSession().save(religia);
    }

    @Override
    public void edytuj(Klasztor k, Religia religia, String nazwa, String kontakt) {
        k = (Klasztor) sf.getCurrentSession().get(Klasztor.class, k.getId());
        k.setReligia(religia);
        k.setNazwa(nazwa);
        k.setKontakt(kontakt);
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
}
