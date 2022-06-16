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

            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA"); // 변경 감지

            // 준영속 상태로 전환
            // --> 트랜잭션 커밋 시 변경 감지로 인한 업데이트 쿼리 실행 X
            //em.detach(member);
            
            // 영속성 컨텍스트 초기화
            // --> 아래 em.find() 실행하면 셀렉트 쿼리가 다시 실행됨
            em.clear();
            
            Member member2 = em.find(Member.class, 150L);

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
