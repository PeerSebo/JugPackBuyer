package src.states;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;

public class State {

	public static int amount = 0;
	public static boolean hopped = false;

	public static void setAmount(int amnt) {
		amount = amnt;
	}

	public static void setHopped(boolean hop) {
		hopped = hop;
	}

	public static boolean enoughPacks() {
		return Inventory.isFull() || Inventory.getCount(20743) >= 26;
	}

	public static boolean hasEnoughGold() {
		Item gold = Inventory.getFirst("Coins");
		return gold != null && gold.getStackSize() >= 1000;
	}

}