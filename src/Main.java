/**
 * Created by crys_ on 16.10.2017.
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(final String[] args) throws InterruptedException, IOException {
        Thread[] ceva = new Thread[2];
        int n, m, threadNr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Matrice nXm" + "\n");
        System.out.println("Dati n: ");
        n = Integer.parseInt(br.readLine());
        System.out.println("Dati m:");
        m = Integer.parseInt(br.readLine());
        System.out.println("Dati numarul de threaduri: ");
        threadNr = Integer.parseInt(br.readLine());


        putInFile(n, m, "matriceA.txt");
        putInFile(n, m, "matriceB.txt");
        putInFile(n, m, "m.txt");
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];

        readArrayFromFile(n, m, a, "matriceA.txt");
        readArrayFromFile(n, m, b, "matriceB.txt");

        Matrice matrice1 = new Matrice(n, m, a);
        Matrice matrice2 = new Matrice(n, m, b);
        Matrice resultMatrix = Matrice.inmulteste(matrice1, matrice2, threadNr);
       // Matrice resultMatrix = Matrice.aduna(matrice1, matrice2, threadNr);

         resultMatrix.printMatrice();
        // STOPSHIP: 16.10.2017

        int[][] dd = new int[n][m];
        long startttt = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                dd[i][j] = a[i][j] + b[i][j];
        }

       /* for (int i = 0; i < n; i++) {
            for (int j = 0; j < secondarray[0].length; j++) {
                for (int k = 0; k < firstarray[0].length; k++) {
                    result[i][j] += firstarray[i][k] * secondarray[k][j];
                }
            }
        }*/

        long finall = System.nanoTime();
        System.out.println("TIME secvential: " + (finall - startttt));
        System.out.println(startttt);
        System.out.println(finall);
    }

    public static void readArrayFromFile(int n, int m, int[][] a, String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = s.nextInt();
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void putInFile(int n, int m, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        Random rand = new Random();

        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                writer.print(rand.nextInt(10));
                writer.print(" ");
            }
            writer.println();
        }
        writer.close();
    }
}
