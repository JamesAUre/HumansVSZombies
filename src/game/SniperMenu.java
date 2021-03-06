package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SniperMenu extends Menu {

    public Action showMenu(Actor actor, Actions actions, Display display) {
        ArrayList<Character> freeChars = new ArrayList<Character>();
        HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();

        for (char i = 'a'; i <= 'z'; i++)
            freeChars.add(i);

        // Show with the actions with hotkeys first;
        for (Action action : actions.sorted(new SortHotkeysFirst())) {
            String hotKey = action.hotkey();
            char c;
            if (hotKey == null || hotKey == "") {
                if (freeChars.isEmpty())
                    break; // we've run out of characters to pick from.
                c = freeChars.get(0);
            } else {
                c = hotKey.charAt(0);
            }
            freeChars.remove(Character.valueOf(c));
            keyToActionMap.put(c, action);
            display.println(c + ": " + "test");
        }

        char key;
        do {
            key = display.readChar();
        } while (!keyToActionMap.containsKey(key));

        return keyToActionMap.get(key);
    }

    class SortHotkeysFirst implements Comparator<Action> {
        public int compare(Action a, Action b) {
            if (a.hotkey() != null && b.hotkey() == null)
                return -1;

            if (a.hotkey() == null && b.hotkey() != null)
                return 1;

            return 0;
        }
    }
}
