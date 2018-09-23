package cz.siemens.inventory.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.siemens.inventory.gen.model.CompanyOwner;
import cz.siemens.inventory.gen.model.Department;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.gen.model.DeviceState;
import cz.siemens.inventory.gen.model.DeviceType;
import cz.siemens.inventory.gen.model.InventoryRecord;
import cz.siemens.inventory.gen.model.Project;
import cz.siemens.inventory.gen.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Device
 */
@Validated

public class Device   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("addDateString")
  private String addDateString = null;

  @JsonProperty("barcodeNumber")
  private String barcodeNumber = null;

  @JsonProperty("calibration")
  private DeviceCalibration calibration = null;

  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("companyOwner")
  private CompanyOwner companyOwner = null;

  @JsonProperty("defaultLocation")
  private String defaultLocation = null;

  @JsonProperty("department")
  private Department department = null;

  @JsonProperty("deviceState")
  private DeviceState deviceState = null;

  @JsonProperty("deviceType")
  private DeviceType deviceType = null;

  @JsonProperty("project")
  private Project project = null;

  @JsonProperty("revision")
  private DeviceRevision revision = null;

  @JsonProperty("inventoryRecord")
  private InventoryRecord inventoryRecord = null;

  @JsonProperty("serialNumber")
  private String serialNumber = null;

  @JsonProperty("holder")
  private User holder = null;

  @JsonProperty("owner")
  private User owner = null;

  public Device id(Long id) {
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

  public Device addDateString(String addDateString) {
    this.addDateString = addDateString;
    return this;
  }

  /**
   * Get addDateString
   * @return addDateString
  **/
  @ApiModelProperty(value = "")


  public String getAddDateString() {
    return addDateString;
  }

  public void setAddDateString(String addDateString) {
    this.addDateString = addDateString;
  }

  public Device barcodeNumber(String barcodeNumber) {
    this.barcodeNumber = barcodeNumber;
    return this;
  }

  /**
   * Get barcodeNumber
   * @return barcodeNumber
  **/
  @ApiModelProperty(value = "")


  public String getBarcodeNumber() {
    return barcodeNumber;
  }

  public void setBarcodeNumber(String barcodeNumber) {
    this.barcodeNumber = barcodeNumber;
  }

  public Device calibration(DeviceCalibration calibration) {
    this.calibration = calibration;
    return this;
  }

  /**
   * Get calibration
   * @return calibration
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DeviceCalibration getCalibration() {
    return calibration;
  }

  public void setCalibration(DeviceCalibration calibration) {
    this.calibration = calibration;
  }

  public Device comment(String comment) {
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

  public Device companyOwner(CompanyOwner companyOwner) {
    this.companyOwner = companyOwner;
    return this;
  }

  /**
   * Get companyOwner
   * @return companyOwner
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CompanyOwner getCompanyOwner() {
    return companyOwner;
  }

  public void setCompanyOwner(CompanyOwner companyOwner) {
    this.companyOwner = companyOwner;
  }

  public Device defaultLocation(String defaultLocation) {
    this.defaultLocation = defaultLocation;
    return this;
  }

  /**
   * Get defaultLocation
   * @return defaultLocation
  **/
  @ApiModelProperty(value = "")


  public String getDefaultLocation() {
    return defaultLocation;
  }

  public void setDefaultLocation(String defaultLocation) {
    this.defaultLocation = defaultLocation;
  }

  public Device department(Department department) {
    this.department = department;
    return this;
  }

  /**
   * Get department
   * @return department
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Device deviceState(DeviceState deviceState) {
    this.deviceState = deviceState;
    return this;
  }

  /**
   * Get deviceState
   * @return deviceState
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DeviceState getDeviceState() {
    return deviceState;
  }

  public void setDeviceState(DeviceState deviceState) {
    this.deviceState = deviceState;
  }

  public Device deviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
    return this;
  }

  /**
   * Get deviceType
   * @return deviceType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public Device project(Project project) {
    this.project = project;
    return this;
  }

  /**
   * Get project
   * @return project
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Device revision(DeviceRevision revision) {
    this.revision = revision;
    return this;
  }

  /**
   * Get revision
   * @return revision
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DeviceRevision getRevision() {
    return revision;
  }

  public void setRevision(DeviceRevision revision) {
    this.revision = revision;
  }

  public Device inventoryRecord(InventoryRecord inventoryRecord) {
    this.inventoryRecord = inventoryRecord;
    return this;
  }

  /**
   * Get inventoryRecord
   * @return inventoryRecord
  **/
  @ApiModelProperty(value = "")

  @Valid

  public InventoryRecord getInventoryRecord() {
    return inventoryRecord;
  }

  public void setInventoryRecord(InventoryRecord inventoryRecord) {
    this.inventoryRecord = inventoryRecord;
  }

  public Device serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNumber
   * @return serialNumber
  **/
  @ApiModelProperty(value = "")


  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Device holder(User holder) {
    this.holder = holder;
    return this;
  }

  /**
   * Get holder
   * @return holder
  **/
  @ApiModelProperty(value = "")

  @Valid

  public User getHolder() {
    return holder;
  }

  public void setHolder(User holder) {
    this.holder = holder;
  }

  public Device owner(User owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")

  @Valid

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Device device = (Device) o;
    return Objects.equals(this.id, device.id) &&
        Objects.equals(this.addDateString, device.addDateString) &&
        Objects.equals(this.barcodeNumber, device.barcodeNumber) &&
        Objects.equals(this.calibration, device.calibration) &&
        Objects.equals(this.comment, device.comment) &&
        Objects.equals(this.companyOwner, device.companyOwner) &&
        Objects.equals(this.defaultLocation, device.defaultLocation) &&
        Objects.equals(this.department, device.department) &&
        Objects.equals(this.deviceState, device.deviceState) &&
        Objects.equals(this.deviceType, device.deviceType) &&
        Objects.equals(this.project, device.project) &&
        Objects.equals(this.revision, device.revision) &&
        Objects.equals(this.inventoryRecord, device.inventoryRecord) &&
        Objects.equals(this.serialNumber, device.serialNumber) &&
        Objects.equals(this.holder, device.holder) &&
        Objects.equals(this.owner, device.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, addDateString, barcodeNumber, calibration, comment, companyOwner, defaultLocation, department, deviceState, deviceType, project, revision, inventoryRecord, serialNumber, holder, owner);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Device {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    addDateString: ").append(toIndentedString(addDateString)).append("\n");
    sb.append("    barcodeNumber: ").append(toIndentedString(barcodeNumber)).append("\n");
    sb.append("    calibration: ").append(toIndentedString(calibration)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    companyOwner: ").append(toIndentedString(companyOwner)).append("\n");
    sb.append("    defaultLocation: ").append(toIndentedString(defaultLocation)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    deviceState: ").append(toIndentedString(deviceState)).append("\n");
    sb.append("    deviceType: ").append(toIndentedString(deviceType)).append("\n");
    sb.append("    project: ").append(toIndentedString(project)).append("\n");
    sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
    sb.append("    inventoryRecord: ").append(toIndentedString(inventoryRecord)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    holder: ").append(toIndentedString(holder)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
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

