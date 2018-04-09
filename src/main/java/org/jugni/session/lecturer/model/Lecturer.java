package org.jugni.session.lecturer.model;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author aalaniz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"jugId", "fullName", "gender"})
public class Lecturer {

    public enum Gender {
        MALE, FEMALE
    }

    private String jugId;

    @NotNull(message = "[fullName] is required")
    @Size(min = 5, max = 50)
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚÑáéíóúñ '-]*")
    private String fullName;

    @NotNull(message = "[gender] is required")
    private Gender gender;

    public Lecturer() {
    }

    public Lecturer(String jugNiId, String fullName, Gender gender) {
        this.jugId = jugNiId;
        this.fullName = fullName;
        this.gender = gender;
    }

    public String getJugId() {
        return jugId;
    }

    public void setJugId(String jugId) {
        this.jugId = jugId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void generateId() {
        String[] names = fullName.split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (String name : names) {
            initials.append(name.charAt(0));
        }

        setJugId(initials.toString().toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.jugId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lecturer other = (Lecturer) obj;
        if (!Objects.equals(this.jugId, other.jugId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lecturer{" + "jugNiId=" + jugId + ", fullName=" + fullName + ", gender=" + gender + '}';
    }
}
