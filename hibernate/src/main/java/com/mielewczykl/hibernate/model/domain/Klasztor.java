package com.mielewczykl.hibernate.model.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "klasztor.wszystkie", query = "SELECT k FROM Klasztor k"),
})

public class Klasztor {
    private Long id;
    private Long religia;
    private String nazwa;
    private String kontakt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getReligia() {
        return religia;
    }
    public void setReligia(long religia) {
        this.religia = religia;
    }

    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKontakt() {
        return kontakt;
    }
    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
}
