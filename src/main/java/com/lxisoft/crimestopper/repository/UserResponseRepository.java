package com.lxisoft.crimestopper.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lxisoft.crimestopper.domain.Complaint;
import com.lxisoft.crimestopper.domain.UserResponse;


/**
 * Spring Data  repository for the UserResponse entity.
 */
/**
 * @author sooraj pn
 *
 */
@SuppressWarnings("unused")
@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {


/**
 * count likes of complaints.
 */
	@Query(value = "select count(u) from UserResponse u where u.complaint"+"=:complaint and u.flag='like'")
	long countLikesOfComplaint(@Param("complaint") Complaint complaint);
	
	/**
	 * count dislikes of complaint
	 */
	@Query(value = "select count(u) from UserResponse u where u.complaint"+"=:complaint and u.flag='dislike'")
	long countDislikesOfComplaint(@Param("complaint") Complaint complaint);

	

	/** 
	*finding all userResponse by complaintId
	*
	*/
	
	
	Page<UserResponse> findAllUserResponseByComplaintId(Long complaintId, Pageable pageable);

	/**
	 * find all user Response by comment id
	 * 
	 */

	Page<UserResponse> findAllUserResponseByCommentId(Long commentId, Pageable pageable);
	
	/**
	 * find all user Response by reply id
	 * 
	 */
	
	Page<UserResponse> findAllUserResponseByReplyId(Long replyId, Pageable pageable);

	/**
	 * find  userResponce by user id and complaitID
	 * 
	 */
	
	Optional<UserResponse> findUserResponseByUserIdAndComplaintId(Long id, Long id2);

	 
}
