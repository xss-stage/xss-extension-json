package org.stage.xss.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.stage.xss.core.spi.XssFilter;
import org.stage.xss.json.exception.JsonXssFilterException;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JsonXssFilter implements XssFilter{

    private static final String FILTER_NAME = "json";
    private final ObjectMapper objectMapper;

    JsonXssFilter(){
        objectMapper = new ObjectMapper();
        objectMapper.getFactory().setCharacterEscapes(new XssCharacterEscapes());
    }

    @Override
    public String getFilterName(){
        return FILTER_NAME;
    }

    @Override
    public <P> P doFilter(Object dirty, Class<P> cast){
        try{
            return objectMapper.readValue(objectMapper.writeValueAsString(dirty), cast);
        } catch (JsonProcessingException jpe){
            throw new JsonXssFilterException(jpe.getMessage());
        }
    }

}
