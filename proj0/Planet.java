public class Planet{
	static final double G=6.67e-11;
	double xxPos,yyPos,xxVel,yyVel,mass;
	String imgFileName;
	public Planet(double xP,double yP,double xV,
		          double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet p){
		double dx=this.xxPos-p.xxPos;
		double dy=this.yyPos-p.yyPos;
	    return Math.sqrt(dx*dx+dy*dy);
	}
	public double calcForceExertedBy(Planet p){
		double r=this.calcDistance(p);
		return (G*this.mass*p.mass/r/r);
	}
	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] planets){
		double NetFx=0;
		for(Planet p:planets){
			if(p.equals(this)){
				continue;
			}
			NetFx+=this.calcForceExertedByX(p);
		}
		return NetFx;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double NetFy=0;
		for(Planet p:planets){
			if(p.equals(this)){
				continue;
			}
			NetFy+=this.calcForceExertedByY(p);
		}
		return NetFy;
	}
	public void update(double dt,double NetFx,double NetFy){
		double ax=NetFx/this.mass;
		double ay=NetFy/this.mass;
		this.xxVel+=ax*dt;
		this.yyVel+=ay*dt;
		this.xxPos+=dt*this.xxVel;
		this.yyPos+=dt*this.yyVel;
	}
	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}