package cz.siemens.inventory.api.gen.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated

public class User   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("superiorFirstName")
  private String superiorFirstName = null;

  @JsonProperty("superiorLastName")
  private String superiorLastName = null;

  @JsonProperty("flagRead")
  private Boolean flagRead = null;

  @JsonProperty("flagWrite")
  private Boolean flagWrite = null;

  @JsonProperty("flagBorrow")
  private Boolean flagBorrow = null;

  @JsonProperty("flagInventory")
  private Boolean flagInventory = null;

  @JsonProperty("flagRevision")
  private Boolean flagRevision = null;

  @JsonProperty("flagAdmin")
  private Boolean flagAdmin = null;

  public User id(Long id) {
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

  public User firstName(String firstName) {
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

  public User lastName(String lastName) {
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

  public User email(String email) {
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

  public User superiorFirstName(String superiorFirstName) {
    this.superiorFirstName = superiorFirstName;
    return this;
  }

  /**
   * Get superiorFirstName
   * @return superiorFirstName
  **/
  @ApiModelProperty(value = "")


  public String getSuperiorFirstName() {
    return superiorFirstName;
  }

  public void setSuperiorFirstName(String superiorFirstName) {
    this.superiorFirstName = superiorFirstName;
  }

  public User superiorLastName(String superiorLastName) {
    this.superiorLastName = superiorLastName;
    return this;
  }

  /**
   * Get superiorLastName
   * @return superiorLastName
  **/
  @ApiModelProperty(value = "")


  public String getSuperiorLastName() {
    return superiorLastName;
  }

  public void setSuperiorLastName(String superiorLastName) {
    this.superiorLastName = superiorLastName;
  }

  public User flagRead(Boolean flagRead) {
    this.flagRead = flagRead;
    return this;
  }

  /**
   * Get flagRead
   * @return flagRead
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagRead() {
    return flagRead;
  }

  public void setFlagRead(Boolean flagRead) {
    this.flagRead = flagRead;
  }

  public User flagWrite(Boolean flagWrite) {
    this.flagWrite = flagWrite;
    return this;
  }

  /**
   * Get flagWrite
   * @return flagWrite
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagWrite() {
    return flagWrite;
  }

  public void setFlagWrite(Boolean flagWrite) {
    this.flagWrite = flagWrite;
  }

  public User flagBorrow(Boolean flagBorrow) {
    this.flagBorrow = flagBorrow;
    return this;
  }

  /**
   * Get flagBorrow
   * @return flagBorrow
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagBorrow() {
    return flagBorrow;
  }

  public void setFlagBorrow(Boolean flagBorrow) {
    this.flagBorrow = flagBorrow;
  }

  public User flagInventory(Boolean flagInventory) {
    this.flagInventory = flagInventory;
    return this;
  }

  /**
   * Get flagInventory
   * @return flagInventory
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagInventory() {
    return flagInventory;
  }

  public void setFlagInventory(Boolean flagInventory) {
    this.flagInventory = flagInventory;
  }

  public User flagRevision(Boolean flagRevision) {
    this.flagRevision = flagRevision;
    return this;
  }

  /**
   * Get flagRevision
   * @return flagRevision
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagRevision() {
    return flagRevision;
  }

  public void setFlagRevision(Boolean flagRevision) {
    this.flagRevision = flagRevision;
  }

  public User flagAdmin(Boolean flagAdmin) {
    this.flagAdmin = flagAdmin;
    return this;
  }

  /**
   * Get flagAdmin
   * @return flagAdmin
  **/
  @ApiModelProperty(value = "")


  public Boolean isFlagAdmin() {
    return flagAdmin;
  }

  public void setFlagAdmin(Boolean flagAdmin) {
    this.flagAdmin = flagAdmin;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.superiorFirstName, user.superiorFirstName) &&
        Objects.equals(this.superiorLastName, user.superiorLastName) &&
        Objects.equals(this.flagRead, user.flagRead) &&
        Objects.equals(this.flagWrite, user.flagWrite) &&
        Objects.equals(this.flagBorrow, user.flagBorrow) &&
        Objects.equals(this.flagInventory, user.flagInventory) &&
        Objects.equals(this.flagRevision, user.flagRevision) &&
        Objects.equals(this.flagAdmin, user.flagAdmin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, superiorFirstName, superiorLastName, flagRead, flagWrite, flagBorrow, flagInventory, flagRevision, flagAdmin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    superiorFirstName: ").append(toIndentedString(superiorFirstName)).append("\n");
    sb.append("    superiorLastName: ").append(toIndentedString(superiorLastName)).append("\n");
    sb.append("    flagRead: ").append(toIndentedString(flagRead)).append("\n");
    sb.append("    flagWrite: ").append(toIndentedString(flagWrite)).append("\n");
    sb.append("    flagBorrow: ").append(toIndentedString(flagBorrow)).append("\n");
    sb.append("    flagInventory: ").append(toIndentedString(flagInventory)).append("\n");
    sb.append("    flagRevision: ").append(toIndentedString(flagRevision)).append("\n");
    sb.append("    flagAdmin: ").append(toIndentedString(flagAdmin)).append("\n");
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

