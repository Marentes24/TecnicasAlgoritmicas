import java.util.Scanner;

public class MoneyChangeDP {

    // Programación dinámica bottom-up: mínimo de monedas de 1, 3 y 4 para formar money
    private static int getChange(int money) {
        int[] coins = {1, 3, 4};
        int[] minCoins = new int[money + 1];

        for (int m = 1; m <= money; m++) {
            minCoins[m] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (m >= coins[j]) {
                    int numCoins = minCoins[m - coins[j]] + 1;
                    minCoins[m] = Math.min(minCoins[m], numCoins);
                }
            }
        }

        return minCoins[money];
    }

    public static void main(String[] args) {
        // Casos de prueba del enunciado
        System.out.println("Prueba 1: money=2  -> " + getChange(2) + " (esperado 2)");
        System.out.println("Prueba 2: money=34 -> " + getChange(34) + " (esperado 9)");
        System.out.println();

        // Prueba con entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor de money (1 a 1000): ");
        int money = scanner.nextInt();
        System.out.println("Mínimo de monedas: " + getChange(money));
    }
}
