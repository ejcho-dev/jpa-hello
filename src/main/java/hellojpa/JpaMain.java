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
            // member 저장하기
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member); // 저장
            */
            // member 조회, 삭제하기
            /*
            Member findMember = em.find(Member.class, 1L); // 단건조회
            em.remove(findMember); // 삭제
            */
            // member 수정하기
            /*
            Member findMember = em.find(Member.class, 1L); // 조회
            findMember.setName("HelloJPA"); // 수정
            */
            // 여러 건 조회 --> JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback(); // 롤백
        } finally {
            em.close();
        }
        
        emf.close();
    }
}
