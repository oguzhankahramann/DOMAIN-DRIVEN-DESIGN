package demo.bookstores.logic.common;


import org.bson.types.ObjectId;

public class Entity {

	private ObjectId id;

	public Entity(ObjectId id) {
		
		this.id = id;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
