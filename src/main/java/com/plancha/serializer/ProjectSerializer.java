package com.plancha.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Project;

public class ProjectSerializer extends StdSerializer<Project> {

	public ProjectSerializer() {
		this(null);
	}

	public ProjectSerializer(Class<Project> t) {
		super(t);
	}

	@Override
	public void serialize(Project project, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(project.getName());
		
	}
}