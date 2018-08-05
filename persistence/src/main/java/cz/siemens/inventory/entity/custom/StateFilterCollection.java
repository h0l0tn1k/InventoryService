package cz.siemens.inventory.entity.custom;

import java.util.ArrayList;
import java.util.List;

public class StateFilterCollection
{
	private List<StateFilterItem> filterCollection;
	
	public StateFilterCollection()
	{
		filterCollection = new ArrayList<StateFilterItem>();
	}

	public StateFilterItem getFilterItemByName(String stateName)
	{
		for(int i = 0; i < filterCollection.size(); i++)
		{
			StateFilterItem item = filterCollection.get(i);
			if(item.getState().getName().equals(stateName))
				return item;
		}
		return null;
	}
	
	public List<StateFilterItem> getFilterCollection()
	{
		return filterCollection;
	}
	
	// setter
	public void addFilterItem(StateFilterItem filterItem)
	{
		filterCollection.add(filterItem);
	}
	
	// changer
	public boolean setFilterItemByName(String stateName, boolean enabled)
	{
		for(int i = 0; i < filterCollection.size(); i++)
		{
			StateFilterItem item = filterCollection.get(i);
			if(item.getState().getName().equals(stateName)) {
				item.setEnabled(enabled);
				return true;
			}
		}
		return false;
	}
}
