package org.edu.mimo.ProjetJava.model.request;

import java.util.List;

public record EmployeeWithEnclosuresRequest(
         String name,
         String role,
         List<EnclosureRequest> enclosures
) {
}
