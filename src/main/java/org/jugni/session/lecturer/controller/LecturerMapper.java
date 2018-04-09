package org.jugni.session.lecturer.controller;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jugni.session.config.Constants;
import org.jugni.session.lecturer.model.Lecturer;

/**
 *
 * @author aalaniz
 *
 */
public final class LecturerMapper {

    private LecturerMapper() {
    }

    public static String mapToXml(final Lecturer input) {
        Objects.requireNonNull(input, "A [lecturer] object is required");

        StringBuilder plainXml = new StringBuilder("<lecturer>");
        plainXml.append("<jugId>").append(input.getJugId()).append("</jugId>");
        plainXml.append("<fullName>").append(input.getFullName()).append("</fullName>");
        plainXml.append("<gender>").append(input.getGender()).append("</gender>");
        plainXml.append("</lecturer>");

        return plainXml.toString();
    }

    public static String mapToXml(final Collection<Lecturer> lecturers) {
        Objects.requireNonNull(lecturers, "A [lecturers] collection is required");

        return lecturers.stream()
                .filter(l -> l != null)
                .map(LecturerMapper::mapToXml)
                .collect(Collectors.joining("", Constants.Xml.XML_PROLOG + "<lecturers>", "</lecturers>"));
    }
}
