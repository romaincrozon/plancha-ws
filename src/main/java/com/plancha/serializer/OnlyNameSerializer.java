package com.plancha.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Entity;
import com.plancha.dto.entity.Project;
import com.plancha.dto.entity.Request;

public class OnlyNameSerializer extends StdSerializer<Entity> {

	private static final long serialVersionUID = 1L;

	public OnlyNameSerializer() {
		this(null);
	}

	public OnlyNameSerializer(Class<Entity> t) {
		super(t);
	}

	@Override
	public void serialize(Entity entity, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(entity.getName());
	}
}