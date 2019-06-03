package com.lxisoft.crimestopper.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.lxisoft.crimestopper.domain.enumeration.Flag;

/**
 * A DTO for the UserResponse entity.
 */
public class UserResponseDTO implements Serializable {

    private Long id;

    private Long userId;

    private Flag flag;

    private Long complaintId;

    private Long commentId;

    private Long replyId;

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

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserResponseDTO userResponseDTO = (UserResponseDTO) o;
        if (userResponseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", flag='" + getFlag() + "'" +
            ", complaint=" + getComplaintId() +
            ", comment=" + getCommentId() +
            ", reply=" + getReplyId() +
            "}";
    }  
}
