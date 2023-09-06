public class NBody
{


public  static double readRadius(String sourceLocation)
{
    In in = new In(sourceLocation);
    int numofAllPlanets= in.readInt();


    return Double.parseDouble(in.readString());
}

public static Planet[] readPlanets(String sourceLocation)
{
    In in = new In(sourceLocation);
    int numofplanets = in.readInt();
    double radius = in.readDouble();
    Planet[] allPlanets = new Planet[numofplanets];
    for(int i=0;i<numofplanets;i++)
    {
        allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());

    }
    return allPlanets;
}

public  static  void  main(String[] args)
{
    /*设置基本参数*/
    double T = Double.parseDouble(args[0]);
    double dt= Double.parseDouble(args[1]);
    String filename = args[2];
   // filename="data/"+filename;
    double universeRadius = NBody.readRadius(filename);
    Planet[] allPlanets = NBody.readPlanets(filename);
    String background = "images/starfield.jpg";
    int backwidth = 512;int backheight = 512;
    StdDraw.setCanvasSize(backwidth, backheight);
    StdDraw.setXscale(-1*universeRadius, universeRadius);
    StdDraw.setYscale(-1*universeRadius, universeRadius);
    StdDraw.enableDoubleBuffering();

    for(double time=0;time<=T;time+=dt)
    {
        double[] xForces = new double[allPlanets.length];
        double[] yForces = new double[allPlanets.length];
        for(int i = 0;i<allPlanets.length;i++)
        {
            xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
            yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);

        }
        for(int i = 0;i<allPlanets.length;i++)
        {
            allPlanets[i].update(dt, xForces[i], yForces[i]);

        }

        /*绘制图像*/
        StdDraw.clear();
        StdDraw.picture(0,0,background);
        //StdDraw.show();
        for(Planet p : allPlanets)
        {
            Planet tmp = new Planet(p);
            tmp.draw();
        }
        StdDraw.pause(10);

    }

    StdOut.printf("%d\n", allPlanets.length);
    StdOut.printf("%.2e\n", universeRadius);
    for (int i = 0; i < allPlanets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
    }




}

}
