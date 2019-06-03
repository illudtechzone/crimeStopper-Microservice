package com.lxisoft.crimestopper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Hashtag entity.
 */
public class HashtagDTO implements Serializable,Comparable<HashtagDTO>{

    private Long id;

    private String name;

    private Long count;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HashtagDTO hashtagDTO = (HashtagDTO) o;
        if (hashtagDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hashtagDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HashtagDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", count=" + getCount() +
            "}";
    }

	@Override
	public int compareTo(HashtagDTO o) {
		Long l=o.getCount()-this.count;
		return l.intValue();
	}
}
