package cz.siemens.inventory.api.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.siemens.inventory.api.gen.model.InventoryState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InventoryRecord
 */
@Validated

public class InventoryRecord   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("inventoryState")
  private InventoryState inventoryState = null;

  @JsonProperty("comment")
  private String comment = null;

  public InventoryRecord id(Long id) {
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

  public InventoryRecord inventoryState(InventoryState inventoryState) {
    this.inventoryState = inventoryState;
    return this;
  }

  /**
   * Get inventoryState
   * @return inventoryState
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public InventoryState getInventoryState() {
    return inventoryState;
  }

  public void setInventoryState(InventoryState inventoryState) {
    this.inventoryState = inventoryState;
  }

  public InventoryRecord comment(String comment) {
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
    InventoryRecord inventoryRecord = (InventoryRecord) o;
    return Objects.equals(this.id, inventoryRecord.id) &&
        Objects.equals(this.inventoryState, inventoryRecord.inventoryState) &&
        Objects.equals(this.comment, inventoryRecord.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, inventoryState, comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryRecord {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    inventoryState: ").append(toIndentedString(inventoryState)).append("\n");
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

