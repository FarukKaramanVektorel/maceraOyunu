package location;

import game.Player;
import inventory.*;
import monster.Obstacle;
import java.util.Locale;
import java.util.Random;

public abstract class BattleLoc extends Location{

    Random random=new Random();
    private Obstacle obstacle;
    private String award;
    private int numObs;
    public BattleLoc(int id, String name, Player player, Obstacle obstacle, String award) {
        super(id, name, player);
        this.obstacle=obstacle;
        this.award=award;
    }

    @Override
    public boolean onLocation() {
        System.out.println("Şuan bulunduğunuz yer : \u001B[31m"+this.getName()+"\u001B[0m");
        setNumObs(this.getId());
        System.out.println("Dikkatli olun burada "+this.getNumObs()+" adet " +this.getObstacle().getName().toUpperCase()+ " yaşıyor!");
        System.out.print("Bu canavarla savaşmak için 'S' kaçmak için 'K' basınız : ");
        String sel=scanner.nextLine().toUpperCase();
        if(sel.equals("S")){
            if(!combat(getNumObs()) && this.getPlayer().getPlayerHealth()==0){
                return false;
            }
        }
        else if(sel.equals("K")){
            System.out.println("\u001B[31mCanavardan kaçtınız.\u001B[0m");

        }
        else {
            System.out.println("\u001B[33mYanlış girdiniz! Ana Ekrana dönülüyor\u001B[0m");
        }
        return true;
    }

    public boolean combat(int value){
        boolean isFirst=true;
        for (int i = 1; i <= value; i++) {

            System.out.println(this.getPlayer().toString());
            System.out.println(this.obstacle.toString());
            boolean finish=true;
            while (this.obstacle.getHealth()>0 && this.getPlayer().getPlayerHealth()>0){
                if(firstShot()==2 && isFirst) { //ilk olarak oyuncu vuruyor
                    System.out.println("Vuruş Hakkı Sizle Başlıyor.");
                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getNick() + " " + this.obstacle.getName() + " canavarına "
                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getObstacle().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getObstacle().setHealth(obsHealth);
                        System.out.println(this.obstacle.getName() + " canavarının canı \u001B[31m" + this.getObstacle().getHealth() + " kaldı.\u001B[0m");
                        if (this.getObstacle().getHealth() > 0) { //canavar ölmezse bize vuruyor
                            System.out.println("\u001B[32m"+i + ".  Canavar Size Vurdu !\u001B[0m");
                            int damage = this.getObstacle().getDamage() - getPlayer().getPlayerDefence();
                            if (damage < 0) damage = 0;
                            this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                            System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
                            if (this.getPlayer().getPlayerHealth() == 0) {
                                return false;
                            }
                        }
                    }
                    else {
                        System.out.println("\u001B[31mSavaş alanından kaçtınız.\u001B[0m");
                        return true;
                    }
                }
                else {
                    finish = true;
                    isFirst = false;
                    System.out.println("\u001B[34mVuruş Hakkı Canavarla Başlıyor.\u001B[0m");
                    System.out.println("\u001B[32m"+i + ". Canavar Size Vurdu !\u001B[0m");
                    int damage = this.getObstacle().getDamage() - getPlayer().getPlayerDefence();
                    if (damage < 0) damage = 0;
                    this.getPlayer().getHero().setHealth(this.getPlayer().getPlayerHealth() - damage);
                    System.out.println("Canınız : \u001B[31m" + this.getPlayer().getPlayerHealth()+"\u001B[0m");
                    if (this.getPlayer().getPlayerHealth() < 1) {
                        return false;
                    }
                    System.out.println("Vurmak için 'V' , kaçmak için 'K' basınız .");
                    String match = scanner.nextLine().toUpperCase(Locale.ROOT);
                    if (match.equals("V")) {
                        System.out.println(this.getPlayer().getNick() + " " + this.obstacle.getName() + " canavarına  "
                                + this.getPlayer().getPlayerDamage() + " puan hasar vurdunuz!");
                        int obsHealth = this.getObstacle().getHealth() - this.getPlayer().getPlayerDamage();
                        this.getObstacle().setHealth(obsHealth);
                        System.out.println(this.obstacle.getName() + " canavarının canı \u001B[31m" + this.getObstacle().getHealth() + " kaldı.\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mSavaş alanından kaçtınız.\u001B[0m");

                        return true;
                    }
                }
            }
            if(this.getObstacle().getHealth()<=0){
                isFirst=true;
                int x=value-i;
                System.out.println("\u001B[32mKazandınız. Canavar Öldü.Kalan Canavar Sayısı :"+x+"\u001B[0m");

                    giveMoney(getObstacle().getAwardMoney());
                    System.out.println("\u001B[36mÖdülünüz verildi . Bakiyeniz : " + this.getPlayer().getPlayerMoney()+"\u001B[0m");
                    if(x==0) {
                        giveItem(this.getAward());
                        System.out.println("\u001B[35m"+this.getAward() + " ödülünü kazandınız!"+"\u001B[0m");
                    }

                if(x!=0){
                    this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
                }
            }
        }
        return true;
    }



    private int firstShot(){
        Random r=new Random();
        //1 çıkarsa canavar, 2 çıkarsa player ilk vursun.
        int x= r.nextInt(1,3);
        return x;
    }

    private void giveMoney(int awardMoney) {
        this.getPlayer().getHero().setMoney(this.getPlayer().getPlayerMoney()+awardMoney);
    }
    private void giveItem(String award){
        if(award.equals("food")){
            getPlayer().getInventory().setFood(true);
        }
        if(award.equals("water")){
            getPlayer().getInventory().setWater(true);
        }
        if(award.equals("wood")){
            getPlayer().getInventory().setWood(true);
        }
        if(award.equals("gold")){
            getPlayer().getInventory().setGold(true);
        }
    }

    public int getNumObs() {
        return numObs;
    }

    public void setNumObs(int id) {

            this.numObs = random.nextInt(1,4);
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}