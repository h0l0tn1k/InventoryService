package cz.siemens.inventory.entity.custom;

public enum InventoryState {

	OK("OK"),
	False("False"),
	Unclear("Unclear");

	private String value;

	InventoryState(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static InventoryState fromValue(String text) {
		for (InventoryState b : InventoryState.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
