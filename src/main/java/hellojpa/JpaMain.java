package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {

            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");

            System.out.println("==========================");

            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback(); // 롤백
        } finally {
            em.close();
        }
        
        emf.close();
    }
}
