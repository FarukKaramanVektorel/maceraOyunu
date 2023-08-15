package inventory;

public class Inventory {
    private boolean water = false;
    private boolean food = false;
    private boolean wood = false;
    private boolean gold = false;
    private Weapon weapon;
    private Armor armor;


    public Inventory(boolean water, boolean food, boolean wood, boolean gold, Weapon weapon, Armor armor) {
        this.water = water;
        this.food = food;
        this.wood = wood;
        this.gold = gold;
        this.weapon = weapon;
        this.armor = armor;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWood() {
        return wood;
    }

    public void setWood(boolean wood) {
        this.wood = wood;
    }

    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
