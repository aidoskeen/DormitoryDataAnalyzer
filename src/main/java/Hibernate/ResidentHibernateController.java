package Hibernate;

import Models.Payment;
import Models.Resident;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ResidentHibernateController {

    private EntityManagerFactory entityManagerFactory = null;

    public ResidentHibernateController(EntityManagerFactory emf) { this.entityManagerFactory = emf; }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    public Resident getResidentById(String id) {
        EntityManager em = getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Resident> query = cb.createQuery(Resident.class);
        Root<Resident> root = query.from(Resident.class);
        query.select(root).where(cb.like(root.get("residentId"), id));
        Query q = em.createQuery(query);
        try {
            return (Resident) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void create(Resident resident) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resident);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resident> getAllResidents(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Resident.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public Resident getResidentById(int id) {
        EntityManager em = null;
        Resident resident = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resident = em.find(Resident.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such Resident");
        }
        return resident;
    }
}
