import java.util.Random;
/* 
 * Author: Sakin Haider
 * Starting date: May 6th
 * Creating monsters for an RPG
 * ICS3U1 Final Culminating
 */
public class Monster
{
    Random rand =  new Random();
    //Constructors
    public Monster()
    {
        name = null;
        level = 0;
        health = 0;
        maxHealth = 0;
        EXP = 0;
    }
    public Monster(String nIn, int lIn, int hIn, int maxHIn, int EXPIn)
    {
        name = nIn;
        level = lIn;
        health = hIn;
        maxHealth = maxHIn;
        EXP = EXPIn;
    }
    //Accessors
    public String getName()
    {
        return name;
    }
    public int getLevel()
    {
        return level;
    }
    public int getHealth()
    {
        return health;
    }
    public int getMaxHealth()
    {
        return maxHealth;
    }
    public int Attack(int b)
    {
        int randNum = rand.nextInt(((level+10) - 5)+ 1)+5;
        randNum = randNum+(b*5);
        return randNum;
    }
    public int giveEXP()
    {
        return EXP;
    }
    //Mutators
    public void loseHealth(int damage)
    {
        health = health - damage;
    }
    public void setHealth(int health1)
    {
        health = health1;
    }
    public void setEXP()
    {
        EXP = maxHealth;
    }
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int EXP;
}