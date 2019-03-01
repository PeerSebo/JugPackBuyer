package src.tasks;

import src.core.JugPackBuyer;
import src.states.State;
import src.util.GeneralStore;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class BuyPacks extends Task {

	@Override
	public int execute() {
		JugPackBuyer.action = "BUYING PACKS.";
		State.setHopped(false);
		
		if (Shop.isOpen()) {
			if (GeneralStore.shopHasJugPacks()) {
				Shop.buyFive("Empty jug pack");
				Time.sleep(250);
			} else {
				GeneralStore.setIsEmpty(true);
				JugPackBuyer.action = "CLOSING STORE.";
			}
		} else {
			if (Npcs.getNearest("Shop keeper") != null) {
				JugPackBuyer.action = "OPENING STORE.";
				Npcs.getNearest("Shop keeper").interact("Trade");
				Time.sleep(2000);
			}
		}
		return 600;
	}

	@Override
	public boolean validate() {
		return !State.enoughPacks() && State.hasEnoughGold() && !GeneralStore.isEmpty;
	}

}