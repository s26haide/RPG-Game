import java.util.Scanner;
import java.util.Random;
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;
import javax.swing.*;
/* 
 * Author: Sakin Haider
 * Starting date: May 6th
 * Driver and Main Method for RPG Game
 * ICS3U1 Final Culminating
 * Turn on sound for maximum experience
 */
public class RPG_Game
{
    public static class MusicThread extends Thread
    {
        private File sound;
        private boolean loop;
        public MusicThread(File sound, boolean loop)
        {
            this.sound = sound;
            this.loop = loop;
        }

        public void run()
        {
            try
            {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                if(loop == true)
                {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else
                {
                    clip.start();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main (String [] args)
    throws Exception
    {
        System.out.println("Turn on sound for maximum experience.");
        Random rand = new Random(); //new RNG
        Scanner getIn = new Scanner(System.in); //new Scanner 
        System.out.println("What is your name?");
        String name = getIn.nextLine(); //Allow user input
        System.out.println("This is the story of " + name + "...");
        delay(1);//Uses delay() method to allow player to read easier.
        System.out.println();
        System.out.println("You wake up like every day and go on your morning routine.");
        delay(1);
        System.out.println("After 2 hours of working and training, you go back inside..."); 
        System.out.println("when you hear something behind you.");
        delay(1);
        Player MC = new Player(name,1,50,50,0,0,50); //Create Player Object
        System.out.println();//Blank Space
        Monster[] a = new Monster[6]; //Create Array of Monsters
        a[0] = new Monster("Green Slime",1,30,30,0);
        a[1] = new Monster("Wolf",5,50,50,0);
        a[2] = new Monster("Rock Golem",5,100,100,0);
        a[3] = new Monster("Cerberus",15,150,100,0);
        a[4] = new Monster("Demon",15,250,200,0);
        a[5] = new Monster("Vanlith, The Ancient Dragon",25,500,500,0);

        Swords [] c = new Swords[6]; //Create Array of Swords
        c[0] = new Swords(3,"Wooden Sword");
        c[1] = new Swords(5,"Iron Sword");
        c[2] = new Swords(10,"Steel Sword");
        c[3] = new Swords(20,"Silver Sword");
        c[4] = new Swords(35,"Sword of Destiny");
        c[5] = new Swords(0,null);

        Armor [] d = new Armor[6]; //Create Array of Armor
        d[0] = new Armor(5, "Wooden Armor");
        d[1] = new Armor(15,"Iron Armor");
        d[2] = new Armor(30,"Steel Armor");
        d[3] = new Armor(50,"Silver Armor");
        d[4] = new Armor(100,"Armor of Destiny");
        d[5] = new Armor(0,null);

        int potions = 3; //Amount of potions
        int monsterKill = 0; //Amount of monsters defeated
        boolean swordEquip = false; //False, true if sword is equipped
        boolean armorEquip = false; //False, true if armor is equipped
        int swordItem = 0; //Amount of swords you have in inventory
        int armorItem = 0; //Amount of armor in inventory
        int timesRun = 0; //The amount of times the loop is run
        String input = null; //variable for every user input
        int potionHeal = 50; //Amount that potions heal
        int b = 0; // Variable to control monster, sword and armor array index

        String fileName;
        MusicThread mt = new MusicThread(new File("FE-TWR.wav"), true);
        MusicThread mt2 = new MusicThread(new File("monster.wav"), false);
        MusicThread mt3 = new MusicThread(new File("Sound-Effect3.wav"), false);
        MusicThread mt4 = new MusicThread(new File("Sound-Effect24.wav"), false);
        mt.start();

        while(a[b].getHealth() > 0 && MC.getHealth() > 0)//Battle while monster and player have more than 0 health
        {
            timesRun++; //Increment timesRun everytime the battle loops
            if (timesRun == 1) // First turn of the battle, displays information
            {
                System.out.println("A monster appeared!");
                delay(2);
                System.out.println("Name: " + MC.getName()); //Display Player Info
                System.out.println("Level: " + MC.getLevel()); //Display Player Info
                System.out.println("HP: " + MC.getHealth()); //Display Player Info
                System.out.println();//Blank Space
                System.out.println("Name: " + a[b].getName());//Display Monster Info
                System.out.println("Level: " + a[b].getLevel());//Display Monster Info
                System.out.println("HP: " + a[b].getHealth());//Display Monster Info
                System.out.println();//Blank Space
                //Plays Audio for when Monster appears
                {
                    mt2 = new MusicThread(new File("monster.wav"), false);
                    mt2.start();
                }
            }
            System.out.println("What will you do? (1.Attack, 2.Defend, 3.Item)");
            input = getIn.next();
            if(input.equalsIgnoreCase("1"))//If attack option is chosen
            {
                System.out.println("You attacked!");
                int damage = MC.Attack();
                if (swordEquip == true)//If sword is equipped
                {
                    if(monsterKill == 0)
                    {
                        damage = damage + c[b-1].getItemAttack();//Increase damage by attack of previous sword if fighting first monster of current type              
                    }
                    else
                    {
                        damage = damage + c[b].getItemAttack();//Increase damage by attack of current sword if fighting second or more monster of current type
                    }
                }
                else
                {
                    damage = damage;//If no sword is equipped, damage is not changed.
                }
                a[b].loseHealth(damage);//Monster loses health equal to damage
                if (damage == 0)//If rng chooses damage = 0
                {
                    System.out.println("You attacked and missed!");//Explain that you did 0 damage because you missed the monster
                    delay(1);
                }
                System.out.println("You dealt " + damage + " damage! ");//Tell how much damage you dealt
                if(a[b].getHealth() < 0)//If Monster health is less than 0
                {
                    a[b].setHealth(0);//Set health to 0 so there is no negative health
                }
                System.out.println(a[b].getName() + " has " + a[b].getHealth() + " health remaining!");//Say how much health is remaining
                delay(1);
                if(a[b].getHealth() == 0)//If monster dies, increment monsterKill
                {   
                    System.out.println("Congratulations, you defeated the monster!");   
                    delay(2);
                    monsterKill++;                    
                }
                System.out.println();
                if(a[b].getHealth() > 0) //if monster is alive, it will attack
                {
                    int damage2 = a[b].Attack(b);//Calculate damage monster will deal
                    MC.loseHealth(damage2);//Player loses health equal to monster damage
                    if (damage2 == 0)//if damage is chosen as 0, explain that attack missed
                    {
                        System.out.println(a[b].getName() + " attacked and missed!");
                        delay(1);
                    }
                    System.out.println(a[b].getName() + " dealt " + damage2 + " damage!");//Display damage
                    if(MC.getHealth() < 0) //if player dies, set health to 0 so there is no negative health
                    {
                        MC.setHealth(0);
                    }
                    System.out.println(MC.getName() + " has " + MC.getHealth() + " health remaining!");//Displayed player health
                    delay(1);
                    if(MC.getHealth() == 0)//if player dies
                    {
                        { //play Sound Effect for game over
                            mt3 = new MusicThread(new File("Sound-Effect3.wav"), false);
                            mt3.start();
                        }
                        System.out.println("You have been defeated. Please enter 'end' to quit game and run to play again."); //if player dies, exit game.
                        input = getIn.next(); //allow user input
                        if (input.equalsIgnoreCase("end")) //player types end and game exits
                        {
                            delay(2);
                            System.exit(0);
                        }
                        else //if input is not recognized, game will exit anyways
                        {
                            System.out.println("Command not valid. Game will exit anyways.");
                            delay(2);
                            System.exit(0);
                        }
                    }
                }
            }

            else if (input.equalsIgnoreCase("2")) //if defend option is chosen
            {
                System.out.println("You defend to receive half damage and deal the same amount of damage back! Also a chance to nullify all damage.");
                int damage = a[b].Attack(b); //calculate monster damage
                int damage2 = damage / 2; //divide monster damage by 2
                int chance = rand.nextInt((9 - 0) + 1) + (0); //choose a random number between 0-10
                delay(1);
                if (chance == 0)//if random number is 0 (10% chance)
                {
                    damage2 = 0; //make damage 0
                    System.out.println("Damage nullified.");
                }
                MC.loseHealth(damage2); //Player loses health equal to 1/2 monster damage
                System.out.println(a[b].getName() + " attacked you for " + damage + " but because you blocked, you received " + damage2 + "!");
                if(MC.getHealth() < 0) //if player dies set health as 0
                {
                    MC.setHealth(0);
                }
                System.out.println(MC.getName() + " has " + MC.getHealth() + " health remaining!");//display player health
                delay(2);
                System.out.println();
                if(MC.getHealth() == 0)//if player dies
                {
                    { //play Sound Effect for game over
                        mt3 = new MusicThread(new File("Sound-Effect3.wav"), false);
                        mt3.start();
                    }
                    System.out.println("You have been defeated. Please enter 'end' to quit game and run to play again.");
                    input = getIn.next();
                    if (input.equalsIgnoreCase("end")) //exit game
                    {
                        System.exit(0);
                    }
                    else //input not recognized, game exits anyways
                    {
                        System.out.println("Command not valid. Game will exit anyways.");
                        System.exit(0);
                    }
                }
                a[b].loseHealth(damage2); //monster loses health equal to 1/2 monster damage because player defended
                System.out.println("You deal the same damage back to " + a[b].getName()); //explain damage return to monster
                System.out.println("You dealt " + damage2 + " damage! "); //display damage dealt
                if(a[b].getHealth() < 0)//If health is less than 0
                {
                    a[b].setHealth(0);//Set health to 0 so there is no negative health
                }
                System.out.println(a[b].getName() + " has " + a[b].getHealth() + " health remaining!");//Say how much health is remaining
                delay(2);
                if(a[b].getHealth() == 0)//If monster dies, increment monsterKill
                {   
                    System.out.println("Congratulations, you defeated the monster!");   
                    monsterKill++;
                    delay(2);
                }
                System.out.println();
            }

            else if (input.equalsIgnoreCase("3")) //if item option is chosen
            {
                System.out.println("1.Potion 2.Equipment"); //choice for potion or equipment
                input = getIn.next();
                if (input.equalsIgnoreCase("1")) // if potion is chosen
                {
                    if(potions <= 0) //if player has no potions
                    {
                        System.out.println("You have no potions left!"); //tell player they have no potions
                        System.out.println();
                        continue;
                    }
                    System.out.println("You currently have " + potions + " health potions. Use one? (1.yes 2.no)"); //display amount of potions player has
                    input = getIn.next(); //option to use one or not
                    if (input.equalsIgnoreCase("1")) //if potion is used
                    {
                        if (MC.getHealth()+potionHeal > MC.getMaxHealth()) //if potion will heal more than player max health
                        {
                            System.out.println("You used a potion. Maximum health is " + MC.getMaxHealth() + "."); // display max health
                            potions--; //lose a potion
                            delay(2);
                            MC.setHealth(MC.getMaxHealth()); //set player health to max health
                            System.out.println("You have " + potions + " potions remaining."); //display amount of potions left
                            delay(2);
                            System.out.println(MC.getName() + " has " + MC.getHealth() + " health remaining!"); // display player health
                            System.out.println();
                        }
                        else if (MC.getHealth() + potionHeal < MC.getMaxHealth()) //if potion will not heal to maximum health
                        {
                            System.out.println("You used a potion. Health increased by " + potionHeal + "."); //display amount potion heals
                            potions--; // lose a potion
                            MC.addHealth(true, potionHeal); //method addHealth true, heals player by potionHeal
                            delay(1);
                            System.out.println("You have " + potions + " potions remaining."); //display amount of potions left
                            delay(1);
                            System.out.println(MC.getName() + " has " + MC.getHealth() + " health remaining!"); // display player health
                            delay(1);
                            System.out.println();
                        }   
                        else //if user input is not valid
                        {
                            System.out.println("Input invalid.");
                            delay(1);
                            continue;
                        }
                    }
                    else if (input.equalsIgnoreCase("2")) //if potion is not used
                    {
                        System.out.println();
                        continue;
                    }
                    else //if user input is not detected
                    {
                        System.out.println("Command not detected.");
                        System.out.println();
                        continue;
                    }
                }
                else if (input.equalsIgnoreCase("2")) //if equipment is chosen
                {
                    if(swordItem == 0 && armorItem == 0) //if no equipment is in inventory
                    {
                        System.out.println("You have no equipment!"); //explain that player has no equipment
                        System.out.println();
                        continue;
                    }
                    System.out.println("1.Sword 2.Armor?"); //give choice to go to sword or armor
                    input = getIn.next();
                    if(input.equalsIgnoreCase("1")) //if sword is chosen
                    {
                        if (swordItem > 0) //if player has sword
                        {
                            System.out.println("You have a sword in your items. Equip? (1.yes 2.no)"); //choice to equip or not
                            input = getIn.next();
                            if(input.equalsIgnoreCase("1")) //if chosen to equip
                            {
                                swordEquip = true; //sword equip is true
                                if (b == 0) //if on first monster, equip first sword
                                {
                                    System.out.println("You equipped the " + c[b].getName() + ". Your damage has been increased by " + c[b].getItemAttack());
                                }
                                else if (b > 0 && monsterKill == 0) //if on monster higher than first, but first monster of that type, equip sword from last index
                                {
                                    System.out.println("You equipped the " + c[b-1].getName() + ". Your damage has been increased by " + c[b-1].getItemAttack());
                                }
                                else if (b > 0 && monsterKill > 0) //if on monster higher than first, and not first monster of that type, equip sword from current index
                                {
                                    System.out.println("You equipped the " + c[b].getName() + ". Your damage has been increased by " + c[b].getItemAttack());
                                }
                                System.out.println("Sword will stay equipped until new sword is equipped."); //explain sword will stay equipped.
                                System.out.println();
                                continue;
                            }
                            else if(input.equalsIgnoreCase("2")) //if no is chosen
                            {
                                System.out.println();
                                continue;
                            }
                            else //if input not valid
                            {
                                System.out.println("Command not valid.");
                                System.out.println();
                                continue;
                            }
                        }
                        else //if player has no sword
                        {
                            System.out.println("You have no swords in your inventory!");
                            System.out.println();
                            continue;
                        }
                    }
                    if(input.equalsIgnoreCase("2")) //if armor is chosen
                    {
                        if (armorItem > 0) //if player has armor
                        {
                            System.out.println("You have armor in your items. Equip? (1.yes 2.no)"); //give option to equip armor or not
                            input = getIn.next();
                            if(input.equalsIgnoreCase("1")) // if equip armor is chosen
                            {
                                armorEquip = true; //armorEquip is true;
                                if(b == 0) //if on first monster, equip first armor
                                {
                                    System.out.println("You equipped the " + d[b].getName() + ". Your maximum health and current health have been increased by " + d[b].getItemHealth());
                                    MC.setMaxHealth(MC.getMaxHealth() + d[b].getItemHealth());
                                    MC.setHealth(MC.getHealth() + d[b].getItemHealth());
                                    System.out.println("Maximum health is " + MC.getMaxHealth() + "   Current health is " +  MC.getHealth());
                                }
                                else if (b > 0 && monsterKill == 0) //if on monster higher than first, but first monster of the type, equip armor from previous index
                                {
                                    System.out.println("You equipped the " + d[b-1].getName() + ". Your maximum health and current health have been increased by " + d[b-1].getItemHealth());
                                    MC.setMaxHealth(MC.getMaxHealth() + d[b-1].getItemHealth());
                                    MC.setHealth(MC.getHealth() + d[b-1].getItemHealth());
                                    System.out.println("Maximum health is " + MC.getMaxHealth() + "   Current health is " +  MC.getHealth());
                                }
                                else if (b > 0 && monsterKill > 0)// if on monster higher than first, and not first monster of the type, equip armor from current index
                                {
                                    System.out.println("You equipped the " + d[b].getName() + ". Your maximum health and current health have been increased by " + d[b].getItemHealth());
                                    MC.setMaxHealth(MC.getMaxHealth() - d[b-1].getItemHealth() + d[b].getItemHealth());
                                    MC.setHealth(MC.getHealth() - d[b-1].getItemHealth() + d[b].getItemHealth());
                                    System.out.println("Maximum health is " + MC.getMaxHealth() + "   Current health is " +  MC.getHealth());
                                }
                                System.out.println("Armor will stay equipped until new armor is equipped."); //explain armor will stay equipped until new armor is equipped
                                System.out.println();
                                continue;
                            }
                            else if(input.equalsIgnoreCase("2")) //if no is chosen to armor equip
                            {
                                System.out.println();
                                continue;
                            }
                            else //if input is not valid
                            {
                                System.out.println("Command not valid.");
                                System.out.println();
                                continue;
                            }
                        }
                        else // if player has no armor
                        { 
                            System.out.println("You have no armor in your inventory!");
                            System.out.println();
                            continue;
                        }
                    }
                }
                System.out.println();
                int damage2 = a[b].Attack(b); //Monster attacks
                MC.loseHealth(damage2); //Player loses health
                System.out.println(a[b].getName() + " hit you for " + damage2 + " damage!"); //display monster damage
                if(MC.getHealth() < 0) //if player dies
                {
                    MC.setHealth(0); //set health to 0 so no negative health
                }
                System.out.println(MC.getName() + " has " + MC.getHealth() + " health remaining!"); //display player health
                if(MC.getHealth() == 0) //if player dies
                {
                    {  // play Sound Effect for game over
                        mt3 = new MusicThread(new File("Sound-Effect3.wav"), false);
                        mt3.start();
                    }
                    System.out.println("You have been defeated. Please enter 'end' to quit game and run to play again."); //explain player died
                    input = getIn.next();
                    if (input.equalsIgnoreCase("end"))
                    {
                        delay(1);
                        System.exit(0); //Exit game
                    }
                    else //if input not valid
                    {
                        System.out.println("Command not valid. Game will exit anyways.");
                        delay(1);
                        System.exit(0); //exit game anyways
                    }
                }
            }
            else //if input not valid
            {
                System.out.println("The inputted command is not valid");
                System.out.println();
                continue;
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println();

            if (MC.getHealth() == 0) //if player died
            {
                {  // play Sound Effect for game over
                    mt3 = new MusicThread(new File("Sound-Effect3.wav"), false);
                    mt3.start();
                }
                System.out.println("You have been defeated. Please enter 'end' to quit game and run to play again.");
                input = getIn.next(); //user input to end game
                if (input.equalsIgnoreCase("end"))
                {
                    delay(1);
                    System.exit(0); //game exits
                }
                else //if input is not valid
                {
                    System.out.println("Command not valid. Game will exit anyways.");
                    System.exit(0); //game exits anyways
                }
            }
            if (a[b].getHealth() == 0) //if monster died and battle is over
            {
                {   //play Sound Effect for end of battle
                    mt4 = new MusicThread(new File("Sound-Effect24.wav"), false);
                    mt4.start();
                }
                System.out.println();
                a[b].setEXP(); //set monster exp
                System.out.println("You earned: " + a[b].giveEXP() + " EXP"); //display exp for killing the monster
                delay(1);
                MC.addEXP(a[b].giveEXP());//Acquire EXP for defeating monster
                MC.addLevel(); //Check if level up has occured
                System.out.println("You currently have: " + MC.getEXP() + " EXP"); //display current exp
                delay(1);
                MC.setDmgRange();//Increase Damage Range with Level
                swordItem++; //increase sword count
                armorItem++; //increase armor count
                if(swordItem == b+1 && armorItem == b+1) //if player has a sword and armor, display the name of both equipment. Explain to equip during next battle
                {
                    System.out.println("The monster dropped a " + c[b].getName() + ". The item has been put in your inventory. Equip during next battle.");
                    System.out.println("The monster dropped a " + d[b].getName() + ". The item has been put in your inventory. Equip during next battle.");
                }
                System.out.println();
                System.out.println("Name: " + MC.getName()); //Display Player Info
                System.out.println("Level: " + MC.getLevel()); //Display Player Info
                System.out.println("HP: " + MC.getHealth()); //Display Player Info
                System.out.println();
                if(b <= 2)//Allow option to explore and rest if on the first 3 monsters
                {
                    System.out.println("What would you like to do? (1.Explore 2.Rest)");
                    input = getIn.next();
                }
                if (b >= 3)//After first 3 monster, you cannot explore anymore
                {
                    System.out.println("After that hard battle, you are tired and go rest.");
                    input = "2"; //set input as 2 to force rest, don't allow player to explore on these monsters.
                }
                if (input.equalsIgnoreCase("1"))//If player chooses to explore
                {
                    a[b].setHealth(a[b].getMaxHealth());//Set monster health to maximum
                    { //Play sound for monster
                        mt2 = new MusicThread(new File("monster.wav"), false);
                        mt2.start();
                    }
                    System.out.println("You explore the area and run into a monster again.");
                    delay(2);
                    System.out.println();
                    System.out.println("Name: " + MC.getName()); //Display Player Info
                    System.out.println("Level: " + MC.getLevel()); //Display Player Info
                    System.out.println("HP: " + MC.getHealth()); //Display Player Info
                    System.out.println();//Blank Space
                    System.out.println("Name: " + a[b].getName());//Display Monster Info
                    System.out.println("Level: " + a[b].getLevel());//Display Monster Info
                    System.out.println("HP: " + a[b].getHealth());//Display Monster Info
                    System.out.println();
                    continue;
                }
                else if (input.equalsIgnoreCase("2")) //if town option is chosen
                {
                    System.out.println("You went back and rested. You gained 3 potions."); //explain that player gets 3 potions and rests
                    delay(2);
                    MC.setHealth(MC.getMaxHealth()); //health player to max health
                    System.out.println("Your current health is " + MC.getHealth()); //display player health
                    delay(2);
                    potions = potions + 3; //increase potion count by 3
                    if (potions >= 5)
                    {
                        System.out.println("Maximum potions you can carry is 5.");
                        potions = 5;
                    }
                    System.out.println("You currently have " + potions + " health potions."); //display amount of potions
                    delay(2);
                    System.out.println();
                    if(b == 0) //if on first monster, display this dialogue
                    {
                        System.out.println("Next morning you set off to begin your journey.");
                        delay(1);
                        System.out.println("You will go on a journey to save the world!");
                        delay(2);
                        System.out.println();
                    }
                    else if(b == 1) //if on second monster, display this dialogue
                    {
                        System.out.println("Why are the monsters in the world so violent now?");
                        delay(2);
                        System.out.println("There must be an explanation for this. Next morning you set off to continue your journey with questions in mind.");
                        delay(2);
                        System.out.println();
                    }
                    else if(b == 2) //if on third monster, display this dialogue
                    {
                        System.out.println("You wonder why the peaceful " + a[b].getName() + " attacked so suddenly.");
                        delay(2);
                        System.out.println("You overhear a group of drunkards yelling about the story of the " + a[5].getName() + "'s revival");
                        delay(2);
                        System.out.println("Maybe that has something to do with it...");
                        delay(2);
                        System.out.println("You resolve yourself to figure out this mystery as you go to sleep for the night.");
                        delay(2);
                        System.out.println("You set out on your journey next morning.");
                        delay(2);
                        System.out.println();
                    }
                    else if(b == 3) //if on fourth monster, display this dialogue
                    {
                        System.out.println(a[3].getName() + " was a ferocious monster. You must be getting closer to the end of your journey.");
                        delay(2);
                        System.out.println("You go to sleep knowing the monsters from now on will be incredibly strong.");
                        delay(1);
                        System.out.println("Next morning you set off on your journey.");
                        System.out.println();
                    }
                    else if(b == 4) //if on fifth monster, display this dialogue
                    {
                        System.out.println("There shouldn't be any " + a[4].getName() + "'s in this world.");
                        delay(2);
                        System.out.println("Maybe this problem is a lot worse than you imagined.");
                        delay(2);
                        System.out.println("You have a strong feeling that tomorrow may be the last day of your journey.");
                        delay(2);
                        System.out.println("You prepare yourself mentally and go to sleep. You set off next morning.");
                    }
                    b++; //increase monster, sword and armor array index
                    if(b == 3) //if on fourth monster, increase potion healing amount
                    {
                        potionHeal = potionHeal + 50; //increase potion healing amount by 50
                        System.out.println("You realize you will need strong potions."); //explain to player
                        delay(2);
                        System.out.println("You buy stronger potions. They heal you for " + potionHeal); //display potion healing amount
                        System.out.println();
                    }
                    if(b <= 2) //if on monsters 1,2,3, explain that the next area is filled a stronger monster
                    {
                        System.out.println("The next area is filled with stronger monsters.");
                        delay(1);
                        System.out.println("You encountered a monster!");
                        delay(1);
                    }
                    if(b == 3) //if on fourth monster, give context to where player finds the mini-boss
                    {
                        System.out.println("You wandered into a mysterious cave and hear wolves howling. You go check it out... and find Cerberus!");
                        System.out.println("Cerberus attacked!");
                    }
                    if(b == 4) //if on fifth monster, give context to where player finds next mini-boss
                    {
                        System.out.println("You find another cave and feel a demonic force pulling you in. You check it out... and find a Demon!");
                        System.out.println("The Demon attacked!");
                    }
                    if(b == 5) //if on final monster, give context to where player finds the final boss
                    {
                        System.out.println("You climb to the peak of the largest mountain and finally find the strongest monster in the world.");
                        System.out.println("The legends say this monster is invincible and will wipe out anyone who challenges it.");
                        System.out.println("Would you like to challenge it or back back and train more? (1.Challenge 2.Train)");
                        input = getIn.next(); //gives option to challenge boss or go back and train more

                        boolean finalBoss = true;
                        if(input.equalsIgnoreCase("1"))
                        {
                            finalBoss = false; //set finalBoss to false, skips the while loop coming up
                            //if player wants to challenge the boss, move on
                        }
                        else if (input.equalsIgnoreCase("2"))
                        {
                            //finalBoss is set as true, so the next while loop will run
                        }
                        while(finalBoss)
                        {
                            if(input.equalsIgnoreCase("2")) //if players wants to go back and train
                            {
                                System.out.println("Which monster field would you like to go to? (Enter a number between 1 - 6)");
                                System.out.println("You will have to play through the game from where you go back to.");
                                int input1 = getIn.nextInt();//input the number of the monster they want to go back to 
                                b = input1 - 1; //the number they input - 1 is the index of that monster so set b to the index
                                finalBoss = false;
                            }
                            else //if input is not valid
                            {
                                System.out.println("Input invalid.");
                                continue;
                            }
                        }
                    }
                    monsterKill = 0; //set monsterKill to 0 everytime player moves on
                    timesRun = 0; //set timesRun to 0 everytime player moves on
                    a[b].setHealth(a[b].getMaxHealth()); //set next monster to maximum health
                    swordItem = b;
                    armorItem = b;
                    continue;
                }
                else //input is not valid
                {
                    System.out.println("Input not valid"); //input not valid
                }
            }
        }
        if(a[5].getHealth() == 0) //if final monster is defeated
        {
            System.out.println("The Ancient Dragon has been defeated!"); 
            System.out.println("All of the monsters throughout the world have calmed down.");
            System.out.println(MC.getName() + " is a hero! You have saved the world! Congratulations, you beat the game!");
            System.out.println("Game will exit in 3 seconds.");
            delay(3);
            System.exit(0);
        }
    }

    public static void delay(int x)
    {
        try 
        {
            Thread.sleep(x*1000); //1000 milliseconds is one second.
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }
}