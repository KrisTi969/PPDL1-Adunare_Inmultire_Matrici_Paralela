/**
 * Created by crys_ on 16.10.2017.
 */
public class MSumThread extends Thread {
    protected Matrice matrice1, matrice2, matrice3;
    protected int iStart, jStart, iStop, jStop;

    public MSumThread(Matrice matrice1, Matrice matrice2, Matrice matrice3,
                      int iStart, int iStop, int jStart, int jStop) {
        this.matrice1 = matrice1;
        this.matrice2 = matrice2;
        this.matrice3 = matrice3;
        this.iStart = iStart;
        this.iStop = iStop;
        this.jStart = jStart;
        this.jStop = jStop;
        System.out.println(getName()+" "+iStart+" "+iStop+" "+jStart+" "+jStop);
    }

    @Override
    public void run() {

        Integer iStartLocal = iStart;
        Integer iStopLocal = iStop;

        if (jStart > 0) {
            iStartLocal ++;
            for (int i = jStart; i < matrice1.getNrColoane(); i++) {
                matrice3.setMatrice(iStart, i, matrice1.getMatrice()[iStart][i] + matrice2.getMatrice()[iStart][i]);
            }
        }
        if (jStop < matrice1.getNrColoane()) {
            iStopLocal --;
            for (int i = 0; i < jStop; i++) {
                matrice3.setMatrice(iStop, i, matrice1.getMatrice()[iStart][i] + matrice2.getMatrice()[iStart][i]);
            }
        }
        for (int i = iStart; i <= iStopLocal; i++) {
            for (int j = 0; j < matrice1.getNrColoane(); j++) {
                matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
        }
/*        if (jStart > 0) {
            for (int i = iStart + 1; i < iStop - 1; i++) {
                for (int j = 0; j < matrice2.getNrColoane(); j++)
                    matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
            for (int i = iStart; i <= iStart; i++) {
                for (int j = jStart; j < matrice2.getNrColoane(); j++)
                    matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
            for (int i = iStop; i <= iStop; i++) {
                for (int j = jStop; j < matrice2.getNrColoane(); j++)
                    matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
        } else if (jStop > 0) {
            for (int i = iStart; i <= iStop - 1; i++) {
                for (int j = 0; j < matrice2.getNrColoane(); j++)
                    matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
            for (int i = iStop; i <= iStop; i++) {
                for (int j = jStop; j < matrice2.getNrColoane(); j++)
                    matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
            }
        }*/

      /*  System.out.println("------------------------");
        System.out.println("istart: " + iStart);
        System.out.println("iStop: "  + iStop);
        System.out.println("jstart: "  + jStart);
        System.out.println("jstop: " + jStop);
        System.out.println("\n");*/
       /* try {
            for (int i = iStart; i <= iStop; i++) {
                for (int j = 0; j < matrice2.getNrColoane(); j++) {
                    *//*System.out.println("i start este: " + iStart);
                    System.out.println("i este: " + i);
                    System.out.println("j este: " + j);*//*

                    if (i == iStart) {
                        if (j >= jStart) {
                            matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
                        }
                    } else if (i == iStop) {
                        if (j <= jStop) {
                            matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
                        }
                    } else {
                        matrice3.setMatrice(i, j, matrice1.getMatrice()[i][j] + matrice2.getMatrice()[i][j]);
                    }
                }
            }
        } catch
                (ArrayIndexOutOfBoundsException e) {
          //  System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }*/
    }
}
