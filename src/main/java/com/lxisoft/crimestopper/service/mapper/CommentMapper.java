package com.lxisoft.crimestopper.service.mapper;

import com.lxisoft.crimestopper.domain.*;
import com.lxisoft.crimestopper.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comment and its DTO CommentDTO.
 */
@Mapper(componentModel = "spring", uses = {ComplaintMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "complaint.id", target = "complaintId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "complaintId", target = "complaint")
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "userResponses", ignore = true)
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
