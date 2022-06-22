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
        tx.begin();

        try {
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            // member.setTeam(team)을 하지 않고
            // team.getMembers().add(member)만 한다면
            // MEMBER 테이블에 team_id 값이 null 로 들어간다!
            // 양방향 매핑시 객체 관계를 고려하면 setTeam, getMembers().add 둘 다 입력해야 한다
            em.persist(team);

            // 멤버 저장
            Member member = new Member();
            member.setName("member1");
            // 연관관계 편의 메소드
            //member.changeTeam(team);
            em.persist(member);

            // 연관관계 편의 메소드
            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("====================");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("====================");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        
        emf.close();
    }
}
