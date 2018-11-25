package cz.siemens.inventory.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BorrowReturn
 */
@Validated

public class BorrowReturn   {
  @JsonProperty("device")
  private Device device = null;

  @JsonProperty("newHolder")
  private User newHolder = null;

  @JsonProperty("comment")
  private String comment = null;

  public BorrowReturn device(Device device) {
    this.device = device;
    return this;
  }

  /**
   * Get device
   * @return device
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  public BorrowReturn newHolder(User newHolder) {
    this.newHolder = newHolder;
    return this;
  }

  /**
   * Get newHolder
   * @return newHolder
  **/
  @ApiModelProperty(value = "")

  @Valid

  public User getNewHolder() {
    return newHolder;
  }

  public void setNewHolder(User newHolder) {
    this.newHolder = newHolder;
  }

  public BorrowReturn comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
  **/
  @ApiModelProperty(value = "")


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BorrowReturn borrowReturn = (BorrowReturn) o;
    return Objects.equals(this.device, borrowReturn.device) &&
        Objects.equals(this.newHolder, borrowReturn.newHolder) &&
        Objects.equals(this.comment, borrowReturn.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(device, newHolder, comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BorrowReturn {\n");
    
    sb.append("    device: ").append(toIndentedString(device)).append("\n");
    sb.append("    newHolder: ").append(toIndentedString(newHolder)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

