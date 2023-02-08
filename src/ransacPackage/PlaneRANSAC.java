package ransacPackage;

import java.io.IOException;

public class PlaneRANSAC {

	private double eps;
	private	PointCloud pc;
	
	// constructeur avec un object PointCloud
	public PlaneRANSAC(PointCloud pc) {
		this.pc = pc;	
	}
	
	public void setEps(double eps) {
		this.eps = eps;
	}
	
	public double getEps() {
		return eps;
	}
	
	//le calcul du nombre d'iteraction
	public int getNumberOfIteractions(double confidence, double pourcentageOfPointsOnPlane) {
		
		int nbOfIteraction = 0;
		// Provient de la formule donnée
		nbOfIteraction = (int) ((int) Math.log(1 - confidence)/Math.log(1 - Math.pow(pourcentageOfPointsOnPlane,3)));
		return nbOfIteraction;
	}
	
	
	public void run(int numberOfIteractions, String filename) throws IOException {
		
		int num = 1;
		
		do {
			PointCloud pointCloudDeBase = new PointCloud();	// PointCloud de base; qui sera formé à partir du premier plan trouvé 
			Plane3D planDebase = new Plane3D(pc.getPoint(), pc.getPoint(), pc.getPoint());	// premier plan generé
			
			for(int i = 0; i < pc.getTaillePointCloud(); i++) {
				Point3D point = pc.PointAtIndex(i);			// on verifie si le point appartient au plan
				if(planDebase.getDistance(point) <= eps) {
					pointCloudDeBase.addPoint(point);		// s'il appartient on l'ajoute au point cloude de base
				}
				
			}	

			double pourcentageOfPointsOnPlane = (double)pointCloudDeBase.getTaillePointCloud()/pc.getTaillePointCloud();
			
			double confidence = 0.99;
			//On calcule le nouveau d'iteractions
			int numberOfIteractionsMax = getNumberOfIteractions(confidence, pourcentageOfPointsOnPlane);
		
			PointCloud pointCloudcMax = new PointCloud();
			pointCloudcMax = pointCloudDeBase.clone();	// le Pointcloud Max qu'on veut extraire lors de ce run
			
			int i = 0;
			while(i < numberOfIteractionsMax) {
				
				// on genere un plan grace à 3 points generé aleatoirement. C'est le plan courant
				Plane3D planCourant = new Plane3D(pc.getPoint(), pc.getPoint(), pc.getPoint());	
				
				//On construit le pointCloud courant à partir du plan courant
				PointCloud pointcloudcourant = new PointCloud();
				for(int j = 0; j <  pc.getTaillePointCloud(); j++) {
					Point3D point = pc.PointAtIndex(j);
					if(planCourant.getDistance(point) <= eps) {
						pointcloudcourant.addPoint(point);
					}
				}
				
				
				// Si le pointcloud courant est de plus grande taille que le pointcloud max, il devient le pointcloud max
				// et on recalcule le nombre d'iteraction et recommence la recherche du nouveau plan Max 
				if(pointcloudcourant.getTaillePointCloud() > pointCloudcMax.getTaillePointCloud())
				{	
					pourcentageOfPointsOnPlane = (double)pointcloudcourant.getTaillePointCloud()/pc.getTaillePointCloud();
					numberOfIteractionsMax = getNumberOfIteractions(confidence, pourcentageOfPointsOnPlane);
					pointCloudcMax = pointcloudcourant.clone();
					i = 0;
				}
				else	
					i++;
			}
			
			System.out.println("Taille du PointCloudMax " + num + ":"+"   " + pointCloudcMax.getTaillePointCloud());
			// On supprime les points ne faisaint plus partir du pointcloud de depart.  
			//Pour eventuellement extraire d'autres pointCloud Max pour d'autres iteractions
			for(int k = 0; k < pointCloudcMax.getTaillePointCloud();  k++) {
				Point3D p = pointCloudcMax.PointAtIndex(k);
				pc.remove(p);
			}
			
			// On sauvegarde les points du pointcloud max
			String filename1 = "PointCloudMax" +num +".xyz"; 
			pointCloudcMax.save(filename1);
			num++;
		}while(num <= 3);
				
	}
}
