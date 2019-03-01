package src.util;

import org.rspeer.runetek.api.component.Shop;

public class GeneralStore {

	public static boolean isEmpty = false;

	public static void setIsEmpty(boolean empty) {
		isEmpty = empty;
	}

	public static boolean shopHasJugPacks() {
		return Shop.getQuantity("Empty jug pack") > 0;
	}

}