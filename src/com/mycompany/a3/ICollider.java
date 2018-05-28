package com.mycompany.a3;

//interface that shows the functions collidable objects have
public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject, GameObject currentObject, GameWorld gW, Game game);
}