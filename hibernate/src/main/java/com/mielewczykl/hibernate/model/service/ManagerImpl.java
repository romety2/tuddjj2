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
    @SuppressWarnings("unchecked")
    public List<Klasztor> dajWszystkieKlasztory() {
        return sf.getCurrentSession().getNamedQuery("klasztor.wszystkie").list();
    }
}
