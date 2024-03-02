import java.util.*;
class randomNum
{       
   public int rand_num ()
    {
          Random rand = new Random(); 
          int upperbound = 100;
          int int_random = rand.nextInt(upperbound);
          return int_random;
    }
}
 class numberGame
    {
        public static void main(String[] args) 
        {
            Scanner sc = new Scanner(System.in);
            randomNum rg = new randomNum();
            int totalAttempts = 0, wins = 0;
            System.out.println("Hey there! \n Get Ready To Put Your Guessing Skills To Test ! \n Give it a shot!" );
            while( true)
            {
                int guess = rg.rand_num();
                System.out.println(" Rules :1) Number is in Between 0 to 100. \n        2)You Are Have 6 Chances." );
                int attempts = 0;
                while(attempts < 6)
                {
                    System.out.println("Enter Your Guess:");
                    int num = sc.nextInt(); 
                    attempts++;
                    if(num > guess)
                    {
                        System.out.println(" HINT : The Number is Lower Than "+ num);
                    }
                    else if(num < guess)
                    { 
                        System.out.println("HINT : The Number is Greater Than "+num);
                    }
                    else
                    {
                        System.out.println("You Nailed It !");
                        wins++;
                        break;
                    }
                    if(attempts == 6)
                    {
                        System.out.println("Sorry! You Are Out Of The Chances \n**The Number is : "+guess+" **");
                    }
                    else
                    {
                       System.out.println(" You Are Left with "+(6 - attempts)+" Chances");
                    }
                }
                totalAttempts += attempts;
                System.out.println(" Attemps = "+attempts);
                System.out.println(" Wins = "+ wins);
                double winrate =(double) wins/totalAttempts*100;
                System.out.printf("Your Winrate Is %.2f%%\n",winrate);
                System.out.println("Do you Want to play again (yes / no)");
                String playAgain = sc.next();
                if(!playAgain.equalsIgnoreCase("yes"))
                {
                    sc.close();
                    System.exit(0);
                }
                sc.nextLine();
            }
            
         }
        } 