package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            // JPQL --> 기본!! + QueryDsl 실무 사용
            List<Member> resultList =
                    em.createQuery("select m from Member m where m.username like '%kim%'", Member.class)
                    .getResultList();
            */

            /*
            // Criteria --> 동적쿼리 작성하기 좋으나 실무에선 안 씀
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();
            */

            /*
            // Native query --> 잘 안 씀
            List<Member> resultList =
                    em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                            .getResultList();
            */
            // flush --> commit or em.query 조회 시 실행

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        emf.close();
    }
}
