package com.lxisoft.crimestopper.repository;

import com.lxisoft.crimestopper.domain.Hashtag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Hashtag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

}
