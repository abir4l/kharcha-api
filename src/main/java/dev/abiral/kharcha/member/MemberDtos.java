package dev.abiral.kharcha.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class MemberDtos {

    private MemberDtos() {
    }

    public record CreateMemberRequest(@NotBlank @Size(max = 100) String name, @Email String email) {
    }

    public record MemberResponse(Long id, String name, String email) {

        static MemberResponse from(Member m) {
            return new MemberResponse(m.getId(), m.getName(), m.getEmail());
        }
    }
}
