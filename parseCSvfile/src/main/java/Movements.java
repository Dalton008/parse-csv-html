import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Movements {
    private String accType;
    private String accNum;
    private String currency;
    private String date;
    private String ref;
    private String info;
    private String revenue;
    private String expenditure;
    private String path;
    private List<Movements> movementsList = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        this.path = pathMovementsCsv;
    }

    public void parseCsv() throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(getPath()));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>();
            for (int i = 0; i < splitedText.length; i++) {
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            Movements movements = new Movements(getPath());
            movements.accType = columnList.get(0);
            movements.accNum = columnList.get(1);
            movements.currency = columnList.get(2);
            movements.date = columnList.get(3);
            movements.ref = columnList.get(4);
            movements.info = columnList.get(5);
            movements.revenue = columnList.get(6);
            movements.expenditure = columnList.get(7);
            movementsList.add(movements);
        }
    }

    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

    public double getExpenseSum()
    {
        double totalExpenditure = 0;
        for (int i = 1; i < movementsList.size(); i++)
        {
            String str = movementsList.get(i).getExpenditure().replace(",", ".");
            str = str.replace("\"", "");
            totalExpenditure += Double.parseDouble(str);
        }
        return totalExpenditure;
    }

    public double getIncomeSum() {
        double totalRevenue = 0;
        for (int i = 1; i < movementsList.size(); i++)
        {
            String str = movementsList.get(i).getRevenue().replace(",", ".");
            str = str.replace("\"", "");
            totalRevenue += Double.parseDouble(str);
        }
        return totalRevenue;
    }

    public String getAccType() {
        return accType;
    }

    public String getAccNum() {
        return accNum;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }

    public String getRef() {
        return ref;
    }

    public String getInfo() {
        return info;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public String getPath() {
        return path;
    }

    public List<Movements> getMovementsList() {
        return movementsList;
    }
}
