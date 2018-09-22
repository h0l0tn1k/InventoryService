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
 * DeviceCalibration
 */
@Validated

public class DeviceCalibration   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("calibrationInterval")
  private Integer calibrationInterval = null;

  public DeviceCalibration id(Long id) {
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

  public DeviceCalibration calibrationInterval(Integer calibrationInterval) {
    this.calibrationInterval = calibrationInterval;
    return this;
  }

  /**
   * Get calibrationInterval
   * @return calibrationInterval
  **/
  @ApiModelProperty(value = "")


  public Integer getCalibrationInterval() {
    return calibrationInterval;
  }

  public void setCalibrationInterval(Integer calibrationInterval) {
    this.calibrationInterval = calibrationInterval;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceCalibration deviceCalibration = (DeviceCalibration) o;
    return Objects.equals(this.id, deviceCalibration.id) &&
        Objects.equals(this.calibrationInterval, deviceCalibration.calibrationInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, calibrationInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceCalibration {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    calibrationInterval: ").append(toIndentedString(calibrationInterval)).append("\n");
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

