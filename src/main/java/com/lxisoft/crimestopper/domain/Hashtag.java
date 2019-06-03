package com.lxisoft.crimestopper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Hashtag.
 */
@Entity
@Table(name = "hashtag")
public class Hashtag implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;

    @ManyToMany(mappedBy = "hashtags")
    @JsonIgnore
    private Set<Complaint> complaints = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Hashtag name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public Hashtag count(Long count) {
        this.count = count;
        return this;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public Hashtag complaints(Set<Complaint> complaints) {
        this.complaints = complaints;
        return this;
    }

    public Hashtag addComplaints(Complaint complaint) {
        this.complaints.add(complaint);
        complaint.getHashtags().add(this);
        return this;
    }

    public Hashtag removeComplaints(Complaint complaint) {
        this.complaints.remove(complaint);
        complaint.getHashtags().remove(this);
        return this;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
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
        Hashtag hashtag = (Hashtag) o;
        if (hashtag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hashtag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hashtag{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", count=" + getCount() +
            "}";
    }
}
