package dev.abiral.kharcha.member;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.abiral.kharcha.member.MemberDtos.CreateMemberRequest;
import dev.abiral.kharcha.member.MemberDtos.MemberResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/groups/{groupId}/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    List<MemberResponse> list(@PathVariable Long groupId) {
        return service.list(groupId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MemberResponse add(@PathVariable Long groupId, @Valid @RequestBody CreateMemberRequest req) {
        return service.add(groupId, req);
    }
}
