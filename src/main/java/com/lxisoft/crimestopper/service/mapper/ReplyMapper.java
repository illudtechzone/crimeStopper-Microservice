package com.lxisoft.crimestopper.service.mapper;

import com.lxisoft.crimestopper.domain.*;
import com.lxisoft.crimestopper.service.dto.ReplyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reply and its DTO ReplyDTO.
 */
@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface ReplyMapper extends EntityMapper<ReplyDTO, Reply> {

    @Mapping(source = "comment.id", target = "commentId")
    ReplyDTO toDto(Reply reply);

    @Mapping(source = "commentId", target = "comment")
    @Mapping(target = "userResponses", ignore = true)
    Reply toEntity(ReplyDTO replyDTO);

    default Reply fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reply reply = new Reply();
        reply.setId(id);
        return reply;
    }
}
