/**
 * Created by crys_ on 16.10.2017.
 */

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Matrice {
    private int[][] matrice;
    private int linii, coloane;

    public Matrice(int linii, int coloane) {
        this.linii = linii;
        this.coloane = coloane;
        matrice = new int[linii][coloane];
    }

    public Matrice(int linii, int coloane, int[][] matrice) {
        this.linii = linii;
        this.coloane = coloane;
        this.matrice = matrice;
    }

    public static Matrice aduna(Matrice matrice1, Matrice matrice2, int nrThreads) throws InterruptedException {

        int operatiiThread = (matrice1.getNrLinii() * matrice1.getNrColoane()) / nrThreads;
        int rest = (matrice1.getNrLinii() * matrice1.getNrColoane()) % nrThreads;
        //Thread[] threads = new Thread[nrThreads];
        // System.out.println("nr threadruri este: " + threads.length);
        Matrice resultMatrix = new Matrice(matrice1.linii, matrice1.coloane);

        ExecutorService service = Executors.newFixedThreadPool(nrThreads);


        int iStart = 0, jStart = 0, iStop = 0, jStop = 0;

        ArrayList<MSumThread> threads = new ArrayList<>();

        for (int i = 0; i < nrThreads; ++i) {
            int operatiiFinal;
            if (rest > 0) {
                operatiiFinal = operatiiThread + 1;
                rest--;
            } else {
                operatiiFinal = operatiiThread;
            }
            //System.out.println("op "+operatiiFinal);

            while (operatiiFinal != 0) {
                if (jStop == matrice1.getNrColoane()) {
                    jStop = 0;
                    iStop++;
                }
                jStop++;
                if (jStop == matrice1.getNrColoane()) {
                    jStop = 0;
                    iStop++;
                }
                operatiiFinal--;
            }
            System.out.println("istop"+iStop);
            System.out.println("jstop"+jStop);

            //final int  ii = iStart, iii = iStop, jj = jStart, jjj = jStop;
            //threads[i] =  new Thread(new MSumThread(matrice1, matrice2, resultMatrix, ii, iii, jj, jjj));
            //service.execute(new MSumThread(matrice1, matrice2, resultMatrix, iStart, jStart, iStop, jStop));
            threads.add(new MSumThread(matrice1, matrice2, resultMatrix, iStart, iStop, jStart, jStop));


            // next thread limits
            iStart = iStop;
            jStart = jStop + 1;
            if (jStart > matrice1.coloane) {
                jStart = 0;
                ++iStart;
            }
            System.out.println("istart"+iStart);
            System.out.println("jstart"+jStart);
        }

        long start = System.nanoTime();
        for (MSumThread tt : threads) {
            tt.start();
        }
        for (MSumThread tt : threads) {
            tt.join();
        }
/*
        for (int i = 0; i<threads.length;i++)
        {
            threads[i].start();
        }
        for (int i = 0; i < nrThreads; i++)
            threads[i].join();
        service.shutdown();
*/
        long stop = System.nanoTime();
        System.out.println("TIME: " + (stop - start));
        System.out.println(start);
        System.out.println(stop);

        return resultMatrix;
    }

    public static Matrice inmulteste(Matrice matrice1, Matrice matrice2, int nrThreads) {
        int operatiiThread = (matrice2.linii * matrice2.coloane) / nrThreads;
        int rest = (matrice2.linii * matrice2.coloane) % nrThreads;
        Thread[] threads = new Thread[nrThreads];

        //ArrayList<MProdThread> threadss = new ArrayList<>();


        Matrice resultMatrix = new Matrice(matrice1.linii, matrice2.coloane);
        ExecutorService service2 = Executors.newFixedThreadPool(nrThreads);

        long start = System.nanoTime();

        int iStart = 0, jStart = 0, iStop = 0, jStop = 0;
        for (int i = 0; i < nrThreads; i++) {
            int operatiiFinal;
            if (rest > 0) {
                operatiiFinal = operatiiThread + 1;
                rest--;
            } else {
                operatiiFinal = operatiiThread;
            }
            while (operatiiFinal != 0) {
                if (jStop == matrice1.getNrColoane()) {
                    jStop = 0;
                    ++iStop;
                }
                jStop++;
                operatiiFinal--;
            }
            System.out.println("istop"+iStop);
            System.out.println("jstop"+jStop);

            final int ii = iStart, iii = iStop, jj = jStart, jjj = jStop;

            threads[i] = new Thread(new MProdThread(matrice1, matrice2, resultMatrix, iStart, iStop, jStart, jStop));
            service2.execute(new MProdThread(matrice1, matrice2, resultMatrix, iStart, jStart, iStop, jStop));
            // threads[i].start();
            iStart = iStop;
            jStart = jStop + 1;
            if (jStart == matrice2.coloane) {
                jStart = 0;
                ++iStart;
            }
        }

        /*for (MProdThread tt : threadss) {
            tt.start();
        }
        for (MProdThread tt : threadss) {
            try {
                tt.join();
            }
            catch (InterruptedException e) {

            }
        }*/
        for (int i = 0; i<nrThreads; i++)
            threads[i].start();
        for (int i = 0; i < nrThreads; ++i)
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        service2.shutdown();
        long stop = System.nanoTime();
        System.out.println("TIME: " + (stop - start));
        System.out.println(start);
        System.out.println(stop);
        return resultMatrix;
    }

    public int getNrLinii() {
        return linii;
    }

    public int getNrColoane() {
        return coloane;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(int i, int j, int sum) {
        matrice[i][j] = sum;
    }

    public void printMatrice() {
        for (int i = 0; i < linii; ++i) {
            for (int j = 0; j < coloane; ++j) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}
