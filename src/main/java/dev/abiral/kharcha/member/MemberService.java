package dev.abiral.kharcha.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.abiral.kharcha.group.Group;
import dev.abiral.kharcha.group.GroupService;
import dev.abiral.kharcha.member.MemberDtos.CreateMemberRequest;
import dev.abiral.kharcha.member.MemberDtos.MemberResponse;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository members;
    private final GroupService groupService;

    public MemberService(MemberRepository members, GroupService groupService) {
        this.members = members;
        this.groupService = groupService;
    }

    public List<MemberResponse> list(Long groupId) {
        groupService.require(groupId); // 404 if the group doesn't exist
        return members.findByGroupIdOrderByNameAsc(groupId).stream().map(MemberResponse::from).toList();
    }

    @Transactional
    public MemberResponse add(Long groupId, CreateMemberRequest req) {
        Group group = groupService.require(groupId);
        return MemberResponse.from(members.save(new Member(group, req.name().trim(), req.email())));
    }
}
