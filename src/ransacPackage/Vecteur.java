package ransacPackage;

public class Vecteur {
	
	private double abs;
	private double ord;
	private double cot;
	
	public Vecteur(Point3D p1, Point3D p2) {
		abs = p2.getX() - p1.getX();
		ord = p2.getY() - p1.getY();
		cot = p2.getZ() - p1.getZ();
	}
	
	public Vecteur(double ab, double or, double co) {
		abs = ab;
		ord = or;
		cot = co;
	}
	// Avoir acces à l'abscisse d'un vecteur
	public double getAbs() {
		return abs;
	}
	
	// avoir acces à l''ordonné d'un vecteur 
	public double getOrd() {
		return ord;
	}
	
	// retourner la cote du vecteur
	public double getCot() {
		return cot;
	}
	
	/*Cette fonction est assez importante car elle nous permet de calculer le produit
	 * vectoriel de deux vecteurs.
	 * Ce produit vectiel estimportant car grace à ce dernier on connait un vecteur normal au plan
	 * et donc ses coefficients a, b et c dans une equation de plan dont la forme est : ax + by + cz + d = 0
	 */
	public Vecteur produitVectoriel( Vecteur v) {
		
		Vecteur Prodvect = new Vecteur(0, 0, 0);
		Prodvect.abs = this.ord * v.cot - this.cot * v.ord;
		Prodvect.ord = -1*(this.abs*v.cot - this.cot*v.abs);
		Prodvect.cot = this.abs*v.ord - this.ord*v.abs;
		return Prodvect;
	}
	
	// calcul du produit scalaire de 2 vecteurs. cela permet de verifier que les troi points constituant
	// les deux vecteurs ne sont pas alignés
	public double produitScalaire(Vecteur v) {
		
		double scal = this.abs*v.abs + this.ord*v.ord + this.cot*v.cot;		
		return scal;
	}
	
	
}
