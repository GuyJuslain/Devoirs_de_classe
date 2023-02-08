package ransacPackage;

import java.io.IOException;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename;
		double eps = 0.0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entrez le nom du fichier pointcloud d'entre : ");
		
		filename = sc.next();
		System.out.println();
		System.out.print("Entrez la valeur de epsilon (eps)  : ");
		
		eps = sc.nextDouble();
		
		PointCloud pc = new PointCloud(filename);
		
		PlaneRANSAC rsc= new PlaneRANSAC(pc);
		
		rsc.setEps(eps);
		
		rsc.run(1000, filename);
		
		System.out.println("\nVerifier les resultats dans les fichiers PointCloudMax");
		
		
	}

}
