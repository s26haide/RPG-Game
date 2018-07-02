import java.util.Random;
/* 
 * Author: Sakin Haider
 * Starting date: May 6th
 * Creating a character for the Player for an RPG
 * ICS3U1 Final Culminating
 */
public class Player
{
    Random rand = new Random();
    //Constructors
    public Player()
    {
        name = null;
        level = 0;
        health = 0;
        maxHealth = 0;
        EXP = 0;
        dmgRange = 0;
        maxEXP = 0;
    }

    public Player(String nIn, int lIn, int hIn, int mhIn, int expIn, int dmgIn, int mexpIn)
    {
        name = nIn;
        level = lIn;
        health = hIn;
        maxHealth = mhIn;
        EXP = expIn;
        dmgRange = dmgIn;
        maxEXP = mexpIn;
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

    public int getEXP()
    {
        return EXP;
    }

    public int getDmgRange()
    {
        return dmgRange;
    }

    public int getMaxEXP()
    {
        return maxEXP;
    }

    public int Attack()
    {
        int randNum = rand.nextInt(((level+10) - 5)+1)+5;
        return randNum;
    }
    //Mutators
    public void addLevel()
    {
        while(EXP >= maxEXP)
        {
            level = level + 1;
            maxHealth = maxHealth + 10;
            System.out.println("Congratulations, you leveled up.");
            EXP = EXP - maxEXP;
            maxEXP = maxEXP + 50;
            System.out.println("Maximum EXP increased to : " + maxEXP);
            System.out.println("Your maximum health and damage range have increased.");
            System.out.println("EXP to level up: " + (maxEXP - EXP));
        }
        System.out.println("EXP to level up: " + (maxEXP - EXP));
    }

    public void addHealth(boolean usePotion, int potionHeal)
    {
        if (usePotion == true)
        {
            health = health + potionHeal;
        }
    }

    public void loseHealth(int damage)
    {
        health = health - damage;
    }

    public void addEXP(int a)
    {
        EXP = EXP + a;
    }

    public void setHealth(int health1)
    {
        health = health1;
    }

    public void setDmgRange()
    {
        dmgRange = (level+10) - 5;
        int randNum = rand.nextInt(((level+10) - 5)+1)+5;
    }

    public void setMaxHealth(int a)
    {
        maxHealth = a;
    }
    private String name;
    private int level;
    private int health;
    private int maxHealth;
    private int EXP;
    private int dmgRange;
    private int maxEXP;
}