package org.imperativeFiction.core;

import org.imperativeFiction.generated.Boundary;

/**
 * Created by developer on 7/27/15.
 */
public class DirectionBoundary {

	private MovementTypes movementTypes;
	private Boundary boundary;

	public DirectionBoundary(MovementTypes movementTypes, Boundary boundary) {
		this.boundary = boundary;
		this.movementTypes = movementTypes;
	}

	public DirectionBoundary() {
	}

	public Boundary getBoundary() {
		return boundary;
	}

	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	public MovementTypes getMovementTypes() {
		return movementTypes;
	}

	public void setMovementTypes(MovementTypes movementTypes) {
		this.movementTypes = movementTypes;
	}

	@Override
	public String toString() {
		return "DirectionPath{" + "boundary=" + boundary + ", movementTypes=" + movementTypes + '}';
	}
}
