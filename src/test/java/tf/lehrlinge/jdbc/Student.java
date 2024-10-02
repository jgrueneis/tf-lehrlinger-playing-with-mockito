package tf.lehrlinge.jdbc;


import tf.lehrlinge.jdbc.fw.Model;

import java.time.LocalDate;

public record Student(
    Integer id,
    String firstName,
    String lastName,
    LocalDate dateOfBirth)
    implements Model {
}
