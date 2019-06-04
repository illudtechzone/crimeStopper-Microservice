package com.lxisoft.crimestopper.repository;

import com.lxisoft.crimestopper.domain.UserResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {

}
