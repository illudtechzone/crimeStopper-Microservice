package com.lxisoft.crimestopper.client.red_alert.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PostDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-14T12:14:49.695365900+05:30[Asia/Calcutta]")

public class PostDTO   {
  @JsonProperty("active")
  private Boolean active = null;

  /**
   * Gets or Sets alertLevel
   */
  public enum AlertLevelEnum {
    RED("RED"),
    
    ORANGE("ORANGE"),
    
    GREEN("GREEN");

    private String value;

    AlertLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AlertLevelEnum fromValue(String text) {
      for (AlertLevelEnum b : AlertLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("alertLevel")
  private AlertLevelEnum alertLevel = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("userRegistrationId")
  private Long userRegistrationId = null;

  public PostDTO active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  **/
  @ApiModelProperty(value = "")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public PostDTO alertLevel(AlertLevelEnum alertLevel) {
    this.alertLevel = alertLevel;
    return this;
  }

  /**
   * Get alertLevel
   * @return alertLevel
  **/
  @ApiModelProperty(value = "")


  public AlertLevelEnum getAlertLevel() {
    return alertLevel;
  }

  public void setAlertLevel(AlertLevelEnum alertLevel) {
    this.alertLevel = alertLevel;
  }

  public PostDTO createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * Get createdOn
   * @return createdOn
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public PostDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PostDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PostDTO latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(value = "")


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public PostDTO longitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  **/
  @ApiModelProperty(value = "")


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public PostDTO userRegistrationId(Long userRegistrationId) {
    this.userRegistrationId = userRegistrationId;
    return this;
  }

  /**
   * Get userRegistrationId
   * @return userRegistrationId
  **/
  @ApiModelProperty(value = "")


  public Long getUserRegistrationId() {
    return userRegistrationId;
  }

  public void setUserRegistrationId(Long userRegistrationId) {
    this.userRegistrationId = userRegistrationId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostDTO postDTO = (PostDTO) o;
    return Objects.equals(this.active, postDTO.active) &&
        Objects.equals(this.alertLevel, postDTO.alertLevel) &&
        Objects.equals(this.createdOn, postDTO.createdOn) &&
        Objects.equals(this.description, postDTO.description) &&
        Objects.equals(this.id, postDTO.id) &&
        Objects.equals(this.latitude, postDTO.latitude) &&
        Objects.equals(this.longitude, postDTO.longitude) &&
        Objects.equals(this.userRegistrationId, postDTO.userRegistrationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(active, alertLevel, createdOn, description, id, latitude, longitude, userRegistrationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostDTO {\n");
    
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    alertLevel: ").append(toIndentedString(alertLevel)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    userRegistrationId: ").append(toIndentedString(userRegistrationId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

