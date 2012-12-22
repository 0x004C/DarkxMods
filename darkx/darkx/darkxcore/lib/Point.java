package darkx.darkxcore.lib;

import java.util.ArrayList;

public class Point {
	public int x;
	public int y;
	public int z;
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public Object[] getNeightbors() {
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point(this.x, this.y + 1, this.z));
		list.add(new Point(this.x, this.y - 1, this.z));
		list.add(new Point(this.x, this.y, this.z - 1));
		list.add(new Point(this.x, this.y, this.z + 1));
		list.add(new Point(this.x - 1, this.y, this.z));
		list.add(new Point(this.x + 1, this.y, this.z));
		
		return list.toArray();
	}
}
