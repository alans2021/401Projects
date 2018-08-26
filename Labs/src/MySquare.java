
public class MySquare extends MyRectangle{
	
	public MySquare(){
	}
	
	public MySquare(int x, int y, int side){
		startX = x;
		startY = y;
		width = side;
		height = side;
	}
	
	public String toString(){
		StringBuilder S = new StringBuilder();
		S.append("Side: " + width);
		S.append(" X: " + startX);
		S.append(" Y: " + startY);
		return S.toString();
	}
	
	public void setSize(int w, int h){
		if(w != h){
			System.out.println("Sides must be equal. " + w + " != " + h + " so no action taken");
		}
		else{
			width = w;
			height = h;
		}
	}
	
	public void setSide(int size){
		width = size;
		height = size;
	}

}
