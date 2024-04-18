package SD_CW;
import java.util.*;
import java.io.*;
public class Theatre {
    public static void main(String[] args) {
        int[] rowOne=new int[12];
        int[] rowTwo=new int[16];
        int[] rowThree=new int[20];
        ArrayList<Ticket> ticketList = new ArrayList<>();
        boolean loopTwo = true;  //Loop will only become false and close the program when option "0" is selected from the menu.
        while (loopTwo){
            System.out.println();
            System.out.println("Welcome to the New Theatre\n-----------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket\n2) Print seating area\n3) Cancel ticket\n4) List available seats\n5) Save to file\n6) Load from file\n7) Print ticket information and total price\n8) Sort tickets by price\n        0) Quit\n-----------------------");
            int option = validate("option", -1, 9);
            switch (option) {
                case 0:
                    loopTwo=false;
                    break;
                case 1:
                    buy_ticket(rowOne, rowTwo, rowThree,ticketList);
                    System.out.println("Please save your tickets after booking them using option 'No.6'.");
                    break;
                case 2:
                    System.out.println("    ***********\n    *  STAGE  *\n    ***********");
                    print_seating_area(rowOne);
                    print_seating_area(rowTwo);
                    print_seating_area(rowThree);
                    break;
                case 3:
                    cancel_ticket(rowOne, rowTwo, rowThree,ticketList);
                    break;
                case 4:
                    show_available(rowOne,12,1);
                    show_available(rowTwo,16,2);
                    show_available(rowThree,20,3);
                    break;
                case 5:
                    save(rowOne,rowTwo,rowThree);
                    break;
                case 6:
                    load(rowOne,rowTwo,rowThree);
                    break;
                case 7:
                    show_tickets_info(ticketList);
                    break;
                case 8:
                    sort_tickets(ticketList);
                    break;
            }
        }
    }
    public static void print_seating_area(int[] row) {
        int length=row.length;
        switch(length){
            case 12:
                System.out.print("    ");
                break;
            case 16:
                System.out.print("  ");
                break;
        }
        for (int i=0;i<length;i++) {
            if (row[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if(i==(length/2)-1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    public static int validate(String type, int min, int max) {    //This method is used to validate "option",row number and seat number accordingly.It is used in the buy_ticket,cancel_ticket method as well.
        boolean loop = true;
        int inputOne = 0;
        while (loop) {
            System.out.print("Please enter the " + type + " number("+(min + 1)+"-"+(max - 1) +"):");
            Scanner input = new Scanner(System.in);
            try {
                inputOne = input.nextInt();
                if ((inputOne > min) && (inputOne < max)) {
                    loop = false;
                } else {
                    if( type.equals("option")) {
                        System.out.println("Please enter an " + type + " number between " + (min + 1) + " and " + (max - 1) + ".");
                    }
                    else{
                        System.out.println("Please enter a " + type + " number between " + (min + 1) + " and " + (max - 1) + ".");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input,please try again:");
            }
        }
        return inputOne;
    }
    public static void buy_ticket(int[] row1, int[] row2, int[] row3,ArrayList<Ticket> tList) {
        boolean loop = true;
        boolean loop2=true; //Used to validate the price entered by the user.
        double price=0;
        String email = "";
        Scanner input=new Scanner(System.in);
        System.out.print("Please enter your first name:");
        String name= input.next();
        System.out.print("Please enter your surname:");
        String surname= input.next();
        System.out.print("Please enter your email:");
        email = input.next();
        while(!email.contains("@") || !email.contains(".")){
            System.out.print("Invalid email format,please enter your email again:");
            email = input.next();
        }
        while(loop2) {
            try {
                System.out.print("Please enter the ticket price($0-$100):");
                price = input.nextDouble();
                if(price>100 || price<0){
                    System.out.println("Price is out of the given range,please enter a price between 0 and 100.");
                }
                else{
                    loop2=false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input,please enter an number for the price");
            }
        }

        while (loop) {
            int rowNO = validate("row", 0, 4);
            switch (rowNO) {
                case 1:
                    int seat = validate("seat", 0, 13);
                    loop=seatAdder(row1,1,seat,name,surname,email,price, tList);
                    break;
                case 2:
                    seat = validate("seat", 0, 17);
                    loop=seatAdder(row2,2,seat,name,surname,email,price, tList);
                    break;
                case 3:
                    seat = validate("seat", 0, 21);
                    loop=seatAdder(row3,3,seat,name,surname,email,price,tList);
                    break;
            }
        }
    }
    //If no seats have been booked, the program won't let the user proceed with option 3 using the arrayChecker method.
    public static void cancel_ticket(int[] row1, int[] row2, int[] row3,ArrayList<Ticket> tList) {
        boolean loop = true;
        while (loop) {
            boolean checker=ArrayChecker(row1);
            boolean checker2=ArrayChecker(row2);
            boolean checker3=ArrayChecker(row3);
            if(checker && checker2 && checker3){
                System.out.println("No seats have been booked yet.");
                break;
            }
            else {
                int rowNO = validate("row", 0, 4);
                switch (rowNO) {
                    case 1:
                        int seat = validate("seat", 0, 13);
                        loop = seatRemover(row1, 1, seat, tList);
                        break;
                    case 2:
                        seat = validate("seat", 0, 17);
                        loop = seatRemover(row2, 2, seat, tList);
                        break;
                    case 3:
                        seat = validate("seat", 0, 21);
                        loop = seatRemover(row3, 3, seat, tList);
                        break;
                }
            }
        }
    }

    public static boolean ArrayChecker(int[] row){
        int len=row.length;
        boolean checker = true;
        for(int i=0;i<len;i++){
            if (row[i] == 1){
                checker=false;
                break;
            }
        }
        return checker;
    }

    //Method used to add a ticket to the arrays and the array list.This array is used in the buy_ticket method.
    public static boolean seatAdder(int[] row, int rowNO, int seat, String name, String surname, String email, double price, ArrayList<Ticket> tList) {
        boolean x = true;
        if (row[seat - 1] == 0) {
            row[seat - 1] = 1;
            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(rowNO, seat, price, person);
            tList.add(ticket);
            System.out.println("Your ticket for seat " + seat + " of row " + rowNO + " has been booked.");
            x = false;
        }
        else {
            System.out.println("This seat has already been reserved ,please select a new seat.");
        }
        return x;
    }

    //Method used to remove a ticket from the arrays and the array list.This array is used in the cancel_ticket method.
    public static boolean seatRemover(int[] row, int rowNO, int seat, ArrayList<Ticket> tList){
        if (row[seat - 1] == 1){
            row[seat - 1] = 0;
            System.out.println("Your ticket for seat " + seat + " of row " + rowNO + " has been cancelled.");
            for(Ticket element: tList) {
                if (element.getRow() == rowNO && element.getSeat() == seat) {
                    tList.remove(element);
                    break;
                }
            }
            return false;
        }
        else{
            System.out.println("This seat hasn't been booked ,please enter the correct seat number.");
            return true;
        }
    }

    public static void show_available(int[] row,int len,int rowNO) {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (row[i] == 0) {
                tempList.add(i + 1);
            }
        }
        int j=0;
        int len2= tempList.size();
        System.out.print("Seats available in row "+rowNO+ ": ");
        for (int element:tempList) {
            if(j==len2-1){
                System.out.print(element+".\n");
            }
            else {
                System.out.print(element+",");
            }
            j++;
        }
    }


    public static void save(int[] row1,int[] row2,int[] row3) {
        try {
            FileWriter myWriter= new FileWriter("Theatre.txt");
            for(int i=0;i<12;i++){
                myWriter.write(Integer.toString(row1[i]));
            }
            myWriter.write("\n");
            for(int i=0;i<16;i++){
                myWriter.write(Integer.toString(row2[i]));
            }
            myWriter.write("\n");

            for(int i=0;i<20;i++){
                myWriter.write(Integer.toString(row3[i]));
            }
            myWriter.close();
            System.out.println("Data was saved successfully to the text file.");
        }

        catch(IOException ex){
            System.out.println(ex);
            System.out.println("An error occurred while saving the file");

        }
    }


    public static void load(int[] row1,int[] row2,int[] row3) {
        try {
            File file = new File("Theatre.txt");
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine();
                int length=text.length();
                switch(length){
                    case 12:
                        for(int i=0;i<12;i++){
                            char chr=text.charAt(i);
                            row1[i]=Character.getNumericValue(chr);
                        }
                        break;
                    case 16:
                        for(int i=0;i<16;i++){
                            char chr=text.charAt(i);
                            row2[i]=Character.getNumericValue(chr);
                        }
                        break;
                    case 20:
                        for(int i=0;i<20;i++){
                            char chr=text.charAt(i);
                            row3[i]=Character.getNumericValue(chr);
                        }
                        break;
                }

            }
            System.out.println("File was loaded successfully.");
            file_reader.close();
        }
        catch(IOException ex){
            System.out.println(ex);
            System.out.println("Please use option 6 to store the data into the text file before loading.");
        }
    }

    public static void show_tickets_info(ArrayList<Ticket> tList){
        int totalPrice = 0;
        for(int i = 0; i< tList.size(); i++){
            totalPrice += tList.get(i).getPrice();
            tList.get(i).print();
        }
        System.out.println("Total price of the tickets: $"+totalPrice);
    }

    public static void sort_tickets(ArrayList<Ticket> tList){
        for (int i=0;i<tList.size();i++){
            for(int j=1;j<tList.size()-i-1;j++){
                if(tList.get(j).getPrice()>tList.get(j+1).getPrice()){
                    Ticket temp=tList.get(j);
                    tList.set(j, tList.get(j+1));
                    tList.set(j+1,temp);
                }
            }
        }
        for(int i = 0; i< tList.size(); i++) {
            tList.get(i).print();
        }

    }
}
