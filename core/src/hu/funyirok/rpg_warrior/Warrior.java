package hu.funyirok.rpg_warrior;

import java.util.Random;

/**
 * Created by bRANC on 9/30/2015.
 */
public class Warrior {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hero a= new Hero("Herooborn",30,10,0,10);
        Hero b= new Hero("Steve",40,20,0,11);

        double i=b.defense()-a.attack();
        if(i>0)
            System.out.println("Támadás előtt "+b.getHealth()+" "+i);
        b.setHealth(b.getHealth()-i);
        System.out.println(b.getHealth());


    }

} class Hero{
    double hp,mana,attack,defense,rd,def;
    String nev;
    Hero(String nev_,double hp_,double mana_,double attack_,double defense_){
        hp=hp_;
        mana=mana_;
        attack=attack_;
        defense=defense_;


    }


    Random rnd = new Random();
    public void setHealth(double hp_){
        hp=hp_;


    }
    public double getHealth(){
        return hp;


    }

    public void useTSpell(){
        if(mana>5){
            int i= rnd.nextInt(6);
            rd*= (1+(i/5));

        }else{
            double i =mana;
            rd*= (1+(i/5));
        }
    }
    public void useDSpell(){
        if(mana>5){
            int i= rnd.nextInt(6);
            def*= (1+(i/5));

        }else{
            double i =mana;
            def*= (1+(i/5));
        }
    }

    public double attack(){
        rd=0;
        int i = rnd.nextInt(2);
        System.out.println("At "+i);

        rd=attack*(1.0 +(1.15-1.0)*rnd.nextDouble());
        if(i==1)
            useTSpell();
        return rd;


    }
    public double defense(){
        int i = rnd.nextInt(2);
        System.out.println("Def "+i);
        def=0;
        def=defense*(0.5 +(1.3-0.5)*rnd.nextDouble());;
        if(i==1)
            useDSpell();
        return def;

    }
}
