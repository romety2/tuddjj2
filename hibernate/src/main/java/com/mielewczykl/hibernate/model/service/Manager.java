package com.mielewczykl.hibernate.model.service;

import com.mielewczykl.hibernate.model.domain.Klasztor;
import com.mielewczykl.hibernate.model.domain.Religia;

import java.util.List;

public interface Manager {

    Klasztor pobierzKlasztorPoId(Long id);
    Religia pobierzReligiePoId(Long id);

    Long dodaj(Klasztor klasztor);
    Long dodaj(Religia religia);

    List<Klasztor> dajWszystkieKlasztory();

}
