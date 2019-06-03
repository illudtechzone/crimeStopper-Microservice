package com.lxisoft.crimestopper.client.red_alert.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lxisoft.crimestopper.client.red_alert.model.UserRegistrationDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserRegistrationDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-14T12:14:49.695365900+05:30[Asia/Calcutta]")

public class UserRegistrationDTO   {
  /**
   * Gets or Sets bloodGroup
   */
  public enum BloodGroupEnum {
    A_POSITIVE("A_POSITIVE"),
    
    A_NEGATIVE("A_NEGATIVE"),
    
    B_POSITIVE("B_POSITIVE"),
    
    B_NEGETIVE("B_NEGETIVE"),
    
    O_POSITIVE("O_POSITIVE"),
    
    O_NEGETIVE("O_NEGETIVE"),
    
    AB_POSITIVE("AB_POSITIVE"),
    
    AB_NEGETIVE("AB_NEGETIVE");

    private String value;

    BloodGroupEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static BloodGroupEnum fromValue(String text) {
      for (BloodGroupEnum b : BloodGroupEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("bloodGroup")
  private BloodGroupEnum bloodGroup = null;

  @JsonProperty("contact")
  private Long contact = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("dob")
  private OffsetDateTime dob = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("friends")
  @Valid
  private List<UserRegistrationDTO> friends = null;

  /**
   * Gets or Sets gender
   */
  public enum GenderEnum {
    MALE("MALE"),
    
    FEMALE("FEMALE"),
    
    OTHER("OTHER");

    private String value;

    GenderEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GenderEnum fromValue(String text) {
      for (GenderEnum b : GenderEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("gender")
  private GenderEnum gender = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("profilePic")
  private byte[] profilePic = null;

  @JsonProperty("profilePicContentType")
  private String profilePicContentType = null;

  @JsonProperty("score")
  private Integer score = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    RED("RED"),
    
    ORANGE("ORANGE"),
    
    GREEN("GREEN");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userName")
  private String userName = null;

  public UserRegistrationDTO bloodGroup(BloodGroupEnum bloodGroup) {
    this.bloodGroup = bloodGroup;
    return this;
  }

  /**
   * Get bloodGroup
   * @return bloodGroup
  **/
  @ApiModelProperty(value = "")


  public BloodGroupEnum getBloodGroup() {
    return bloodGroup;
  }

  public void setBloodGroup(BloodGroupEnum bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  public UserRegistrationDTO contact(Long contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
  **/
  @ApiModelProperty(value = "")


  public Long getContact() {
    return contact;
  }

  public void setContact(Long contact) {
    this.contact = contact;
  }

  public UserRegistrationDTO createdOn(OffsetDateTime createdOn) {
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

  public UserRegistrationDTO dob(OffsetDateTime dob) {
    this.dob = dob;
    return this;
  }

  /**
   * Get dob
   * @return dob
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDob() {
    return dob;
  }

  public void setDob(OffsetDateTime dob) {
    this.dob = dob;
  }

  public UserRegistrationDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRegistrationDTO firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserRegistrationDTO friends(List<UserRegistrationDTO> friends) {
    this.friends = friends;
    return this;
  }

  public UserRegistrationDTO addFriendsItem(UserRegistrationDTO friendsItem) {
    if (this.friends == null) {
      this.friends = new ArrayList<UserRegistrationDTO>();
    }
    this.friends.add(friendsItem);
    return this;
  }

  /**
   * Get friends
   * @return friends
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UserRegistrationDTO> getFriends() {
    return friends;
  }

  public void setFriends(List<UserRegistrationDTO> friends) {
    this.friends = friends;
  }

  public UserRegistrationDTO gender(GenderEnum gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(value = "")


  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }

  public UserRegistrationDTO id(Long id) {
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

  public UserRegistrationDTO lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserRegistrationDTO password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRegistrationDTO profilePic(byte[] profilePic) {
    this.profilePic = profilePic;
    return this;
  }

  /**
   * Get profilePic
   * @return profilePic
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(byte[] profilePic) {
    this.profilePic = profilePic;
  }

  public UserRegistrationDTO profilePicContentType(String profilePicContentType) {
    this.profilePicContentType = profilePicContentType;
    return this;
  }

  /**
   * Get profilePicContentType
   * @return profilePicContentType
  **/
  @ApiModelProperty(value = "")


  public String getProfilePicContentType() {
    return profilePicContentType;
  }

  public void setProfilePicContentType(String profilePicContentType) {
    this.profilePicContentType = profilePicContentType;
  }

  public UserRegistrationDTO score(Integer score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
  **/
  @ApiModelProperty(value = "")


  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public UserRegistrationDTO status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public UserRegistrationDTO userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public UserRegistrationDTO userName(String userName) {
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
    UserRegistrationDTO userRegistrationDTO = (UserRegistrationDTO) o;
    return Objects.equals(this.bloodGroup, userRegistrationDTO.bloodGroup) &&
        Objects.equals(this.contact, userRegistrationDTO.contact) &&
        Objects.equals(this.createdOn, userRegistrationDTO.createdOn) &&
        Objects.equals(this.dob, userRegistrationDTO.dob) &&
        Objects.equals(this.email, userRegistrationDTO.email) &&
        Objects.equals(this.firstName, userRegistrationDTO.firstName) &&
        Objects.equals(this.friends, userRegistrationDTO.friends) &&
        Objects.equals(this.gender, userRegistrationDTO.gender) &&
        Objects.equals(this.id, userRegistrationDTO.id) &&
        Objects.equals(this.lastName, userRegistrationDTO.lastName) &&
        Objects.equals(this.password, userRegistrationDTO.password) &&
        Objects.equals(this.profilePic, userRegistrationDTO.profilePic) &&
        Objects.equals(this.profilePicContentType, userRegistrationDTO.profilePicContentType) &&
        Objects.equals(this.score, userRegistrationDTO.score) &&
        Objects.equals(this.status, userRegistrationDTO.status) &&
        Objects.equals(this.userId, userRegistrationDTO.userId) &&
        Objects.equals(this.userName, userRegistrationDTO.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bloodGroup, contact, createdOn, dob, email, firstName, friends, gender, id, lastName, password, profilePic, profilePicContentType, score, status, userId, userName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegistrationDTO {\n");
    
    sb.append("    bloodGroup: ").append(toIndentedString(bloodGroup)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    friends: ").append(toIndentedString(friends)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    profilePic: ").append(toIndentedString(profilePic)).append("\n");
    sb.append("    profilePicContentType: ").append(toIndentedString(profilePicContentType)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

