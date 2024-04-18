package SD_CW;
public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void print(){
        System.out.println("");
        System.out.println("Name: "+person.getName());
        System.out.println("Surname: "+person.getSurname());
        System.out.println("email: "+person.getEmail());
        System.out.println("Row: "+row);
        System.out.println("Seat: "+seat);
        System.out.println("Price: "+price);
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", price=" + price +
                ", person=" + person +
                '}';
    }
}

