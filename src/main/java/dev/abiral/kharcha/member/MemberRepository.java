package dev.abiral.kharcha.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByGroupIdOrderByNameAsc(Long groupId);
}
