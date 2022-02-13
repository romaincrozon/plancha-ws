package com.plancha.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.plancha.dto.entity.Competence;
import com.plancha.dto.entity.Project;

public class CompetenceSerializer extends StdSerializer<List> {

	public CompetenceSerializer() {
		this(null);
	}

	public CompetenceSerializer(Class<List> t) {
		super(t);
	}

	@Override
	public void serialize(List competences, JsonGenerator gen, SerializerProvider provider) throws IOException {
		List<String> competenceNames = new ArrayList();
        for (Object obj : competences) {
        	Competence competence = (Competence)obj;
            competenceNames.add(competence.getName());
        }
        gen.writeObject(competenceNames);
	}

}