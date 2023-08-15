package characters;

public class Hero {
    private int id;
    private String name;
    private int health;
    private int damage;
    private int money;
    private int defaultDamage;
    private int defaultHealth;

    public Hero(int id, String name, int health, int damage, int money) {
        this.id=id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.money = money;
        this.defaultDamage=damage;
        this.defaultHealth=health;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health<0){
            health=0;
        }
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /*
    Karakterin başlangıç hasarını gösterir.
    Silahı varken yeni silah alırsa hasarı güncelleyebilmek için.
     */
    public int getDefaultDamage() {
        return defaultDamage;
    }

    /*
    Karakterin başlangıçtaki canını veren method.
    Sağlığını yenilemek için kullanacağım.
     */
    public int getDefaultHealth() {
        return defaultHealth;
    }
}
