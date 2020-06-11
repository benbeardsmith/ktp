public class Lab1 {
    public static void main(String[] args){
        double x1 = Double.valueOf(args[0]);
        double y1 = Double.valueOf(args[1]);
        double z1 = Double.valueOf(args[2]);
        Point3d point1 = new Point3d(x1,y1,z1);
        double x2 = Double.valueOf(args[3]);
        double y2 = Double.valueOf(args[4]);
        double z2 = Double.valueOf(args[5]);
        Point3d point2 = new Point3d(x2,y2,z2);
        double x3 = Double.valueOf(args[6]);
        double y3 = Double.valueOf(args[7]);
        double z3 = Double.valueOf(args[8]);
        Point3d point3 = new Point3d(x3,y3,z3);
        System.out.println(computeArea(point1, point2, point3));
    }

    public static double computeArea(Point3d point1, Point3d point2, Point3d point3){
        if (!point1.equil(point2) && !point2.equil(point3) && !point3.equil(point1)) {
            double a = point1.distanceTo(point2);
            double b = point2.distanceTo(point3);
            double c = point3.distanceTo(point1);
            double p = (a + b + c)/2;
            double S = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return (double) Math.round(S*100)/100;
        }
        System.out.println("координаты 2 или 3 точек равны");
        return 0;
    }
}
