package com.plancha.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Resource;

public class ResourceSerializer extends StdSerializer<Resource> {

	private static final long serialVersionUID = 1L;

	public ResourceSerializer() {
		this(null);
	}

	public ResourceSerializer(Class<Resource> t) {
		super(t);
	}

	@Override
	public void serialize(Resource resource, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(resource.getQuadri());
	}
}