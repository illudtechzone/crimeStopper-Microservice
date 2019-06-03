package com.lxisoft.crimestopper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.lxisoft.crimestopper.domain.enumeration.Flag;

/**
 * A UserResponse.
 */
@Entity
@Table(name = "user_response")
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "flag")
    private Flag flag;

    @ManyToOne
    @JsonIgnoreProperties("userResponses")
    private Complaint complaint;

    @ManyToOne
    @JsonIgnoreProperties("userResponses")
    private Comment comment;

    @ManyToOne
    @JsonIgnoreProperties("userResponses")
    private Reply reply;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public UserResponse userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Flag getFlag() {
        return flag;
    }

    public UserResponse flag(Flag flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public UserResponse complaint(Complaint complaint) {
        this.complaint = complaint;
        return this;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Comment getComment() {
        return comment;
    }

    public UserResponse comment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Reply getReply() {
        return reply;
    }

    public UserResponse reply(Reply reply) {
        this.reply = reply;
        return this;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserResponse userResponse = (UserResponse) o;
        if (userResponse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userResponse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserResponse{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", flag='" + getFlag() + "'" +
            "}";
    }
}
