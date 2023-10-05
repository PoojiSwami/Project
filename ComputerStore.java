// Project : Computer Store
// Written by : Poojitha Kandru (2395225)

package PartA;

import java.util.Scanner;

public class ComputerStore {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to the Computer Store!! ");   //Welcome message
        int size = 0;
        do {
            try {                                    // takes the computer number from user
                System.out.println("Enter the maximum of computers that computer store can contain : ");
                size = kb.nextInt();
                if (checkNegative(size))                             //checks if its -ve
                    System.out.println("Invalid, the inventory should be positive");
            }catch (Exception e) {
                System.out.println("Invalid input, Please enter a valid input");
                System.out.println("An error occured!\n"+e);
                kb.nextLine();
            }
        } while (checkNegative(size));
        // Create an array 'inventory' to hold Computer objects based on the given size
        Computer[] inventory = new Computer[size];
        // Main menu options and functionality
        int mainMenu = 0;
        do {
            System.out.println("What do you want to do?\n" +
                    "1.\tEnter new computers (password required)\n" +
                    "2.\tChange information of a computer (password required)\n" +
                    "3.\tDisplay all computers by a specific brand\n" +
                    "4.\tDisplay all computers under a certain a price.\n" +
                    "5.\tQuit\n" +
                    "Please enter your choice >\n");
            try {
                mainMenu = kb.nextInt();
            }catch (Exception e) {			// handling the invalid values such as strings, special characters
                System.out.println("An exception occurred!\n" + e);
                kb.nextLine();
            }
            // Variable to store the number of computers to be entered
            int compcount = 0;
            switch (mainMenu) {
                case 1:
                    if (!checkPassword())      // Call the "checkPassword" function and validate if it's true or false
                        break;					// Break if it doesn't match
                    int createdcomp = Computer.findNumberOfCreatedComputers();  // Get the count of created computers
                    int flag1 = 0;
                    do {
                        flag1 = 0;
                        try {
                            System.out.println("How many computers you want to enter : ");
                            compcount = kb.nextInt();
                        }catch (Exception e){
                            System.out.println("An exception occurred!\n"+e);
                            flag1++;
                            kb.nextLine();
                        }
                        // Validating the number of computers to be entered
                        if (compcount > (size - createdcomp)) {
                            System.out.println("There are only " + (size - createdcomp) + " empty spaces available. Please enter a valid number.");
                            flag1++;
                        } else if (checkNegative(compcount)) {
                            System.out.println("Invalid Input, please enter a positive value");
                            flag1++;
                        } else {
                            for (int i = 0; i < compcount; i++) {
                                // Get computer details and store them in the inventory array
                                System.out.println("Enter computer" + (createdcomp + i + 1) + "'s brand : ");
                                String brand = kb.next();
                                System.out.println("Enter computer" + (createdcomp + i + 1) + "'s model : ");
                                String model = kb.next();
                                double price = 0;
                                do {
                                    try {
                                        System.out.println("Enter computer" + (createdcomp + i + 1) + "'s price : ");
                                        price = kb.nextDouble();
                                        if (checkNegative(price))
                                            System.out.println("Invalid, price should be positive");
                                    }catch(Exception e){
                                        System.out.println("An exception occurred!\n"+e);
                                        kb.nextLine();
                                    }
                                } while (checkNegative(price)|| price == 0);
                                // Create a new Computer object and set its details
                                inventory[createdcomp+i] = new Computer();
                                inventory[createdcomp+i].setBrand(brand);
                                inventory[createdcomp+i].setModel(model);
                                inventory[createdcomp+i].setPrice(price);
                                // Display the computer details
                                Computer.displayComputer(inventory[createdcomp]);
                            }
                        }

                    } while (size <= createdcomp || flag1 != 0);
                    break;
                case 2:
                    if (!checkPassword())
                        break;
                    int compnum, option = 0, changeval = 0, flag2 = 0,flag5 =1;
                    do {
                        try {
                            System.out.println("Enter the computer number you wish to modify : ");
                            compnum = kb.nextInt();
                            if (checkNegative(compnum)) {
                                System.out.println("Invalid, computer number should be positive");
                                flag2++;
                            }
                            else if (compnum > Computer.findNumberOfCreatedComputers()) {
                                System.out.println("Given computer doesn't exists, you wish to" +
                                        "\n 1. enter another computer" +
                                        "\n 2. quit this operation and go back to the main menu");
                                option = kb.nextInt();

                            }
                            else {
                                if (compnum != 0)
                                    compnum -= 1;
                                System.out.println("Computer " +
                                        "\nBrand: " + inventory[compnum].getBrand()
                                        + "\nModel: " + inventory[compnum].getModel()
                                        + "\nSN: " + inventory[compnum].getSN()
                                        + "\nPrice: $" + inventory[compnum].getPrice());

                                do {
                                    try {
                                        System.out.println("What information would you like to change?\n" +  //display update Menu
                                                "1.\tbrand\n" +
                                                "2.\tmodel\n" +
                                                "3.\tSN\n" +
                                                "4.\tprice\n" +
                                                "5.\tQuit\n" +
                                                "Enter your choice >\n");
                                        changeval = kb.nextInt();
                                    }catch (Exception e){
                                        System.out.println("An exception occurred!\n"+e);
                                        kb.nextLine();
                                    }
                                    switch (changeval) {
                                        case 1:
                                            System.out.println("Enter the new Brand name : ");
                                            String newBrand = kb.next();
                                            inventory[compnum].setBrand(newBrand);
                                            Computer.displayComputer(inventory[compnum]);
                                            break;
                                        case 2:
                                            System.out.println("Enter the new Model name : ");
                                            String newModel = kb.next();
                                            inventory[compnum].setModel(newModel);
                                            Computer.displayComputer(inventory[compnum]);
                                            break;
                                        case 3:
                                            int newSN = 0;
                                            do {
                                                try {
                                                    System.out.println("Enter the new serial number : ");
                                                    newSN = kb.nextInt();
                                                    if(checkNegative(newSN)) {
                                                        System.out.println("Enter a positive number");
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("An exception occurred!\n"+e);
                                                    kb.nextLine();
                                                }
                                            }while(newSN <=0);
                                            inventory[compnum].setSN(newSN);
                                            Computer.displayComputer(inventory[compnum]);
                                            break;
                                        case 4:
                                            double newprice = 0;
                                            do {
                                                try{
                                                    System.out.println("Enter the new price : ");
                                                    newprice = kb.nextDouble();
                                                    if(checkNegative(newprice)) {
                                                        System.out.println("Enter a positive number");
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("An exception occurred!\n"+e);
                                                    kb.nextLine();
                                                }
                                            }while(newprice <= 0);
                                            inventory[compnum].setPrice(newprice);
                                            Computer.displayComputer(inventory[compnum]);
                                            break;
                                        case 5:
                                            flag5 =0;
                                            break;
                                        default:
                                            System.out.println("Enter a number between 1 and 5");
                                            flag2++;
                                            break;
                                    }
                                } while (changeval != 5 );
                            }
                        }catch (Exception e){
                            System.out.println("An exception occurred!\n"+e);
                            flag2 ++;
                            kb.nextLine();
                        }
                    } while ((option == 1 || flag2 != 0) && flag5 != 0 );
                    break;
                case 3:
                    try {
                        System.out.println("Enter Brand name : ");
                        String brandname = kb.next();
                        if (findComputersBy(inventory, brandname) == 0) {
                            System.out.println("There are no computers with the brand name as " + brandname);
                        }
                    }catch(Exception e){
                        System.out.println("An exception occurred!\n"+e);
                        kb.nextLine();
                    }

                    break;
                case 4:
                    double pr =0;
                    do {
                        try{
                            System.out.println("Enter the price value to display the computers cheaper than the given price: ");
                            pr = kb.nextDouble();
                            if(checkNegative(pr)) {
                                System.out.println("Enter a positive number");
                            }
                        }catch (Exception e){
                            System.out.println("An exception occurred!\n"+e);
                            kb.nextLine();
                        }
                    }while(pr <= 0);
                    if (findCheaperThan(inventory, pr) == 0)
                        System.out.println("There are no computers cheaper than " + pr);
                    break;
                case 5:
                    System.out.println("Thank you for visiting the Computer store!! \nHope we have serverd you the best!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input, Please enter a valid input");
            }
            // Looping until the user chooses to quit
        } while (mainMenu != 5);
    }
    // Function to check the password
    public static boolean checkPassword(){
        Scanner kb = new Scanner(System.in);
        String psd;
        int loopCount = 0;
        do {
            System.out.println("Enter the password: ");
            psd = kb.next();
            if (psd.equals("password"))
                return true;
            loopCount++;
            System.out.println("Invalid, Please enter a valid password!.");
            if (loopCount == 2)
                System.out.println("This is your last attempt to enter a valid password!");
        } while (loopCount < 3);
        return false;
    }
    // Function to check if a number is negative
    public static boolean checkNegative(double num){
        if (num <= 0)
            return true;
        return false;
    }
    // Function to find computers by brand name
    public static int findComputersBy(Computer[] inventory, String brandname){
        int flag=0;
        for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
            if(inventory[i].getBrand().equalsIgnoreCase(brandname)) {
                System.out.println("Computer's information of given brand :");
                Computer.displayComputer(inventory[i]);
                flag++;
            }
        }
        return flag;
    }
    // Function to find computers cheaper than a given price
    public static int findCheaperThan(Computer[] inventory, double pr){
        int flag = 0;
        for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
            if(inventory[i].getPrice() <= pr){
                System.out.println("The computer/computers cheaper than the given price:");
                Computer.displayComputer(inventory[i]);
                flag++;
            }
        }
        return flag;
    }

}
