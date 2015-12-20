package com.mielewczykl.hibernate.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "religia.wszystkie", query = "SELECT r FROM Religia r"),
})
public class Religia{
    private Long id;
    private String religia;
    private String opis;

    private List<Klasztor> klasztory = new ArrayList<Klasztor>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "religia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Klasztor> getKlasztory() {
        return klasztory;
    }
    public void setKlasztory(List<Klasztor> klasztory) {
        this.klasztory =  klasztory;
    }

    @Column(nullable = false)
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

