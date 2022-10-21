package Kapoll_db.daoImplementation;



import Kapoll_db.tables.Voters;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class VoterDaoImp extends MainImplementation {

    public static final String PERSISTENCE_UNIT_NAME = "experiment-2";

    List<Voters> voterList = new ArrayList<>();
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    EntityTransaction emt = em.getTransaction();


    public Voters update(Voters voter) throws Exception {
        try {
            if (voter.getVoter_id() == null)
                throw new Exception("ID cannot be blank");

            Voters updatedVoter = get(voter.getVoter_id());
            voterList.remove(updatedVoter);
            if (updatedVoter == null)
                throw new Exception("Employee not found");

            if (voter.getVote() != 0 || voter.getVote() != 1) {
                updatedVoter.setVote(voter.getVote());
            }

            voterList.add(updatedVoter);
            save(updatedVoter);
            return updatedVoter;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Voters get(Long id) {
        return em.find(Voters.class, id);
    }

    @Override
    public List<Voters> getAll() {
        Query query = em.createQuery("SELECT v FROM Voters v");
        voterList = query.getResultList();
        return voterList;
    }

    @Override
    public void removeFromList(Object o) {
        voterList.remove(o);
    }
    

    @Override
    public boolean exist(Long id) {
        if (!(get(id) == (null))) {
            return true;
        }
        else {
            return false;
        }
    }



}
