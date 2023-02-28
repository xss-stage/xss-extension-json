package org.stage.xss.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JsonXssFilter.class)
class JsonXssFilterTest{

    private static final String INVALID = read("./src/test/resources/xss-invalid.html");

    @Autowired
    private JsonXssFilter jsonXssFilter;

    private static String read(String filePath){
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();
        try{
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException ioe){
            throw new IllegalStateException("Cannot read file from path \"" + filePath + "\"");
        }
        return sb.toString();
    }

    /*
        Json 객체에 Xss Filtering 되어야할 텍스트가 들어간다면?
        -> Filtering 되어야함.
     */
    @Test
    @DisplayName("Json Xss Filtering 테스트")
    void JSON_XSS_FILTERING_TEST(){
        // given
        String expected = "&lt;SCRIPT&gt;alert(&quot;테스트!!!&quot;);&lt;/SCRIPT&gt;";
        FilterObject request = new FilterObject(INVALID, INVALID);

        // when
        FilterObject result = jsonXssFilter.doFilter(request, FilterObject.class);

        // then
        assertEquals(expected, result.getHello());
        assertEquals(expected, result.getWorld());
    }

    /*
        객체가 중첩되어 있다면 어떻게 될까? -> Filtering 되어야함.
        json의 key가 filtering 대상이라면? -> Filtering 되어야함.
     */
    @Test
    @DisplayName("Json Xss Filtering 테스트 - 중첩 객체")
    void JSON_XSS_FILTERING_OVERLAPPED_TEST() throws Throwable{
        // given
        String expected = "&lt;SCRIPT&gt;alert(&quot;테스트!!!&quot;);&lt;/SCRIPT&gt;";
        FilterObjectOverlapped request = new FilterObjectOverlapped(INVALID);

        // when
        FilterObjectOverlapped result = jsonXssFilter.doFilter(request, FilterObjectOverlapped.class);
        ObjectMapper objectMapper = new ObjectMapper();

        // then
        assertEquals(expected, result.getFilterObject().getHello());
        assertEquals(expected, result.getFilterObject().getWorld());
        result.getFilterObjectList().forEach(
                r -> {
                    assertEquals(expected, r.getWorld());
                    assertEquals(expected, r.getHello());
                }
        );
        for(Map.Entry<String, FilterObject> entry : result.getFilterObjectMap().entrySet()){
            assertEquals(expected, entry.getKey());
            assertEquals(expected, entry.getValue().getHello());
            assertEquals(expected, entry.getValue().getHello());
        }
    }

    /*
        Filtering 하지 않아도 되는 대상이 들어온 경우
     */
    @Test
    @DisplayName("Json Xss Filtering - non filtering")
    void JSON_XSS_FILTERING_DOES_NOT_NEED_FILTERING_TEST(){
        // given
        String expected = "hello world";
        FilterObject request = new FilterObject(expected, expected);

        // when
        FilterObject result = jsonXssFilter.doFilter(request, FilterObject.class);

        // then
        assertEquals(expected, result.getHello());
        assertEquals(expected, result.getWorld());
    }

}
