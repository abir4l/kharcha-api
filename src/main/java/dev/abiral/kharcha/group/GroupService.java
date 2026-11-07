package dev.abiral.kharcha.group;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.abiral.kharcha.common.NotFoundException;
import dev.abiral.kharcha.group.GroupDtos.CreateGroupRequest;
import dev.abiral.kharcha.group.GroupDtos.GroupResponse;

@Service
@Transactional(readOnly = true)
public class GroupService {

    private final GroupRepository groups;

    public GroupService(GroupRepository groups) {
        this.groups = groups;
    }

    public List<GroupResponse> list() {
        return groups.findAll().stream().map(GroupResponse::from).toList();
    }

    public GroupResponse get(Long id) {
        return GroupResponse.from(require(id));
    }

    @Transactional
    public GroupResponse create(CreateGroupRequest req) {
        return GroupResponse.from(groups.save(new Group(req.name().trim(), req.baseCurrency())));
    }

    /** Used by other services to load a group or fail with 404. */
    public Group require(Long id) {
        return groups.findById(id).orElseThrow(() -> new NotFoundException("Group", id));
    }
}
