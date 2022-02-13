package com.plancha.serializer;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Entity;

public class ListOnlyNameSerializer extends StdSerializer<Set<Entity>> {

	private static final long serialVersionUID = 1L;

	public ListOnlyNameSerializer() {
		this(null);
	}

	public ListOnlyNameSerializer(Class<Set<Entity>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<Entity> entitiesList, JsonGenerator gen, SerializerProvider provider) throws IOException {
		List<String> names = entitiesList.stream().map(entity -> entity.getName()).collect(Collectors.toList());
		gen.writeObject(String.join(", ", names));
	}
}