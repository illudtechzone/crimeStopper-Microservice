package com.lxisoft.crimestopper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "no_of_likes")
    private Long noOfLikes;

    @Column(name = "no_of_dislikes")
    private Long noOfDislikes;

    @Column(name = "no_of_replies")
    private Long noOfReplies;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private Complaint complaint;

    @OneToMany(mappedBy = "comment")
    private Set<Reply> replies = new HashSet<>();
    @OneToMany(mappedBy = "comment")
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

    public Comment userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public Comment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNoOfLikes() {
        return noOfLikes;
    }

    public Comment noOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
        return this;
    }

    public void setNoOfLikes(Long noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Long getNoOfDislikes() {
        return noOfDislikes;
    }

    public Comment noOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
        return this;
    }

    public void setNoOfDislikes(Long noOfDislikes) {
        this.noOfDislikes = noOfDislikes;
    }

    public Long getNoOfReplies() {
        return noOfReplies;
    }

    public Comment noOfReplies(Long noOfReplies) {
        this.noOfReplies = noOfReplies;
        return this;
    }

    public void setNoOfReplies(Long noOfReplies) {
        this.noOfReplies = noOfReplies;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public Comment complaint(Complaint complaint) {
        this.complaint = complaint;
        return this;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public Comment replies(Set<Reply> replies) {
        this.replies = replies;
        return this;
    }

    public Comment addReplies(Reply reply) {
        this.replies.add(reply);
        reply.setComment(this);
        return this;
    }

    public Comment removeReplies(Reply reply) {
        this.replies.remove(reply);
        reply.setComment(null);
        return this;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Set<UserResponse> getUserResponses() {
        return userResponses;
    }

    public Comment userResponses(Set<UserResponse> userResponses) {
        this.userResponses = userResponses;
        return this;
    }

    public Comment addUserResponses(UserResponse userResponse) {
        this.userResponses.add(userResponse);
        userResponse.setComment(this);
        return this;
    }

    public Comment removeUserResponses(UserResponse userResponse) {
        this.userResponses.remove(userResponse);
        userResponse.setComment(null);
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
        Comment comment = (Comment) o;
        if (comment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", description='" + getDescription() + "'" +
            ", noOfLikes=" + getNoOfLikes() +
            ", noOfDislikes=" + getNoOfDislikes() +
            ", noOfReplies=" + getNoOfReplies() +
            "}";
    }
}
