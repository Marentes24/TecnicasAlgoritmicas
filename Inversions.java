import java.io.*;
import java.util.*;

public class Inversions {

    // Combina las dos mitades ordenadas y cuenta las inversiones entre ellas
    private static long count(int[] a, int[] aux, int left, int mid, int right) {
        long numberOfInversions = 0;

        for (int k = left; k <= right; k++)
            aux[k] = a[k];

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid)                  a[k] = aux[j++];
            else if (j > right)           a[k] = aux[i++];
            else if (aux[i] <= aux[j])    a[k] = aux[i++];
            else {
                a[k] = aux[j++];
                numberOfInversions += (mid - i + 1);
            }
        }
        return numberOfInversions;
    }

    // Divide y Vencerás: cuenta inversiones de a[left..right]
    private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int mid = left + (right - left) / 2;
        numberOfInversions += getNumberOfInversions(a, aux, left, mid);
        numberOfInversions += getNumberOfInversions(a, aux, mid + 1, right);
        numberOfInversions += count(a, aux, left, mid, right);
        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a) {
        int[] aux = new int[a.length];
        return getNumberOfInversions(a, aux, 0, a.length - 1);
    }

    // Versión ingenua O(n^2), usada para comparar en el stress testing
    private static long getNumberOfInversionsNaive(int[] a) {
        long number = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    number++;
                }
            }
        }
        return number;
    }

    // Compara ambas versiones con arreglos aleatorios hasta encontrar una diferencia
    private static void stressTesting() {
        Random r = new Random();
        int[] a = new int[10];
        while (true) {
            for (int i = 0; i < a.length; i++) {
                a[i] = r.nextInt(100);
            }
            // Se copia porque la versión de Divide y Vencerás ordena el arreglo
            long number1 = getNumberOfInversionsNaive(a);
            long number2 = getNumberOfInversions(a.clone());
            System.out.println(number1 + " " + number2);
            if (number1 != number2) {
                for (int i = 0; i < a.length; i++) {
                    System.out.printf("%d ", a[i]);
                }
                break;
            }
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        // Para ejecutar el stress testing: java Inversions stress
        // (el stress testing es un bucle infinito hasta hallar una diferencia; detener con Ctrl+C)
        if (args.length > 0 && args[0].equals("stress")) {
            stressTesting();
            return;
        }

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a));
    }
}
