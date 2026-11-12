import java.util.Scanner;

public class MoneyChangeGreedy {

    // Enfoque 1: aritmética básica
    private static int getChange(int m) {
        int count = m / 10;
        m = m % 10;
        count += m / 5;
        m = m % 5;
        count += m / 1;
        return count;
    }

    // Enfoque 2: voraz (siempre toma la moneda más grande posible)
    private static int getChangeGreedy(int m) {
        int changes = 0, count = 0;
        int[] denominations = {1, 5, 10};
        int max = denominations.length - 1; // índice de la mayor denominación disponible

        while (changes < m) {
            while (changes <= m && (m - changes) >= denominations[max]) {
                changes += denominations[max];
                count++;
            }
            max = max - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // Casos de prueba del enunciado
        System.out.println("Prueba 1: m=2  -> aritmética: " + getChange(2)
                + ", voraz: " + getChangeGreedy(2) + " (esperado 2)");
        System.out.println("Prueba 2: m=28 -> aritmética: " + getChange(28)
                + ", voraz: " + getChangeGreedy(28) + " (esperado 6)");
        System.out.println();

        // Prueba con entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor de m (1 a 1000): ");
        int m = scanner.nextInt();
        System.out.println("Mínimo de monedas (aritmética): " + getChange(m));
        System.out.println("Mínimo de monedas (voraz):      " + getChangeGreedy(m));
    }
}
