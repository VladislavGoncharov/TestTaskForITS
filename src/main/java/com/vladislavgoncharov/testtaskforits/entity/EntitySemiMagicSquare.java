package com.vladislavgoncharov.testtaskforits.entity;//package com.vladislavgoncharov.tetstask.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "test_task_semi_magic_square")
public class EntitySemiMagicSquare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String ORIGINAL_SQUARE;
    private String NEW_SEMI_MAGIC_SQUARE;
    private int LOWEST_COST;
    private String SIZE_SIDE;
    private String DATE_OF_RECORDING;

    public EntitySemiMagicSquare() {

    }

    public EntitySemiMagicSquare(String ORIGINAL_SQUARE, String NEW_SEMI_MAGIC_SQUARE, int LOWEST_COST, String SIZE_SIDE) {
        this.ORIGINAL_SQUARE = ORIGINAL_SQUARE;
        this.NEW_SEMI_MAGIC_SQUARE = NEW_SEMI_MAGIC_SQUARE;
        this.LOWEST_COST = LOWEST_COST;
        this.SIZE_SIDE = SIZE_SIDE;
        this.DATE_OF_RECORDING = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(LocalDateTime.now().atZone(ZoneId.systemDefault()));

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getORIGINAL_SQUARE() {
        return ORIGINAL_SQUARE;
    }

    public void setORIGINAL_SQUARE(String ORIGINAL_SQUARE) {
        this.ORIGINAL_SQUARE = ORIGINAL_SQUARE;
    }

    public String getNEW_SEMI_MAGIC_SQUARE() {
        return NEW_SEMI_MAGIC_SQUARE;
    }

    public void setNEW_SEMI_MAGIC_SQUARE(String NEW_SEMI_MAGIC_SQUARE) {
        this.NEW_SEMI_MAGIC_SQUARE = NEW_SEMI_MAGIC_SQUARE;
    }

    public int getLOWEST_COST() {
        return LOWEST_COST;
    }

    public void setLOWEST_COST(int LOWEST_COST) {
        this.LOWEST_COST = LOWEST_COST;
    }

    public String getSIZE_SIDE() {
        return SIZE_SIDE;
    }

    public void setSIZE_SIDE(String SIZE_SIDE) {
        this.SIZE_SIDE = SIZE_SIDE;
    }

    public String getDATE_OF_RECORDING() {
        return DATE_OF_RECORDING;
    }

    public void setDATE_OF_RECORDING(String DATE_OF_RECORDING) {
        this.DATE_OF_RECORDING = DATE_OF_RECORDING;
    }
}
