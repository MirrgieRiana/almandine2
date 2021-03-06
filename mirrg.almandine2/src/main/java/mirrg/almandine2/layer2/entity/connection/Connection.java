package mirrg.almandine2.layer2.entity.connection;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import mirrg.almandine2.layer2.entity.Entity;

public abstract class Connection
{

	@XStreamOmitField
	private Entity<?, ?> parent;

	public void enable(Entity<?, ?> parent)
	{
		this.parent = parent;
	}

	public void disable()
	{

	}

	public void fire(Entity<?, ?> owner, Event event)
	{
		parent.onConnectionEvent(owner, event);
	}

	public abstract Point2D.Double getPoint();

	public abstract Stream<Entity<?, ?>> getEntities();

	public Entity<?, ?> getParent()
	{
		return parent;
	}

}
