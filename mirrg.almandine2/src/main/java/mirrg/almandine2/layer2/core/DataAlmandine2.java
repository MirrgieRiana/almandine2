package mirrg.almandine2.layer2.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import mirrg.almandine2.layer2.entity.Entity;

public class DataAlmandine2
{

	@XStreamOmitField
	private GameAlmandine2 game;

	private ArrayList<Entity<?, ?>> entities;

	private HashSet<Entity<?, ?>> selection;

	public void reset()
	{
		entities = new ArrayList<>();
		selection = new HashSet<>();
	}

	public void cleanEntities()
	{
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isDead()) {
				entities.get(i).disable();
				entities.remove(i);
				i--;
			}
		}
	}

	public void addEntity(Entity<?, ?> entity)
	{
		entities.add(entity);
		entity.enable(game);
	}

	public Stream<Entity<?, ?>> getEntities()
	{
		return entities.stream();
	}

	public Stream<Entity<?, ?>> getSelection()
	{
		return selection.stream();
	}

	@SuppressWarnings("unchecked")
	public <E extends Entity<?, ?>> Stream<E> getEntities(Class<E> clazz)
	{
		return entities.stream()
			.filter(entity -> clazz.isInstance(entity))
			.map(entity -> (E) entity);
	}

	public void onLoad(GameAlmandine2 game)
	{
		this.game = game;
		entities.forEach(entity -> entity.reset());
		entities.forEach(entity -> entity.enable(game));
	}

	public void addSelection(Entity<?, ?> entity)
	{
		selection.add(entity);
	}

	public void removeSelection(Entity<?, ?> entity)
	{
		selection.remove(entity);
	}

	public boolean isSelected(Entity<?, ?> entity)
	{
		return selection.contains(entity);
	}

}
