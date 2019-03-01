package src.core;

import src.tasks.BuyPacks;
import src.tasks.Hop;
import src.tasks.OpenPacks;

import java.awt.Font;
import java.awt.Graphics;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

@ScriptMeta(category = ScriptCategory.OTHER, desc = "Buys packs of jugs in F2P.", developer = "Sebo", name = "JugPackBuyer", version = 1.00)
public class JugPackBuyer extends TaskScript implements RenderListener {

	public static String action = "IDLE.";
	private String elapsedTime = "CONFIGURING.";
	public static StopWatch time = null;

	private final Task[] tasks = { new OpenPacks(), new BuyPacks(), new Hop() };

	@Override
	public void onStart() {
		time = StopWatch.start();
	}

	@Override
	public int loop() {
		elapsedTime = time.toElapsedString();
		if (!src.states.State.hasEnoughGold()) {
			Log.info("We have less than 1,000 gold left! Stopping.");
			this.setStopping(true);
		}

		for (Task task : tasks) {
			if (task.validate()) {
				return task.execute();
			}
		}
		return 600;
	}

	@Override
	public void notify(RenderEvent renderEvent) {
		Graphics g = renderEvent.getSource();
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawString("JugPackBuyer v1.00 - Sebo", 2, 270);
		g.drawString("Jugs purchased: " + src.states.State.amount * 100, 2, 290);
		g.drawString("Current action: " + action, 2, 310);
		g.drawString("Time ran: " + elapsedTime, 2, 330);
	}
}
