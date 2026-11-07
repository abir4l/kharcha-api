package dev.abiral.kharcha.group;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public final class GroupDtos {

    private GroupDtos() {
    }

    public record CreateGroupRequest(
            @NotBlank @Size(max = 100) String name,
            @NotBlank @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter ISO currency code") String baseCurrency) {
    }

    public record GroupResponse(Long id, String name, String baseCurrency, Instant createdAt) {

        static GroupResponse from(Group g) {
            return new GroupResponse(g.getId(), g.getName(), g.getBaseCurrency(), g.getCreatedAt());
        }
    }
}
