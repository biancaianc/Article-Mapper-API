package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class StringUtils {

    public static String readFileAsString(String file)throws Exception
    {

        return (new String(Files.readAllBytes(Paths.get(file))).replaceAll(" ","").replaceAll("\n",""));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
