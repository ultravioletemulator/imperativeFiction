package org.imperativeFiction.core;

import org.imperativeFiction.generated.Action;
import org.imperativeFiction.generated.Movements;
import org.imperativeFiction.generated.ObjectType;

import java.util.List;

/**
 * Created by developer on 7/22/15.
 */
public class GameAction {

	private Action action;
	private List<String> parameters;

	//	private java.util.List<org.imperativeFiction.generated.ObjectType> objects;
	//	private Movements movement;
	public GameAction() {
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "GameAction{" + "action=" + action + ", parameters=" + parameters + '}';
	}
	//
	//	public List<ObjectType> getObjects() {
	//		return objects;
	//	}
	//
	//	public void setObjects(List<ObjectType> objects) {
	//		this.objects = objects;
	//	}
	//
	//	public Movements getMovement() {
	//		return movement;
	//	}
	//
	//	public void setMovement(Movements movement) {
	//		this.movement = movement;
	//	}
	//
	//	@Override
	//	public String toString() {
	//		return "GameAction{" + "action=" + action + ", objects=" + objects + ", movement=" + movement + '}';
	//	}
}
