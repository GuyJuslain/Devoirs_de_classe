package ransacPackage;

public class Plane3D {
	
	// coefficient a, b, c et d pour permettront d'avoir l'équation du plan
	double a;
	double b;
	double c;
	double d;

	/*Constructeur du plan à partir de 3 points
	 */
	public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
		// On calcule les coordonnées des 2 vecteurs du plan formés par les 3 points
		Vecteur p1p2 = new Vecteur(p1, p2);
		Vecteur p1p3 = new Vecteur(p1, p3);
		
		// On calcule le produit vectoriel des 2 vecteurs. le resultat est un vecteur normal au plan 
		// dont les coordonnées les coefficients a, b, c de l'equation du plan 
		Vecteur vecteurNormal = p1p2.produitVectoriel(p1p3);
		a = vecteurNormal.getAbs();
		b = vecteurNormal.getOrd();
		c = vecteurNormal.getCot();
		
		//on calcul le coefficient d
		d = -1*(a*p1.getX() + b*p1.getY() + c*p1.getZ());
	}
	// A constructor from parameters
	public Plane3D(double a, double b, double c, double d) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	// A getDistance method that returns the distance from a point to the plane
	public double getDistance(Point3D pt) {
		
		double dist = Math.abs(a*pt.getX() + b*pt.getY() + c*pt.getZ() + d)/Math.sqrt(a*a + b*b + c*c);
		return dist;
	}
	
	

}
