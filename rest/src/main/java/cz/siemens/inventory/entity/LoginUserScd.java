package cz.siemens.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "login_user_scd")
public class LoginUserScd implements Serializable {

	private static final long serialVersionUID = 7421379045143833250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long scdId;
	private String firstName;
	private String lastName;
	private String gid;
	private String orgcode;
	private String email;
	private long superiorId;
	private String superiorFirstName;
	private String superiorLastName;
	private boolean flagRead;
	private boolean flagWrite;
	private boolean flagBorrow;
	private boolean flagInventory;
	private boolean flagRevision;
	private boolean flagAdmin;

	public LoginUserScd() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getScdId() {
		return scdId;
	}

	public void setScdId(long scdId) {
		this.scdId = scdId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(long superiorId) {
		this.superiorId = superiorId;
	}

	public String getSuperiorFirstName() {
		return superiorFirstName;
	}

	public void setSuperiorFirstName(String superiorFirstName) {
		this.superiorFirstName = superiorFirstName;
	}

	public String getSuperiorLastName() {
		return superiorLastName;
	}

	public void setSuperiorLastName(String superiorLastName) {
		this.superiorLastName = superiorLastName;
	}

	public String getSuperiorName() {
		String name = "";
		if (superiorFirstName != null)
			name = name + superiorFirstName + " ";
		if (superiorLastName != null)
			name += superiorLastName;
		return name;
	}

	public boolean isFlagRead() {
		return flagRead;
	}

	public void setFlagRead(boolean flagRead) {
		this.flagRead = flagRead;
	}

	public boolean isFlagWrite() {
		return flagWrite;
	}

	public void setFlagWrite(boolean flagWrite) {
		this.flagWrite = flagWrite;
	}

	public boolean isFlagBorrow() {
		return flagBorrow;
	}

	public void setFlagBorrow(boolean flagBorrow) {
		this.flagBorrow = flagBorrow;
	}

	public boolean isFlagInventory() {
		return flagInventory;
	}

	public void setFlagInventory(boolean flagInventory) {
		this.flagInventory = flagInventory;
	}

	public boolean isFlagRevision() {
		return flagRevision;
	}

	public void setFlagRevision(boolean flagRevision) {
		this.flagRevision = flagRevision;
	}

	public boolean isFlagAdmin() {
		return flagAdmin;
	}

	public void setFlagAdmin(boolean flagAdmin) {
		this.flagAdmin = flagAdmin;
	}

	/********** LOGIKA PRO FLAGY ************/

	public String getFlagReadIcon() {
//		if (flagRead)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}

	public String getFlagWriteIcon() {
//		if (flagWrite)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}

	public String getFlagBorrowIcon() {
//		if (flagBorrow)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}

	public String getFlagInventoryIcon() {
//		if (flagInventory)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}

	public String getFlagRevisionIcon() {
//		if (flagRevision)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}

	public String getFlagAdminIcon() {
//		if (flagAdmin)
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
		return "";
	}
}
