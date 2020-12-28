package com.plancha.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Request;

public class RequestSerializer extends StdSerializer<Set<Request> > {

	private static final long serialVersionUID = 1L;

	public RequestSerializer() {
		this(null);
	}

	public RequestSerializer(Class<Set<Request>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<Request> requests, JsonGenerator gen, SerializerProvider provider) throws IOException {
		List<Request> ids = new ArrayList<>();
        for (Request item : requests) {
            ids.add(item);
        }
        gen.writeObject(ids);
	}
}