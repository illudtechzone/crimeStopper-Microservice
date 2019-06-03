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
 * ActionDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-14T12:14:49.695365900+05:30[Asia/Calcutta]")

public class ActionDTO   {
  @JsonProperty("approval")
  private Boolean approval = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("postId")
  private Long postId = null;

  /**
   * Gets or Sets reaction
   */
  public enum ReactionEnum {
    COMMENT("COMMENT"),
    
    REQUEST_TO_CLOSE("REQUEST_TO_CLOSE");

    private String value;

    ReactionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ReactionEnum fromValue(String text) {
      for (ReactionEnum b : ReactionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("reaction")
  private ReactionEnum reaction = null;

  @JsonProperty("takenOn")
  private OffsetDateTime takenOn = null;

  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("userName")
  private String userName = null;

  public ActionDTO approval(Boolean approval) {
    this.approval = approval;
    return this;
  }

  /**
   * Get approval
   * @return approval
  **/
  @ApiModelProperty(value = "")


  public Boolean isApproval() {
    return approval;
  }

  public void setApproval(Boolean approval) {
    this.approval = approval;
  }

  public ActionDTO description(String description) {
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

  public ActionDTO id(Long id) {
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

  public ActionDTO postId(Long postId) {
    this.postId = postId;
    return this;
  }

  /**
   * Get postId
   * @return postId
  **/
  @ApiModelProperty(value = "")


  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public ActionDTO reaction(ReactionEnum reaction) {
    this.reaction = reaction;
    return this;
  }

  /**
   * Get reaction
   * @return reaction
  **/
  @ApiModelProperty(value = "")


  public ReactionEnum getReaction() {
    return reaction;
  }

  public void setReaction(ReactionEnum reaction) {
    this.reaction = reaction;
  }

  public ActionDTO takenOn(OffsetDateTime takenOn) {
    this.takenOn = takenOn;
    return this;
  }

  /**
   * Get takenOn
   * @return takenOn
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getTakenOn() {
    return takenOn;
  }

  public void setTakenOn(OffsetDateTime takenOn) {
    this.takenOn = takenOn;
  }

  public ActionDTO userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ActionDTO userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActionDTO actionDTO = (ActionDTO) o;
    return Objects.equals(this.approval, actionDTO.approval) &&
        Objects.equals(this.description, actionDTO.description) &&
        Objects.equals(this.id, actionDTO.id) &&
        Objects.equals(this.postId, actionDTO.postId) &&
        Objects.equals(this.reaction, actionDTO.reaction) &&
        Objects.equals(this.takenOn, actionDTO.takenOn) &&
        Objects.equals(this.userId, actionDTO.userId) &&
        Objects.equals(this.userName, actionDTO.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(approval, description, id, postId, reaction, takenOn, userId, userName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionDTO {\n");
    
    sb.append("    approval: ").append(toIndentedString(approval)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    postId: ").append(toIndentedString(postId)).append("\n");
    sb.append("    reaction: ").append(toIndentedString(reaction)).append("\n");
    sb.append("    takenOn: ").append(toIndentedString(takenOn)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
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

