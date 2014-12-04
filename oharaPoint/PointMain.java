package oharaPoint;

public class PointMain {
	
   public static void main(String[] args) {
      ReddingPoint p3 = new ReddingPoint();
      System.out.println(p3);
      
      // create 2 points
      ReddingPoint p1 = new ReddingPoint(7, 2);
      ReddingPoint p2 = new ReddingPoint(4, 3);
      
      System.out.println("p1 is (" + p1.getX() + ","
         + p1.getY() + ")");
      System.out.println("p2 is (" + p2.getX() + ","
         + p2.getY() + ")");
         
      p1.setX(5);
      
      p1.translate(11, 6);
      
      System.out.println(p1.distanceFromOrigin());
               
      System.out.println(p1);
      System.out.println(p2);
      
   }
   
}