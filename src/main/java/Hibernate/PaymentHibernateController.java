package Hibernate;

import Models.Payment;
import Models.Resident;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PaymentHibernateController {

    private EntityManagerFactory entityManagerFactory = null;

    public PaymentHibernateController(EntityManagerFactory emf) { this.entityManagerFactory = emf; }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void create(Payment payment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(payment);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> getAllPayments(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Payment.class));
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

    public Payment getPaymentById(int id) {
        EntityManager em = null;
        Payment payment = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            payment = em.find(Payment.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such Resident");
        }
        return payment;
    }
}
