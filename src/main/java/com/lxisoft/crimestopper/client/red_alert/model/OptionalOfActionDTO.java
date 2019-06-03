package com.lxisoft.crimestopper.client.red_alert.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OptionalOfActionDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-01-14T12:14:49.695365900+05:30[Asia/Calcutta]")

public class OptionalOfActionDTO   {
  @JsonProperty("present")
  private Boolean present = null;

  public OptionalOfActionDTO present(Boolean present) {
    this.present = present;
    return this;
  }

  /**
   * Get present
   * @return present
  **/
  @ApiModelProperty(value = "")


  public Boolean isPresent() {
    return present;
  }

  public void setPresent(Boolean present) {
    this.present = present;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionalOfActionDTO optionalOfActionDTO = (OptionalOfActionDTO) o;
    return Objects.equals(this.present, optionalOfActionDTO.present);
  }

  @Override
  public int hashCode() {
    return Objects.hash(present);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OptionalOfActionDTO {\n");
    
    sb.append("    present: ").append(toIndentedString(present)).append("\n");
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

