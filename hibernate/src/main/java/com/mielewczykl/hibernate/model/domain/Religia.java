package com.mielewczykl.hibernate.model.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "religia.wszystkie", query = "SELECT r FROM Religia r"),
})
public class Religia implements java.io.Serializable{
    private Long id;
    private String religia;
    private String opis;

    private Set<Klasztor> klasztory = new HashSet<Klasztor>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "religia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Klasztor> getKlasztory() {
        return klasztory;
    }
    public void setKlasztory(Set<Klasztor> klasztory) {
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

