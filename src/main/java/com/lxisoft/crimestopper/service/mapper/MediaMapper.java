package com.lxisoft.crimestopper.service.mapper;

import com.lxisoft.crimestopper.domain.*;
import com.lxisoft.crimestopper.service.dto.MediaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Media and its DTO MediaDTO.
 */
@Mapper(componentModel = "spring", uses = {ComplaintMapper.class})
public interface MediaMapper extends EntityMapper<MediaDTO, Media> {

    @Mapping(source = "complaint.id", target = "complaintId")
    MediaDTO toDto(Media media);

    @Mapping(source = "complaintId", target = "complaint")
    Media toEntity(MediaDTO mediaDTO);

    default Media fromId(Long id) {
        if (id == null) {
            return null;
        }
        Media media = new Media();
        media.setId(id);
        return media;
    }
}
