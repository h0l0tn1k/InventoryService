package cz.siemens.inventory.entity.custom;

import cz.siemens.inventory.entity.UserScd;

import java.util.ArrayList;
import java.util.List;

public class PermissionItem
{

	private String name;
	private boolean state;

	private List<PermissionItem> perm;

	public PermissionItem(UserScd permission)
	{
		perm = new ArrayList<>();
		perm.add(new PermissionItem("Read-only", permission.isFlagRead()));
		perm.add(new PermissionItem("Edit", permission.isFlagWrite()));
		perm.add(new PermissionItem("Borrowing", permission.isFlagBorrow()));
		perm.add(new PermissionItem("Inventory-making", permission.isFlagInventory()));
		perm.add(new PermissionItem("Revision-making", permission.isFlagRevision()));
		perm.add(new PermissionItem("Admin", permission.isFlagAdmin()));
	}

	private PermissionItem(String name, boolean state)
	{
		this.name = name;
		this.state = state;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isState()
	{
		return state;
	}

	public void setState(boolean state)
	{
		this.state = state;
	}

	public List<PermissionItem> getPerm()
	{
		return perm;
	}

	public void setPerm(List<PermissionItem> perm)
	{
		this.perm = perm;
	}
}
