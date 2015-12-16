package com.mielewczykl.hibernate.model.service;

import com.mielewczykl.hibernate.model.domain.Klasztor;

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
    @SuppressWarnings("unchecked")
    public List<Klasztor> dajWszystkieKlasztory() {
        return sf.getCurrentSession().getNamedQuery("klasztor.wszystkie").list();
    }
}
