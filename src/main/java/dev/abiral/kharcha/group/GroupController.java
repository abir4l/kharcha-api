package dev.abiral.kharcha.group;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abiral.kharcha.group.GroupDtos.CreateGroupRequest;
import dev.abiral.kharcha.group.GroupDtos.GroupResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    List<GroupResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    GroupResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    ResponseEntity<GroupResponse> create(@Valid @RequestBody CreateGroupRequest req) {
        GroupResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/groups/" + created.id())).body(created);
    }
}
