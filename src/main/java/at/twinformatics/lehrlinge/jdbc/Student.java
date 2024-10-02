package at.twinformatics.lehrlinge.jdbc;


import at.twinformatics.lehrlinge.jdbc.fw.Model;

import java.time.LocalDate;

public record Student(
    Integer id,
    String firstName,
    String lastName,
    LocalDate dateOfBirth)
    implements Model {
}
