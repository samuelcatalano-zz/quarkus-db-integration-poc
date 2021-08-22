package co.uk.olm.group.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    private static Mapper instance;
    private final ObjectMapper objectMapper;

    private Mapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.findAndRegisterModules();
    }

    public static Mapper getInstance() {
        if (instance == null) {
            synchronized (Mapper.class) {
                if (instance == null) {
                    instance = new Mapper();
                }
            }
        }
        return instance;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
