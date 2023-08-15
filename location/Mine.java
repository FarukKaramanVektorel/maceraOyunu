package location;

import game.Player;
import monster.Snake;

public class Mine extends BattleLoc{
    public Mine(Player player) {
        super(6, "Maden", player, new Snake(), "gold");
    }
}
