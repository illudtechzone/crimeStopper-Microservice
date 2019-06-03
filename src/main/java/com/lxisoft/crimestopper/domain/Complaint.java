package com.lxisoft.crimestopper.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.lxisoft.crimestopper.domain.enumeration.Status;

/**
 * A Complaint.
 */
@Entity
@Table(name = "complaint")
public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "subject")
	private String subject;

	@Column(name = "description")
	private String description;

	@Column(name = "jhi_time")
	private Instant time;

	@Column(name = "time_of_incident")
	private Instant timeOfIncident;

	@Lob
	@Column(name = "media")
	private byte[] media;

	@Column(name = "media_content_type")
	private String mediaContentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "no_of_likes")
	private Long noOfLikes;

	@Column(name = "no_of_dislikes")
	private Long noOfDislikes;

	@OneToMany(mappedBy = "complaint")
	private Set<UserResponse> userResponses = new HashSet<>();
	@OneToMany(mappedBy = "complaint")
	private Set<Comment> comments = new HashSet<>();
	@OneToMany(mappedBy = "complaint")
	private Set<Media> attachments = new HashSet<>();
	@ManyToOne
	private Location location;

	@ManyToMany
	@JoinTable(name = "complaint_department", joinColumns = @JoinColumn(name = "complaints_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "departments_id", referencedColumnName = "id"))
	private Set<Department> departments = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "complaint_linked_complaints", joinColumns = @JoinColumn(name = "complaints_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "linked_complaints_id", referencedColumnName = "id"))
	private Set<Complaint> linkedComplaints = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "complaint_hashtags", joinColumns = @JoinColumn(name = "complaints_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "hashtags_id", referencedColumnName = "id"))
	private Set<Hashtag> hashtags = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() { 
		return id;
	}

	public Set<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public Complaint userId(Long userId) {
		this.userId = userId;
		return this;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public Complaint subject(String subject) {
		this.subject = subject;
		return this;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public Complaint description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getTime() {
		return time;
	}

	public Complaint time(Instant time) {
		this.time = time;
		return this;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public Instant getTimeOfIncident() {
		return timeOfIncident;
	}

	public Complaint timeOfIncident(Instant timeOfIncident) {
		this.timeOfIncident = timeOfIncident;
		return this;
	}

	public void setTimeOfIncident(Instant timeOfIncident) {
		this.timeOfIncident = timeOfIncident;
	}

	public byte[] getMedia() {
		return media;
	}

	public Complaint media(byte[] media) {
		this.media = media;
		return this;
	}

	public void setMedia(byte[] media) {
		this.media = media;
	}

	public String getMediaContentType() {
		return mediaContentType;
	}

	public Complaint mediaContentType(String mediaContentType) {
		this.mediaContentType = mediaContentType;
		return this;
	}

	public void setMediaContentType(String mediaContentType) {
		this.mediaContentType = mediaContentType;
	}

	public Status getStatus() {
		return status;
	}

	public Complaint status(Status status) {
		this.status = status;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getNoOfLikes() {
		return noOfLikes;
	}

	public Complaint noOfLikes(Long noOfLikes) {
		this.noOfLikes = noOfLikes;
		return this;
	}

	public void setNoOfLikes(Long noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	public Long getNoOfDislikes() {
		return noOfDislikes;
	}

	public Complaint noOfDislikes(Long noOfDislikes) {
		this.noOfDislikes = noOfDislikes;
		return this;
	}

	public void setNoOfDislikes(Long noOfDislikes) {
		this.noOfDislikes = noOfDislikes;
	}

	public Set<UserResponse> getUserResponses() {
		return userResponses;
	}

	public Complaint userResponses(Set<UserResponse> userResponses) {
		this.userResponses = userResponses;
		return this;
	}

	public Complaint addUserResponses(UserResponse userResponse) {
		this.userResponses.add(userResponse);
		userResponse.setComplaint(this);
		return this;
	}

	public Complaint removeUserResponses(UserResponse userResponse) {
		this.userResponses.remove(userResponse);
		userResponse.setComplaint(null);
		return this;
	}

	public void setUserResponses(Set<UserResponse> userResponses) {
		this.userResponses = userResponses;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public Complaint comments(Set<Comment> comments) {
		this.comments = comments;
		return this;
	}

	public Complaint addComments(Comment comment) {
		this.comments.add(comment);
		comment.setComplaint(this);
		return this;
	}

	public Complaint removeComments(Comment comment) {
		this.comments.remove(comment);
		comment.setComplaint(null);
		return this;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Media> getAttachments() {
		return attachments;
	}

	public Complaint attachments(Set<Media> media) {
		this.attachments = media;
		return this;
	}

	public Complaint addAttachments(Media media) {
		this.attachments.add(media);
		media.setComplaint(this);
		return this;
	}

	public Complaint removeAttachments(Media media) {
		this.attachments.remove(media);
		media.setComplaint(null);
		return this;
	}

	public void setAttachments(Set<Media> media) {
		this.attachments = media;
	}

	public Location getLocation() {
		return location;
	}

	public Complaint location(Location location) {
		this.location = location;
		return this;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public Complaint departments(Set<Department> departments) {
		this.departments = departments;
		return this;
	}

	public Complaint addDepartment(Department department) {
		this.departments.add(department);
		department.getComplaints().add(this);
		return this;
	}

	public Complaint removeDepartment(Department department) {
		this.departments.remove(department);
		department.getComplaints().remove(this);
		return this;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Complaint> getLinkedComplaints() {
		return linkedComplaints;
	}

	public Complaint linkedComplaints(Set<Complaint> complaints) {
		this.linkedComplaints = complaints;
		return this;
	}

	public Complaint addLinkedComplaints(Complaint complaint) {
		this.linkedComplaints.add(complaint);
		return this;
	}

	public Complaint removeLinkedComplaints(Complaint complaint) {
		this.linkedComplaints.remove(complaint);
		return this;
	}

	public void setLinkedComplaints(Set<Complaint> complaints) {
		this.linkedComplaints = complaints;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Complaint complaint = (Complaint) o;
		if (complaint.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), complaint.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Complaint{" + "id=" + getId() + ", userId=" + getUserId() + ", subject='" + getSubject() + "'"
				+ ", description='" + getDescription() + "'" + ", time='" + getTime() + "'" + ", timeOfIncident='"
				+ getTimeOfIncident() + "'" + ", media='" + getMedia() + "'" + ", mediaContentType='"
				+ getMediaContentType() + "'" + ", status='" + getStatus() + "'" + ", noOfLikes=" + getNoOfLikes()
				+ ", noOfDislikes=" + getNoOfDislikes() + "}";
	}
 
	
}
