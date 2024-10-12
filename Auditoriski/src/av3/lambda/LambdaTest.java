package av3.lambda;

public class LambdaTest {
    public static void main(String[] args) {
        FunctionalInterface functionalInterface1 = (x, y) -> {
            System.out.println("text");
            return x + y;
        };

        FunctionalInterface functionalInterface2 = (x, y) -> x * y;
    }
}
