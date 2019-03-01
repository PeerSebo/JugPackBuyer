package src.tasks;

import src.core.JugPackBuyer;
import src.states.State;
import src.util.GeneralStore;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.script.task.Task;

public class Hop extends Task {

	@Override
	public int execute() {
		closeAndHop();
		Time.sleep(3000);
		return 600;
	}

	@Override
	public boolean validate() {
		return GeneralStore.isEmpty && !State.hopped;
	}

	public static void closeAndHop() {
		InterfaceComponent component = Interfaces.getComponent(300, 1, 11);
		if (component != null) {
			JugPackBuyer.action = "CLOSING STORE.";
			component.interact("Close");
			Time.sleep(1000);
		}
		Time.sleepUntil(() -> WorldHopper.open(), 2400, 4800);
		JugPackBuyer.action = "WAITING 10 SECONDS.";
		Time.sleep(10000);
		randomSafeF2P();
	}

	public static boolean randomSafeF2P() {
		JugPackBuyer.action = "HOPPING.";
		State.setHopped(true);
		GeneralStore.setIsEmpty(false);
		return WorldHopper.randomHop(world -> world.getId() != Game.getClient().getCurrentWorld() && !world.isMembers()
				&& !world.isPVP() && !world.isSkillTotal() && !world.isTournament() && !world.isHighRisk()
				&& !world.isDeadman() && !world.isSeasonDeadman());
	}

}