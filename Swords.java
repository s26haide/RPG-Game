public class Swords
{
    //Constructors
    public Swords()
    {
        itemAttack = 0;
        name = null;
    }
    public Swords(int maxAIn, String nIn)
    {
        itemAttack = maxAIn;
        name = nIn;
    }
    //Accessors
    public int getItemAttack()
    {
        return itemAttack;
    }
    public String getName()
    {
        return name;
    }

    private String name;
    private int itemAttack;
}