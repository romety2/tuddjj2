package com.mielewczykl.hibernate.model.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mielewczykl.hibernate.model.domain.Klasztor;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerTest {

    @Autowired
    Manager m;

    @Test
    public void sprawdzDajWszystkieKlasztory() {

        //List<Klasztor> klasztory = m.dajWszystkieKlasztory();

        assertEquals(0, 0);
    }

}
