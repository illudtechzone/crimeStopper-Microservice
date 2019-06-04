package com.lxisoft.crimestopper.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.lxisoft.crimestopper.domain.enumeration.Status;

/**
 * A DTO for the Complaint entity.
 */
public class ComplaintDTO implements Serializable {

    private Long id;

    private String userId;

    private String subject;

    private String description;

    private Instant time;

    private Instant timeOfIncident;

    @Lob
    private byte[] media;

    private String mediaContentType;
    private Status status;

    private Long noOfLikes;

    private Long noOfDislikes;


    private Long locationId;

    private Set<DepartmentDTO> departments = new HashSet<>();

    private Set<ComplaintDTO> linkedComplaints = new HashSet<>();

    private Set<HashtagDTO> hashtags = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getTimeOfIncident() {
        return timeOfIncident;
    }

    public void setTimeOfIncident(Instant timeOfIncident) {
        this.timeOfIncident = timeOfIncident;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Set<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDTO> departments) {
        this.departments = departments;
    }

    public Set<ComplaintDTO> getLinkedComplaints() {
        return linkedComplaints;
    }

    public void setLinkedComplaints(Set<ComplaintDTO> complaints) {
        this.linkedComplaints = complaints;
    }

    public Set<HashtagDTO> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<HashtagDTO> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintDTO complaintDTO = (ComplaintDTO) o;
        if (complaintDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), complaintDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", time='" + getTime() + "'" +
            ", timeOfIncident='" + getTimeOfIncident() + "'" +
            ", media='" + getMedia() + "'" +
            ", status='" + getStatus() + "'" +
            ", noOfLikes=" + getNoOfLikes() +
            ", noOfDislikes=" + getNoOfDislikes() +
            ", location=" + getLocationId() +
            "}";
    }
}
