import org.testng.annotations.Test;

import java.io.*;
import java.util.Random;

public class MatriceTest extends  Main{
    static Random rand = new Random();

    @Test
    public  void test1() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
        int n = 10, m = 10, nrThreaduri = 5;

        putInFile(n, m, "matriceA.txt");
        putInFile(n, m, "matriceB.txt");
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        readArrayFromFile(n, m, a, "matriceA.txt");
        readArrayFromFile(n, m, b, "matriceB.txt");

        Matrice matrice1 = new Matrice(n,m,a);
        Matrice matrice2 = new Matrice(n,m,b);
        System.out.println("Produs");
        Matrice resultMatrix = Matrice.inmulteste(matrice1, matrice2, nrThreaduri);
//        resultMatrix.printMatrice();

        System.out.println("Suma");
        resultMatrix = Matrice.aduna(matrice1, matrice2, nrThreaduri);
//        resultMatrix.printMatrice();
    }

    @Test
    public  void test2() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
        int n = 1000, m = 1000, nrThreaduri = 5;

        putInFile(n, m, "matriceA.txt");
        putInFile(n, m, "matriceB.txt");
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        readArrayFromFile(n, m, a, "matriceA.txt");
        readArrayFromFile(n, m, b, "matriceB.txt");

        Matrice matrice1 = new Matrice(n,m,a);
        Matrice matrice2 = new Matrice(n,m,b);
        System.out.println("Produs");
        Matrice resultMatrix = Matrice.inmulteste(matrice1, matrice2, nrThreaduri);
        System.out.println("Suma");
        resultMatrix = Matrice.aduna(matrice1, matrice2, nrThreaduri);
    }

}