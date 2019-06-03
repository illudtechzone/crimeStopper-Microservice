package com.lxisoft.crimestopper.service.mapper;

import com.lxisoft.crimestopper.domain.*;
import com.lxisoft.crimestopper.service.dto.UserResponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserResponse and its DTO UserResponseDTO.
 */
@Mapper(componentModel = "spring", uses = {ComplaintMapper.class, CommentMapper.class, ReplyMapper.class})
public interface UserResponseMapper extends EntityMapper<UserResponseDTO, UserResponse> {

    @Mapping(source = "complaint.id", target = "complaintId")
    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "reply.id", target = "replyId")
    UserResponseDTO toDto(UserResponse userResponse);

    @Mapping(source = "complaintId", target = "complaint")
    @Mapping(source = "commentId", target = "comment")
    @Mapping(source = "replyId", target = "reply")
    UserResponse toEntity(UserResponseDTO userResponseDTO);

    default UserResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        return userResponse;
    }
}
