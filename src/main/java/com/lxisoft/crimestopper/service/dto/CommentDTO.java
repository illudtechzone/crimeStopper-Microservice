package com.lxisoft.crimestopper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Comment entity.
 */
public class CommentDTO implements Serializable {

    private Long id;

    private Long userId;
    
    private String userName;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String description;

    private Long noOfLikes;

    private Long noOfDislikes;

    private Long noOfReplies;

    private Long complaintId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Long getNoOfDislikes() {
        return noOfDislikes;
    }

    public void setNoOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

    public Long getNoOfReplies() {
        return noOfReplies;
    }

    public void setNoOfReplies(Long noOfReplies) {
        this.noOfReplies = noOfReplies;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentDTO commentDTO = (CommentDTO) o;
        if (commentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", description='" + getDescription() + "'" +
            ", noOfLikes=" + getNoOfLikes() +
            ", noOfDislikes=" + getNoOfDislikes() +
            ", noOfReplies=" + getNoOfReplies() +
            ", complaint=" + getComplaintId() +
            "}";
    }
    
}
