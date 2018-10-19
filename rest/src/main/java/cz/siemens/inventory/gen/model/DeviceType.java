package cz.siemens.inventory.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.siemens.inventory.gen.model.Supplier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DeviceType
 */
@Validated

public class DeviceType   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("objectTypeName")
  private String objectTypeName = null;

  @JsonProperty("classification")
  private Integer classification = null;

  @JsonProperty("manufacturer")
  private String manufacturer = null;

  @JsonProperty("orderNumber")
  private String orderNumber = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("supplier")
  private Supplier supplier = null;

  @JsonProperty("price")
  private Double price = null;

  public DeviceType id(Long id) {
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

  public DeviceType objectTypeName(String objectTypeName) {
    this.objectTypeName = objectTypeName;
    return this;
  }

  /**
   * Get objectTypeName
   * @return objectTypeName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getObjectTypeName() {
    return objectTypeName;
  }

  public void setObjectTypeName(String objectTypeName) {
    this.objectTypeName = objectTypeName;
  }

  public DeviceType classification(Integer classification) {
    this.classification = classification;
    return this;
  }

  /**
   * Get classification
   * @return classification
  **/
  @ApiModelProperty(value = "")


  public Integer getClassification() {
    return classification;
  }

  public void setClassification(Integer classification) {
    this.classification = classification;
  }

  public DeviceType manufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

  /**
   * Get manufacturer
   * @return manufacturer
  **/
  @ApiModelProperty(value = "")


  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public DeviceType orderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
    return this;
  }

  /**
   * Get orderNumber
   * @return orderNumber
  **/
  @ApiModelProperty(value = "")


  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public DeviceType version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public DeviceType supplier(Supplier supplier) {
    this.supplier = supplier;
    return this;
  }

  /**
   * Get supplier
   * @return supplier
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public DeviceType price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(value = "")


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceType deviceType = (DeviceType) o;
    return Objects.equals(this.id, deviceType.id) &&
        Objects.equals(this.objectTypeName, deviceType.objectTypeName) &&
        Objects.equals(this.classification, deviceType.classification) &&
        Objects.equals(this.manufacturer, deviceType.manufacturer) &&
        Objects.equals(this.orderNumber, deviceType.orderNumber) &&
        Objects.equals(this.version, deviceType.version) &&
        Objects.equals(this.supplier, deviceType.supplier) &&
        Objects.equals(this.price, deviceType.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectTypeName, classification, manufacturer, orderNumber, version, supplier, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectTypeName: ").append(toIndentedString(objectTypeName)).append("\n");
    sb.append("    classification: ").append(toIndentedString(classification)).append("\n");
    sb.append("    manufacturer: ").append(toIndentedString(manufacturer)).append("\n");
    sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    supplier: ").append(toIndentedString(supplier)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

