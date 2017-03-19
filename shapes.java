
/**
 *
 * @Filename shapes.java
 *
 * @Version $Id: shapes.java,v 1.0 2014/12/11 05:15:00 $
 *
 * @Revisions
 *     Initial Revision
 *
 */


/**
 * Simple class which extends the simpleShape class to create objects
 * 
 * @author Sumedha Singh
 * 
 */
public class shapes extends simpleShape {

    /**
     * Object selection variables
     */
	
    public static final int OBJ_SUN = 0;
    public static final int OBJ_E = 1;
    public static final int OBJ_SAT = 2;
    public static final int OBJ_BACKGROUND = 3;
    public static final int OBJ_Earth = 4;
    public static final int OBJ_UmbrellaHandle = 5;

    public static final int OBJ_Cylinder = 6;
    
    /**
     * Shading selection variables
     */
    
    public static final int SHADE_FLAT = 0;
    public static final int SHADE_NOT_FLAT = 1;

    /**
     * Constructor
     */
    public shapes() {
    }
    
    
    /**
	 * function to draw shapes
	 * @param choice
	 * @param shadingType
	 */
    public void makeShape( int choice, int shadingType ) {
    	if(choice == 0 || choice == 1 || choice == 4 ) {
    		makeSphere(1.0f, 72, 72);
    	}
    	else if (choice == 2 || choice == 5 ){
    		makeSatellite(1.0f, 72, 72);
    		
    	}
    	
    	else if(choice == 3) {
    		makeSphere(1.0f, 72, 72);
    	}
    	
    	else if (choice == 6) {
    		makeCone(1.0f, 20, 20);
    	}
    }

    /**
	 * makeSphere - Create sphere of a given radius, centered at the origin,
	 * using spherical coordinates with separate number of thetha and phi
	 * subdivisions.
	 * 
	 * @param radius
	 *            - Radius of the sphere
	 * @param slides
	 *            - number of subdivisions in the theta direction
	 * @param stacks
	 *            - Number of subdivisions in the phi direction.
	 * 
	 *            Can only use calls to addTriangle
	 */
	public void makeSphere(float radius, int slices, int stacks) {
		if (slices < 3)
			slices = 3;

		if (stacks < 3)
			stacks = 3;

		double phi = Math.PI / stacks;
		double theta = 2 * Math.PI / slices;

		loopingMethod(radius, slices, stacks, phi, theta);
	}

	public void loopingMethod(float radius, int slices, int stacks, double phi,
			double theta) {
		float angle1 = 0;
		float angle2 = 0;
		for (int i = 0; i < slices; i++, angle1 += theta) {
			for (int j = 0; j < stacks; j++, angle2 += phi) {
				//defining coordinates of first point
				float x1 = (float) (radius * Math.cos(angle1) * Math
						.sin(angle2));
				float y1 = (float) (radius * Math.sin(angle1) * Math
						.sin(angle2));
				float z1 = (float) (radius * Math.cos(angle2));
				
				//defining coordinates of second point
				float x2 = (float) (radius * Math.cos(angle1 + theta) * Math
						.sin(angle2));
				float y2 = (float) (radius * Math.sin(angle1 + theta) * Math
						.sin(angle2));
				float z2 = (float) (radius * Math.cos(angle2));
				
				//defining coordinates of third point
				float x3 = (float) (radius * Math.cos(angle1) * Math.sin(angle2
						+ phi));
				float y3 = (float) (radius * Math.sin(angle1) * Math.sin(angle2
						+ phi));
				float z3 = (float) (radius * Math.cos(angle2 + phi));
				//defining coordinates of forth point
				float x4 = (float) (radius * Math.cos(theta + angle1) * Math
						.sin(angle2 + phi));
				float y4 = (float) (radius * Math.sin(theta + angle1) * Math
						.sin(angle2 + phi));
				float z4 = (float) (radius * Math.cos(angle2 + phi));
				
				float u1 = (float) (0.5f + (Math.atan2(z3, x3)/6.28f));
				float v1 = (float) (0.5f - (Math.asin(y3)/3.14f));
				
				float u2 = (float) (0.5f + (Math.atan2(z1, x1)/6.28f));
				float v2 = (float) (0.5f - (Math.asin(y1)/3.14f));
				
				float u3 = (float) (0.5f + (Math.atan2(z4, x4)/6.28f));
				float v3 = (float) (0.5f - (Math.asin(y4)/3.14f));
				
				addTriangle(x3, y3, z3, u1, v1, x1, y1, z1, u2, v2, x4, y4, z4, u3, v3);
				
				u1 = (float) (0.5f + (Math.atan2(z1, x1)/6.28f));
				v1 = (float) (0.5f - (Math.asin(y1)/3.14f));
				
				u2 = (float) (0.5f + (Math.atan2(z2, x2)/6.28f));
				v2 = (float) (0.5f - (Math.asin(y2)/3.14f));
				
				u3 = (float) (0.5f + (Math.atan2(z4, x4)/6.28f));
				v3 = (float) (0.5f - (Math.asin(y4)/3.14f));
				addTriangle(x1, y1, z1, u1, v1, x2, y2, z2, u2, v2, x4, y4, z4, u3, v3);
				
			}
			angle2 = 0;
		}
	}

	/**
	 * makeCylinder - Create polygons for a cylinder with unit height, centered
	 * at the origin, with separate number of radial subdivisions and height
	 * subdivisions.
	 * 
	 * @param radius
	 *            - Radius of the base of the cylinder
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 * 
	 *            Can only use calls to addTriangle()
	 */
	public void makeSatellite(float radius, int radialDivisions,
			int heightDivisions) {
		if (radialDivisions < 3)
			radialDivisions = 3;

		if (heightDivisions < 1)
			heightDivisions = 1;

		if (heightDivisions > 65)
			heightDivisions = 65;

		if (radialDivisions > 65)
			radialDivisions = 65;
	//	System.out.println(radialDivisions + "   " + heightDivisions);

		float theta = (float) (2 * Math.PI / radialDivisions);
		float refy = 0.5f;
		float nxty = 1f / heightDivisions;

		// coordinates for the triangle formation
		float x1 = 0;
		float y1 = 0;
		float z1 = 0;
		float x2 = 0;
		float y2 = 0;
		float z2 = 0;
		float x3 = 0;
		float y3 = 0;
		float z3 = 0;
		for (int rD = 0; rD < radialDivisions; rD++) {
			//defining coordinates of first point
			x1 = 0f;
			y1 = refy;
			z1 = 0f;
			//defining coordinates of second point
			x2 = (float) (radius * Math.cos(theta * rD));
			y2 = y1;
			z2 = -(float) (radius * Math.sin(theta * rD));
			//defining coordinates of third point
			x3 = (float) (radius * Math.cos(theta * (rD + 1)));
			y3 = y1;
			z3 = -(float) (radius * Math.sin(theta * (rD + 1)));

			// tesselation for bottom circle
			addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

			y1 = -y1;

			x2 = (float) (radius * Math.cos(theta * (rD + 1)));
			y2 = y1;
			z2 = -(float) (radius * Math.sin(theta * (rD + 1)));

			x3 = (float) (radius * Math.cos(theta * (rD)));
			y3 = y1;
			z3 = -(float) (radius * Math.sin(theta * (rD)));

			// tesselation for top circle
			addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

			tesselationForCylinder(radius, heightDivisions, theta, refy, nxty,
					rD);
		}
		
	}

	public void tesselationForCylinder(float radius, int heightDivisions,
			float theta, float refy, float nxty, int rD) {
		float x1;
		float y1;
		float z1;
		float x2;
		float y2;
		float z2;
		float x3;
		float y3;
		float z3;
		for (int hD = 0; hD < heightDivisions; hD++) {

			//defining coordinates of first point
			x1 = (float) (radius * Math.cos(theta * rD));
			y1 = -refy + (nxty * hD);
			z1 = -(float) (radius * Math.sin(theta * rD));
			//defining coordinates of second point
			x2 = (float) (radius * Math.cos(theta * (rD + 1)));
			y2 = -refy + (nxty * hD);
			z2 = -(float) (radius * Math.sin(theta * (rD + 1)));
			//defining coordinates of third point
			x3 = (float) (radius * Math.cos(theta * rD));
			y3 = refy - (nxty * (heightDivisions - 1 - hD));
			z3 = -(float) (radius * Math.sin(theta * rD));
			// tesselation for front facing
			addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

			x1 = (float) (radius * Math.cos(theta * (rD + 1)));
			z1 = -(float) (radius * Math.sin(theta * (rD + 1)));

			y2 = y3;
			// tesselation for back facing
			addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

		}
	}
    
	/**
	 * makeCone - Create polygons for a cone with unit height, centered at the
	 * origin, with separate number of radial subdivisions and height
	 * subdivisions.
	 * 
	 * @param radius
	 *            - Radius of the base of the cone
	 * @param radialDivision
	 *            - number of subdivisions on the radial base
	 * @param heightDivisions
	 *            - number of subdivisions along the height
	 * 
	 *            Can only use calls to addTriangle()
	 */
	public void makeCone(float radius, int radialDivisions, int heightDivisions) {
		// System.out.println("ram");
		if (radialDivisions < 3)
			radialDivisions = 3;

		if (heightDivisions < 1)
			heightDivisions = 1;

		if (heightDivisions > 65)
			heightDivisions = 65;

		if (radialDivisions > 65)
			radialDivisions = 65;
	//	System.out.println(radialDivisions + "   " + heightDivisions);
		// coordinates for the triangle formation
		float x1 = 0;
		float y1 = 0;
		float z1 = 0;
		float x2 = 0;
		float y2 = 0;
		float z2 = 0;
		float x3 = 0;
		float y3 = 0;
		float z3 = 0;
		float theta = (float) (2 * Math.PI / radialDivisions);
		float refy = -0.5f;
		float nexty = 1f / heightDivisions;
		for (int rD = 0; rD < radialDivisions; rD++) {
			//defining coordinates of first point
			x1 = 0f;
			y1 = refy;
			z1 = 0f;
			//defining coordinates of second point
			x2 = (float) (radius * Math.cos(theta * rD));
			y2 = y1;
			z2 = -(float) (radius * Math.sin(theta * rD));
			//defining coordinates of third point
			x3 = (float) (radius * Math.cos(theta * (rD + 1)));
			y3 = y1;
			z3 = -(float) (radius * Math.sin(theta * (rD + 1)));

			// tessellation for circle
			addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

			// tessellation for covering
			for (int hD = 0; hD < heightDivisions; hD++) {
				//updating coordinates of first point
				x1 = (float) ((radius - (radius * hD / heightDivisions)) * Math
						.cos(theta * (rD + 1)));
				y1 = refy + (nexty * hD);
				z1 = -(float) ((radius - (radius * hD / heightDivisions)) * Math
						.sin(theta * (rD + 1)));
				//updating coordinates of first point
				x2 = (float) ((radius - (radius * hD / heightDivisions)) * Math
						.cos(theta * rD));
				y2 = refy + (nexty * hD);
				z2 = -(float) ((radius - (radius * hD / heightDivisions)) * Math
						.sin(theta * rD));
				//updating coordinates of first point
				x3 = (float) ((radius - (radius * (hD + 1) / heightDivisions)) * Math
						.cos(theta * rD));
				y3 = refy + (nexty * (hD + 1));
				z3 = -(float) ((radius - (radius * (hD + 1) / heightDivisions)) * Math
						.sin(theta * rD));

				addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);
				//updating coordinates of first point
				x1 = (float) ((radius - (radius * (hD + 1) / heightDivisions)) * Math
						.cos(theta * (rD + 1)));
				y1 = refy + (nexty * (hD + 1));
				z1 = -(float) ((radius - (radius * (hD + 1) / heightDivisions)) * Math
						.sin(theta * (rD + 1)));
				//updating coordinates of first point
				x2 = (float) ((radius - (radius * hD / heightDivisions)) * Math
						.cos(theta * (rD + 1)));
				y2 = refy + (nexty * hD);
				z2 = -(float) ((radius - (radius * hD / heightDivisions)) * Math
						.sin(theta * (rD + 1)));

				addTriangle(x1, y1, z1, 0.0f, 0.0f, x2, y2, z2, 0.0f, 0.0f, x3, y3, z3, 0.0f, 0.0f);

			}

		}

	}
	
	
}
