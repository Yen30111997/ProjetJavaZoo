package org.edu.mimo.ProjetJava.model.request;

public record AnimalRequest(
         String name,
         String species,
         Integer age,
         float height,
         float length,
         float weight
) {
}
