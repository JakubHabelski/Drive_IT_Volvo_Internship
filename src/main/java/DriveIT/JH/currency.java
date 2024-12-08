package DriveIT.JH;

import lombok.AllArgsConstructor;

public class currency {


    private String name;
    private String symbol;

    public currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "currency{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
