package location;

import game.Player;

public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(1,"Güvenli ev" ,player);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli Evdesiniz.");
        if(getPlayer().win()){
            System.out.println("Tebrikler, tüm itemleri topladınız ve oyunu kazandınız.");
            System.exit(0);
        }
        System.out.println("Mevcut Sağlığınız :"+getPlayer().getHero().getHealth());
        System.out.println("Canınız yenilendi.");
        getPlayer().getHero().setHealth(getPlayer().getHero().getDefaultHealth());
        System.out.println("Sağlığınız :"+getPlayer().getHero().getHealth());
        return true;
    }
}
