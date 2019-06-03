package com.lxisoft.crimestopper.service.mapper;

import com.lxisoft.crimestopper.domain.*;
import com.lxisoft.crimestopper.service.dto.HashtagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Hashtag and its DTO HashtagDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HashtagMapper extends EntityMapper<HashtagDTO, Hashtag> {


    @Mapping(target = "complaints", ignore = true)
    Hashtag toEntity(HashtagDTO hashtagDTO);

    default Hashtag fromId(Long id) {
        if (id == null) {
            return null;
        }
        Hashtag hashtag = new Hashtag();
        hashtag.setId(id);
        return hashtag;
    }
}
