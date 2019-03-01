package src.tasks;

import src.core.JugPackBuyer;
import src.states.State;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class OpenPacks extends Task {

	@Override
	public int execute() {
		InterfaceComponent component = Interfaces.getComponent(300, 1, 11);
		if (component != null) {
			component.interact("Close");
			Time.sleep(1000);
		} else {
			for (Item item : Inventory.getItems()) {
				JugPackBuyer.action = "OPENING PACKS.";
				if (item.getName().equals("Empty jug pack")) {
					item.interact("Open");
					src.states.State.amount++;
					Time.sleep(100, 200);
				}
			}
		}
		return 600;
	}

	@Override
	public boolean validate() {
		return State.enoughPacks() && State.hasEnoughGold();
	}

}
