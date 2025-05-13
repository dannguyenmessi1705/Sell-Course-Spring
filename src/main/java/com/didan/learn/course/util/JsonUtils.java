package com.didan.learn.course.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.TimeZone;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JsonUtils {

  public static final String EXCEPTION_WHEN_PARSING = "Exception when parsing [JSON=%s] to object [Class=%s]";
  private static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
        .setTimeZone(TimeZone.getDefault())
        .registerModule(new JavaTimeModule());
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (Exception e) {
      log.error(String.format(EXCEPTION_WHEN_PARSING, json, clazz.getSimpleName()), e);
      return null;
    }
  }

  public static <T> T fromJson(String json, TypeReference<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (Exception e) {
      log.error(String.format(EXCEPTION_WHEN_PARSING, json, clazz.getType()), e);

      return null;
    }
  }


  public static <T> String toJson(T data) {
    try {
      return objectMapper.writeValueAsString(data);
    } catch (Exception e) {
      return null;
    }
  }
}