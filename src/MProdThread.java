/**
 * Created by crys_ on 16.10.2017.
 */
public class MProdThread extends Thread{
    protected Matrice matrice1, matrice2, matrice3;
    protected int iStart, jStart, iStop, jStop;

    public MProdThread(Matrice matrice1, Matrice matrice2, Matrice matrice3,
                      int iStart, int jStart, int iStop, int jStop) {
        this.matrice1 = matrice1;
        this.matrice2 = matrice2;
        this.matrice3 = matrice3;
        this.iStart = iStart;
        this.iStop = iStop;
        this.jStart = jStart;
        this.jStop = jStop;
    }

    @Override
    public void run() {
        try {
            for (int i = iStart; i <= iStop; ++i) {
                for (int j = 0; j < matrice2.getNrColoane(); ++j) {
                    if (i == iStart) {
                        if (j >= jStart) {
                            int res = 0;
                            for (int index = 0; index < matrice1.getNrColoane(); ++index) {
                                res += (matrice1.getMatrice()[i][index] * matrice2.getMatrice()[index][j]);
                            }
                            matrice3.getMatrice()[i][j] = res;
                        }
                    } else if (i == iStop) {
                        if (j <= jStop) {
                            int res = 0;
                            for (int index = 0; index < matrice1.getNrColoane(); ++index) {
                                res += (matrice1.getMatrice()[i][index] * matrice2.getMatrice()[index][j]);
                            }
                            matrice3.getMatrice()[i][j] = res;
                        }
                    } else {
                        int res = 0;
                        for (int index = 0; index < matrice1.getNrColoane(); ++index) {
                            res += (matrice1.getMatrice()[i][index] * matrice2.getMatrice()[index][j]);
                        }
                        matrice3.getMatrice()[i][j] = res;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}