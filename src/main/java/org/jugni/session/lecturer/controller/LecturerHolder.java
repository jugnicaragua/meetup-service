package org.jugni.session.lecturer.controller;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jugni.session.lecturer.model.Lecturer;

/**
 *
 * @author aalaniz
 *
 */
@XmlRootElement(name = "lecturers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LecturerHolder {

    @XmlElement(name = "lecturer")
    private Collection<Lecturer> lecturers;

    public LecturerHolder() {
    }

    public LecturerHolder(Collection<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }
}
