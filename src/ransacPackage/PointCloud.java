package ransacPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;


public class PointCloud {

	private ArrayList<Point3D> ensembleDesPoints;
	
	// Constructeur avec le fichier
	public PointCloud(String filename)  {
		
		ensembleDesPoints = new ArrayList<Point3D>();
		
		try {
			// Lecture des données du fichier 
			BufferedReader entree = new BufferedReader (new FileReader (filename));
			String ligne = entree.readLine() ;
			while (true)
			{   

				String ligneLue = entree.readLine() ;
				
				if (ligneLue == null) 	break ;
				
				//On separe les les données lu pour en extraire les double puis on utilise ces double pour 
				StringTokenizer tok = new StringTokenizer (ligneLue, "\t") ;
				
				double X,Y,Z;
				X = Double.parseDouble (tok.nextToken());
				
				Y = Double.parseDouble (tok.nextToken());
				
				
				Z = Double.parseDouble (tok.nextToken());
				
				// creer un Point3D et ajouter le point à la liste des points.
				Point3D p = new Point3D(X,Y,Z);
				
				ensembleDesPoints.add(p);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// constructer vide
	public PointCloud() {
		ensembleDesPoints = new ArrayList<Point3D>();
	}
	
	//A addPoint method that adds a point to the point cloud
	public void addPoint(Point3D pt) {	
		ensembleDesPoints.add(pt);
	}
	
	//A getPoint method that returns a random point from the cloud
	public Point3D getPoint() {
		Point3D pt = null;
		int pos = 0;
		Random random = new Random();
		pos = random.nextInt(ensembleDesPoints.size());
		pt = ensembleDesPoints.get(pos);
		return pt;
	}
	
	public int getTaillePointCloud() {
		return ensembleDesPoints.size();
	}
	
	// A save method that saves the point cloud into a xyz file
	public void save(String filename) throws IOException {
		
		PrintWriter sortie = new PrintWriter(new FileWriter(filename));
		
		int compteur = 0;
		do {
			Point3D pt = ensembleDesPoints.get(compteur);
			compteur++;
			sortie.println(pt.getX()+ "\t" + pt.getY() + "\t" + pt.getZ());	
		}while(compteur < ensembleDesPoints.size());
		sortie.println("\n\nTAILLE DU PointCloud :    " + compteur);
		sortie.close();
	}
	
	// An iterator method that returns an iterator to the points in the cloud
	Iterator<Point3D> iterator(){
		return null;
	}
	
	public Point3D PointAtIndex(int i) {
		return ensembleDesPoints.get(i);
	}
	
	public void remove(Point3D p) {
		ensembleDesPoints.remove(p);
	}
	

	
	public PointCloud clone() {
		PointCloud p = new PointCloud();
		p.ensembleDesPoints = (ArrayList<Point3D>) ensembleDesPoints.clone();
		return p;
	}
}

	
