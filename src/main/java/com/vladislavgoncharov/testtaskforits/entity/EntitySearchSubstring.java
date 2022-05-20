package com.vladislavgoncharov.testtaskforits.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "test_task_search_substring")
public class EntitySearchSubstring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String FIRST_ARRAY;
    private String SECOND_ARRAY;
    private String RESULT;
    private String DATE_OF_RECORDING;

    public EntitySearchSubstring() {
    }

    public EntitySearchSubstring(String FIRST_ARRAY, String SECOND_ARRAY, String RESULT) {
        this.FIRST_ARRAY = FIRST_ARRAY;
        this.SECOND_ARRAY = SECOND_ARRAY;
        this.RESULT = RESULT;
        this.DATE_OF_RECORDING = DateTimeFormatter
                .ofPattern("dd.MM.yyyy HH:mm")
                .format(LocalDateTime.now().atZone(ZoneId.systemDefault()));
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFIRST_ARRAY() {
        return FIRST_ARRAY;
    }

    public void setFIRST_ARRAY(String FIRST_ARRAY) {
        this.FIRST_ARRAY = FIRST_ARRAY;
    }

    public String getSECOND_ARRAY() {
        return SECOND_ARRAY;
    }

    public void setSECOND_ARRAY(String SECOND_ARRAY) {
        this.SECOND_ARRAY = SECOND_ARRAY;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getDATE_OF_RECORDING() {
        return DATE_OF_RECORDING;
    }

    public void setDATE_OF_RECORDING(String DATE_OF_RECORDING) {
        this.DATE_OF_RECORDING = DATE_OF_RECORDING;
    }
}
