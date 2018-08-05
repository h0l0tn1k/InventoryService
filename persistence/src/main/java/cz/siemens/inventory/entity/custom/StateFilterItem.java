package cz.siemens.inventory.entity.custom;

import cz.siemens.inventory.entity.DeviceState;

public class StateFilterItem
{
	private DeviceState state;
	private boolean enabled;
	
	public StateFilterItem(DeviceState state)
	{
		this.state = state;
		
		if(state.getName().equals("Discard"))
			enabled = false;
		else
			enabled = true;
	}

	public DeviceState getState()
	{
		return state;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}	
}
