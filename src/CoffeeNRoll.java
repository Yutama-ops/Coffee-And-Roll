

public class CoffeeNRoll {
   public static void main(String[] args) {
      // Create Variables
      int opt;                                  // variable for option this one tells the program wich products we use
      int quantity;                             // variable for quantity this one tells the program how many products we buy
      int sale;                                 // variable for sale this one tells the program how much for each products we sell
      int paid;                                 // variable for paid this one tells the program how much money we use to pay
      int total = 0;                            // variable for total this one tells the program how much we make in a day / before we close the program
      int notes[][];                            // variable for notes this one tells the program to put a notes in a 2 dimentional array
      int coins[][];                            // variable for coins this one tells the program to put a coins in a 2 dimentional array   
      String s1, s2;                            // variable for s1 and s2 this one an empty variable to be assign to some string wich we will use multiple time in the program ahead
      String hline;                             // variable for hline and triple this one we use them to create a horizontal and triple line string
      String triple;
      ValidateInput in;                         // variable for in is a variable that we create to be an object for class Validateinput
      Product order[];                          // variable for order is a variable that we create to be an object for class product in a list
      Change change;                            // variable for change is a variable that we create to be an object for class Change

      // Create and initialise objects
      order = new Product[6];                       //each element corresponds to a product, ignore 0 element
      order[1] = new Product("Schnitzel Roll");
      order[1].setPrice(1880);
      order[2] = new Product("Fish Roll");
      order[2].setPrice(1725);
      order[3] = new Product("Lamb Roll");
      order[3].setPrice(1460);
      order[4] = new Product("Ice Cream Roll");
      order[4].setPrice(675);
      order[5] = new Product("Coffee Latte");
      order[5].setPrice(340);
      change = new Change();                          //create Change object
      in = new ValidateInput();                       //validate input
      hline = new String("*****************************************"); //34 char horizontal line string
      triple = new String("________________________________________"); //34 char triple line string

      do {
         // Print Coffee N Roll menu, prices in dollars and cents
         System.out.printf("%n"+ triple);             //triple horizontal line (break line)
         System.out.printf("%n"+ hline + "%n");       //horizontal line
         for (int i = 1; i < 6; i++)
            System.out.printf("%4d. %-15s%10s%n", i, order[i].getName(), change.currency(order[i].getPrice()));
         System.out.printf("%4d. Done%n" + hline + "%n%n", 6); //Done and horizontal line

         do {
            // Get the order number
            s1 = "Enter the item number you want to order: ";
            s2 = "*** Only options 1 to 6 allowed";
            opt = in.getInput(s1, s2, 1, 6);          //description string, error msg, min and max integers
            if (opt == 6)
               break;                                 //exit while loop

            // Get ordered quantity, getInput() handles input errors
            s1 = "Enter quantity ordered: ";
            s2 = "*** Quantity is a positive integer";
            sale = order[opt].salePrice(in.getInput(s1, s2, 1, 200));  //limit quantity to 200
            order[opt].addToTotal(sale);              // track total sales for this product
            System.out.println("Sale price: " + change.currency(sale));

            // Amount of tendered money
            do {
               s1 = "Enter the amount paid in cents [0-1000000]: ";
               s2 = "*** Amount paid is a positive integer";
               paid = in.getInput(s1, s2, 1, 1000000); //max spend $10,000
               if (paid % 5 != 0)                     // 5 cents piece is AU smallest legal coin
                  System.out.println("*** Money paid must be in multiples of 5 cents");
               if (paid < sale)
                  System.out.println("*** Not enough money paid for the purchase");
            } while (paid % 5 != 0 || paid < sale);
            paid = paid - sale;                       //change in cents
            if (paid > 0)
               System.out.println("The change is: " + change.currency(paid));
            else
               System.out.println("Exact money tendered, no change required");
            System.out.println("");

            // Print to screen formatted change currency denominations
            change.denChange(paid);                   // calculate currency denominations
            notes = change.getNotes();
            coins = change.getCoins();
            System.out.println("The change returned to the customer is:");
            System.out.printf(hline + "%n");             //horizontal line
            for (int i = 0; i < 5; i++)
               if (notes[i][0] > 0)
                  System.out.printf("| Number of %3d dollar notes: %2d |%n", notes[i][1]/100, notes[i][0]);

            for (int i = 0; i < 6; i++)
               if (coins[i][0] > 0)
                  if (coins[i][1] < 100)
                     System.out.printf("| Number of %3d cents  coins: %2d |%n", coins[i][1], coins[i][0]);
                  else
                     System.out.printf( "| Number of %3d dollar coins: %2d |%n", coins[i][1]/100, coins[i][0]);
            System.out.printf(hline + "%n");             //horizontal line
         } while (opt < 1 || opt > 6);
      } while (opt != 6);

      for (int i = 1; i < 6; i++)
         total += order[i].getTotal();
      System.out.printf("Total %14s Sales: %9s%n", "Daily", change.currency(total));

      for (int i = 1; i < 6; i++)
         System.out.printf("Total %14s Sales: %9s%n", order[i].getName(), change.currency(order[i].getTotal()));
   } // end of main
} // end of class CoffeeNRoll
