package First_Lab_Java;

public class Sum {
    private double x;
    private double e;

    Sum() {
        x = 0.0;
        e = 1.0;
    }

    public Sum(double x, double e) {
        this.x = x;
        this.e = e;
    }

    public double sum() {
        double current = 0;
        double sum = 0;
        int k = 1;

        current = (Math.pow(x, (3 * k * k)));
        sum += current;

        double multiplier = 0;

        while (Math.abs(current) >= e) {
            multiplier = Math.pow(x, (3*(k + 1)*(k + 1) - 3*(k)*(k)));
            current = current * multiplier;
            sum += current;
            k++;
        }

        return sum;
    }
}
