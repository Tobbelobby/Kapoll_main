package Kapoll_db.daoImplementation;


import javax.persistence.*;


public abstract class MainImplementation<K> implements KapDao<K> {

    public static final String PERSISTENCE_UNIT_NAME = "experiment-2";


    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    EntityTransaction emt = em.getTransaction();


    @Override
    public void save(K k){
        emt.begin();
        em.merge(k);
        emt.commit();
        em.close();
        factory.close();
    }

    @Override
    public void addAndSave(K k){
        emt.begin();
        em.persist(k);
        emt.commit();
        em.close();
        factory.close();
    }




    @Override
    public void delete(K k) {
        emt.begin();
        em.remove(em.merge(k));
        emt.commit();
        em.close();
        factory.close();
        removeFromList(k);


    }
}
