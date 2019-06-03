package com.lxisoft.crimestopper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Reply.
 */
@Entity
@Table(name = "reply")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "no_of_likes")
    private Long noOfLikes;

    @Column(name = "no_of_dislikes")
    private Long noOfDislikes;

    @ManyToOne
    @JsonIgnoreProperties("replies")
    private Comment comment;

    @OneToMany(mappedBy = "reply")
    private Set<UserResponse> userResponses = new HashSet<>();
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

    public Reply userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNoOfLikes() {
        return noOfLikes;
    }

    public Reply noOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
        return this;
    }

    public void setNoOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Long getNoOfDislikes() {
        return noOfDislikes;
    }

    public Reply noOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
        return this;
    }

    public void setNoOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

    public Comment getComment() {
        return comment;
    }

    public Reply comment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Set<UserResponse> getUserResponses() {
        return userResponses;
    }

    public Reply userResponses(Set<UserResponse> userResponses) {
        this.userResponses = userResponses;
        return this;
    }

    public Reply addUserResponses(UserResponse userResponse) {
        this.userResponses.add(userResponse);
        userResponse.setReply(this);
        return this;
    }

    public Reply removeUserResponses(UserResponse userResponse) {
        this.userResponses.remove(userResponse);
        userResponse.setReply(null);
        return this;
    }

    public void setUserResponses(Set<UserResponse> userResponses) {
        this.userResponses = userResponses;
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
        Reply reply = (Reply) o;
        if (reply.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reply.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reply{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", noOfLikes=" + getNoOfLikes() +
            ", noOfDislikes=" + getNoOfDislikes() +
            "}";
    }
}
