package darkx.darkxcore.lib;

import darkx.darkxcore.lib.BlockInfo.Texture;

public class ItemInfo {
	public int id;
	public String name;
	public Texture texture;
	
	public class Texture {
		public String location;
		public int index;
		
		public Texture(String location, int index) {
			this.location = location;
			this.index = index;
		}
	}
	
	public ItemInfo(int id, String name, String location, int index) {
		this.id = id;
		this.name = name;
		this.texture = new Texture(location, index);
	}
	
	public ItemInfo(int id, String name, Texture texture) {
		this.id = id;
		this.name = name;
		this.texture = texture;
	}
}