import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        double real = 0; //действительная часть
        double noreal = 0;//мнимая часть
        int i;
        for (i=0; i<MAX_ITERATIONS && real*real+noreal*noreal < 4; i++){
            double real2 = real*real - noreal*noreal + x;
            double notreal2 = -2 * real * noreal + y;
            real = real2;
            noreal = notreal2;
        }
        if (i == MAX_ITERATIONS) return -1;
        return i;
    }

    public String toString() {
        return "Tricorn";
    }
}
