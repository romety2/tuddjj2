package com.mielewczykl.hibernate.model.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "religia.wszystkie", query = "SELECT r FROM Religia r"),
})

public class Religia {
    private Long id;
    private String religia;
    private String opis;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
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

