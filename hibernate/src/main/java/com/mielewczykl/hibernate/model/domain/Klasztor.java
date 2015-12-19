package com.mielewczykl.hibernate.model.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "klasztor.wszystkie", query = "SELECT k FROM Klasztor k"),
})
public class Klasztor implements java.io.Serializable{
    private Long id;
    private Religia religia;
    private String nazwa;
    private String kontakt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Religia getReligia() {
        return religia;
    }
    public void setReligia(Religia religia) {
        this.religia = religia;
    }

    @Column(nullable = false)
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
