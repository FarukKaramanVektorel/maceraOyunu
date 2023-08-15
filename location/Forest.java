package location;

import game.Player;
import monster.Vampire;

public class Forest extends BattleLoc{
    //orman savaş alanı burada  vampir ile savaşılacak.
    public Forest(Player player) {
        super(3, "Orman", player, new Vampire(),"wood");
    }
}
