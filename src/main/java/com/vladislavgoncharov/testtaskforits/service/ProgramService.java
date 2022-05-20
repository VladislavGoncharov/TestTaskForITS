package com.vladislavgoncharov.testtaskforits.service;

import java.util.List;

public interface ProgramService {


    void saveSearchSubstring(String firstArrays, String secondArrays, String result);

    void saveSemiMagicSquare(String originalSquare,String newSemiMagicSquare,int lowestCost,String size);

    List<?> getAllEntityObject(Class<?> entityClass);
}
