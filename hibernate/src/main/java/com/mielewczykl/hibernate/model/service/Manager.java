package com.mielewczykl.hibernate.model.service;

import com.mielewczykl.hibernate.model.domain.Klasztor;
import com.mielewczykl.hibernate.model.domain.Religia;

import java.util.List;

public interface Manager {

    Klasztor pobierzKlasztorPoId(Long id);
    Religia pobierzReligiePoId(Long id);

    Long dodaj(Klasztor klasztor);
    Long dodaj(Religia religia);

    void edytuj(Klasztor k, Religia religia, String nazwa, String kontakt);
    void edytuj(Religia r, String religia, String opis);

    void usun(Klasztor k);
    void usun(Religia r);

    List<Klasztor> dajWszystkieKlasztory();
    List<Religia> dajWszystkieReligie();

}
