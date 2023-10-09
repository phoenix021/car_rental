package com.accelex.sample.exercise.controllers.mapping;

//J representing a DTO object, and Z representing an entity object
public interface Mapper<J,Z> {
    public J toDto(Z entity);
    public Z toEntity(J dto);
}
