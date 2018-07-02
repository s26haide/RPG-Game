public class Armor
{
    //Constructors
    public Armor()
    {
        itemHealth = 0;
        name = null;
    }
    public Armor(int maxHIn, String nIn)
    {
        itemHealth = maxHIn;
        name = nIn;
    }
    //Accessors
    public int getItemHealth()
    {
        return itemHealth;
    }
    public String getName()
    {
        return name;
    }
    private String name;
    private int itemHealth;
}