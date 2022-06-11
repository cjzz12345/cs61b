public class NBody{
	public static double readRadius(String filename){
		In in =new In(filename);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets(String filename){
		In in=new In(filename);
		int number=in.readInt();
		in.readDouble();
		Planet[] planets=new Planet[number];
		for(int i=0;i<number;i++){
			planets[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),
				in.readDouble(),in.readString());
		}
		return planets;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double Radius=NBody.readRadius(filename);
		Planet[] planets=NBody.readPlanets(filename);
		StdDraw.setScale(-Radius,Radius);
		StdDraw.picture(0,0,"images/starfield.jpg",2*Radius,2*Radius);
		StdDraw.show();
		for(Planet p:planets){
			p.draw();
		}
		StdDraw.show();
		for(double t=0;t<T;t+=dt){
			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for(int i=0;i<planets.length;i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg",2*Radius,2*Radius);
			for(Planet p:planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
				planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}