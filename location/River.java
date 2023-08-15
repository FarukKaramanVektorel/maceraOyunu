package location;

import game.Player;
import monster.Bear;

public class River extends BattleLoc {
    //nehir savaş alanıi burada ayı ile savaşılacak.
    public River(Player player) {
        super(5, "Nehir", player, new Bear(),"water");
    }

}
