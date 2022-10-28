package com.example.Kapoll.Kapoll_db.daoImplementation;


import javax.persistence.*;


public abstract class MainImplementation<K> implements KapDao<K> {

    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction emt = em.getTransaction();

    public MainImplementation(EntityManagerFactory entityManagerFactory, EntityManager em) {
        this.entityManagerFactory = entityManagerFactory;
        this.em = em;
    }

    public MainImplementation() {

    }

    @Override
    public void save(K k){
        emt.begin();
        em.merge(k);
        emt.commit();

    }

    @Override
    public void addAndSave(K k){
        emt.begin();
        em.persist(k);
        emt.commit();
    }




    @Override
    public void delete(K k) {
        emt.begin();
        em.remove(em.merge(k));
        emt.commit();
        removeFromList(k);


    }

    @Override
    public void close() {
        em.close();
        entityManagerFactory.close();
    }
}
