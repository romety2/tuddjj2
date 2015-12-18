package com.mielewczykl.hibernate.model.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NamedQueries({
        @NamedQuery(name = "religia.wszystkie", query = "SELECT r FROM Religia r"),
})

public class Religia {
    private Long id;
    private String religia;
    private String opis;
    private  ArrayList<Klasztor> klasztory;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ArrayList<Klasztor> getKlasztory() {
        return klasztory;
    }

    public void setKlasztory(ArrayList<Klasztor> klasztory) {
        this.klasztory = klasztory;
    }

    public String getReligia() {
        return religia;
    }
    public void setReligia(String religia) {
        this.religia = religia;
    }

    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
}

