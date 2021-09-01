
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/data/movementList.csv";

        Movements movements = new Movements(path);
        System.out.println(movements.getPath());
        movements.parseCsv();

        System.out.println("total revenue = " + movements.getIncomeSum());
        System.out.println("total expenditure = " + movements.getExpenseSum());
    }
}
