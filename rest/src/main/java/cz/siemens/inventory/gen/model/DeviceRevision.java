package cz.siemens.inventory.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DeviceRevision
 */
@Validated

public class DeviceRevision   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("revisionInterval")
  private Integer revisionInterval = null;

  @JsonProperty("lastRevisionDateString")
  private String lastRevisionDateString = null;

  public DeviceRevision id(Long id) {
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

  public DeviceRevision revisionInterval(Integer revisionInterval) {
    this.revisionInterval = revisionInterval;
    return this;
  }

  /**
   * Get revisionInterval
   * @return revisionInterval
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getRevisionInterval() {
    return revisionInterval;
  }

  public void setRevisionInterval(Integer revisionInterval) {
    this.revisionInterval = revisionInterval;
  }

  public DeviceRevision lastRevisionDateString(String lastRevisionDateString) {
    this.lastRevisionDateString = lastRevisionDateString;
    return this;
  }

  /**
   * Get lastRevisionDateString
   * @return lastRevisionDateString
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLastRevisionDateString() {
    return lastRevisionDateString;
  }

  public void setLastRevisionDateString(String lastRevisionDateString) {
    this.lastRevisionDateString = lastRevisionDateString;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceRevision deviceRevision = (DeviceRevision) o;
    return Objects.equals(this.id, deviceRevision.id) &&
        Objects.equals(this.revisionInterval, deviceRevision.revisionInterval) &&
        Objects.equals(this.lastRevisionDateString, deviceRevision.lastRevisionDateString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, revisionInterval, lastRevisionDateString);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceRevision {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    revisionInterval: ").append(toIndentedString(revisionInterval)).append("\n");
    sb.append("    lastRevisionDateString: ").append(toIndentedString(lastRevisionDateString)).append("\n");
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

