
public class Triangle{
		int width;
		Triangle(int w){
			width = w;
		}
		public int getArea(){
			if(width==1){
				return 1;
			}
			Triangle smallerTriangle = new Triangle(width-1);
			int smallerArea = smallerTriangle.getArea();
			System.out.println("width " + width);
			System.out.println("smallerArea " + smallerArea);
			return smallerArea + width;

		}
	}