//package cz.rb.bccustomer.domain.repository.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Converter
//public class LongListConverter implements AttributeConverter<List<Long>, String> {
//
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public String convertToDatabaseColumn(List<Long> attribute) {
//        try {
//            // Převod na JSON řetězec
//            return objectMapper.writeValueAsString(attribute);
//        } catch (JsonProcessingException e) {
//            throw new IllegalArgumentException("Error converting list to JSON", e);
//        }
//    }
//
//    @Override
//    public List<Long> convertToEntityAttribute(String dbData) {
//        try {
//            // Převod z JSON zpět na List<Long>
//            return objectMapper.readValue(dbData, new TypeReference<List<Long>>() {});
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Error converting JSON to list", e);
//        }
//    }
//}
