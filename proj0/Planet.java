public class Planet{

private static final double G = 6.67e-11;
public double xxPos;
public double yyPos;
public double xxVel;
public double yyVel;
public double mass;
public String imgFileName;
public Planet(double xP, double yP, double xV, double yV, double m, String img)
{
	xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass  = m;
	imgFileName = img;

}

public Planet(Planet p)
{
xxPos = p.xxPos;
yyPos = p.yyPos;
xxVel=p.xxVel;
yyVel=p.yyVel;
mass=p.mass;
imgFileName = p.imgFileName;
}



private   boolean equal(Planet p)
{
	if(this==p) return true;
	else return false;
}

public double calcDistance(Planet p)
{
	return Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos)+(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));

}

public double calcForceExertedBy(Planet p)
{
	return G*(this.mass*p.mass)/(this.calcDistance(p)*this.calcDistance(p));
}

public  double calcForceExertedByX(Planet p)
{
	return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
}
public  double calcForceExertedByY(Planet p)
{
	return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
}

public  double calcNetForceExertedByX(Planet[] allPlanets)
{
	double sumX = 0;
	for(Planet p : allPlanets){
		if(!this.equal(p)) sumX+=this.calcForceExertedByX(p);
	}
	return  sumX;
}

public  double calcNetForceExertedByY(Planet[] allPlanets)
{
		double sumY = 0;
		for(Planet p : allPlanets){
			if(!this.equal(p)) sumY+=this.calcForceExertedByY(p);
		}
		return  sumY;
}

/*计算速度与加速度*/
public void update(double dt, double xForce, double yForce)
{
	double accelerationX=xForce/this.mass;
	double accelerationY=yForce/this.mass;
	this.xxVel = this.xxVel+dt*accelerationX;
	this.yyVel = this.yyVel+dt*accelerationY;
	this.xxPos = this.xxPos+dt*xxVel;
	this.yyPos = this.yyPos+dt*yyVel;
}

public  void  draw()
{
	//System.out.println("come to draw");
	//StdDraw.clear();
	StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	StdDraw.show();
}


}