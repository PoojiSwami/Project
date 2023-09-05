package PartA;

import java.util.Scanner;

public class ComputerStore {
    public static void main(String[] args) {
        try {
            Scanner kb = new Scanner(System.in);
            System.out.println("Welcome to Computer Store!! ");
            int size = 0;
            do {
                System.out.println("Enter the maximum of computers that computer store can contain : ");
                size = kb.nextInt();
                if (checkNegative(size))
                    System.out.println("Invalid, the inventory should be positive");
            } while (checkNegative(size));
            Computer[] inventory = new Computer[size];
            int mainMenu;
            do {
                System.out.println("What do you want to do?\n" +
                        "1.\tEnter new computers (password required)\n" +
                        "2.\tChange information of a computer (password required)\n" +
                        "3.\tDisplay all computers by a specific brand\n" +
                        "4.\tDisplay all computers under a certain a price.\n" +
                        "5.\tQuit\n" +
                        "Please enter your choice >\n");
                mainMenu = kb.nextInt();
                int compcount;
                switch (mainMenu) {
                    case 1:
                        if (!checkPassword())
                            break;
                        int createdcomp = Computer.findNumberOfCreatedComputers();

                        do {
                            System.out.println("How many computers you want to enter : ");
                            compcount = kb.nextInt();
                            if (compcount > size) {
                                System.out.println("There are only " + (size - createdcomp) + " empty spaces available. Please enter a valid number.");
                            }
                        } while (size <= createdcomp);
                        for (int i = createdcomp; i < compcount; i++) {
                            System.out.println("Enter computer" + (i + 1) + "'s brand : ");
                            String brand = kb.next();
                            System.out.println("Enter computer" + (i + 1) + "'s model : ");
                            String model = kb.next();
                            double price;
                            do {
                                System.out.println("Enter computer" + (i + 1) + "'s price : ");
                                price = kb.nextDouble();
                                if (checkNegative(price))
                                    System.out.println("Invalid, price should be positive");
                            } while (checkNegative(price));
                            inventory[i] = new Computer(brand, model, price);
                            inventory[i].setBrand(brand);
                            inventory[i].setModel(model);
                            inventory[i].setPrice(price);
                            Computer.displayComputer(inventory[i]);
                        }
                        break;
                    case 2:
                        if (!checkPassword())
                            break;
                        int compnum, option, changeval;
                        do {
                            System.out.println("Enter the computer number you wish to modify : ");
                            compnum = kb.nextInt();
                            if (checkNegative(compnum)) {
                                System.out.println("Invalid, computer number should be positive");
                                break;
                            } else if (compnum > size) {
                                System.out.println("Given computer doesn't exists, you wish to" +
                                        "\n 1. enter another computer" +
                                        "\n 2. quit this operation and go back to the main menu");
                                option = kb.nextInt();
                            } else
                                break;
                        } while (option == 1);
                        if (compnum != 0)
                            compnum -= 1;
                        System.out.println("Computer " +
                                "\nBrand: " + inventory[compnum].getBrand()
                                + "\nModel: " + inventory[compnum].getModel()
                                + "\nSN: " + inventory[compnum].getSN()
                                + "\nPrice: $" + inventory[compnum].getPrice());
                        do {
                            System.out.println("What information would you like to change?\n" +
                                    "1.\tbrand\n" +
                                    "2.\tmodel\n" +
                                    "3.\tSN\n" +
                                    "4.\tprice\n" +
                                    "5.\tQuit\n" +
                                    "Enter your choice >\n");
                            changeval = kb.nextInt();
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
                                    inventory[compnum].setBrand(newModel);
                                    Computer.displayComputer(inventory[compnum]);
                                    break;
                                case 3:
                                    System.out.println("Enter the new serial number : ");
                                    String newSN = kb.next();
                                    inventory[compnum].setBrand(newSN);
                                    Computer.displayComputer(inventory[compnum]);
                                    break;
                                case 4:
                                    System.out.println("Enter the new price : ");
                                    String newprice = kb.next();
                                    inventory[compnum].setBrand(newprice);
                                    Computer.displayComputer(inventory[compnum]);
                                    break;
                                case 5:
                                    break;
                            }
                        } while (changeval != 5);
                        break;
                    case 3:
                        System.out.println("Enter Brand name : ");
                        String brandname = kb.next();
                        if (findComputersBy(inventory, brandname) == 0)
                            System.out.println("There are no computers with the brand name as " + brandname);
                        break;
                    case 4:
                        System.out.println("Enter the price value to display the computers cheaper than the given price: ");
                        double pr = kb.nextDouble();
                        if (findCheaperThan(inventory, pr) == 0)
                            System.out.println("There are no computers cheaper than " + pr);
                        break;
                    case 5:
                        System.out.println("Thank you for visiting Computer store!! \nHope we have serverd you the best!");
                        System.exit(0);
                }
            } while (mainMenu != 5);
        }
        catch (Exception e){
            System.out.println("An exception occurred!\n"+e);
        }
    }

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
        public static boolean checkNegative(double num){
            if (num < 0)
                return true;
            return false;
        }
        public static int findComputersBy(Computer[] inventory, String brandname){
            int flag=0;
            for (int i = 0; i < inventory.length; i++) {
                if(inventory[i].getBrand().equals(brandname)) {
                    System.out.println("Computer's information of given brand :");
                    Computer.displayComputer(inventory[i]);
                    flag++;
                }
            }
            return flag;
        }

        public static int findCheaperThan(Computer[] inventory, double pr){
            int flag = 0;
            for (Computer ele:inventory) {
                if(ele.getPrice() < pr){
                    System.out.println("The computer/computers cheaper than the given price:");
                    Computer.displayComputer(ele);
                    flag++;
                }
            }
            return flag;
        }

}

