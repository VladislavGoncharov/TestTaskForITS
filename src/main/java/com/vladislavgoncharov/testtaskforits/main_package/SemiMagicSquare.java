package com.vladislavgoncharov.testtaskforits.main_package;

import java.util.*;
import java.util.stream.Collectors;

public class SemiMagicSquare {

    private String originalSemiMagicSquare;

    private Integer[] semiMagicSquare;
    private String newSemiMagicSquare;
    private int lowestCost;

    private int sideSize = 3;
    private int sumSideSize;
    private long countSemiMagicSquare = 0;

    private List<Integer[]> listSemiMagicSquare = new ArrayList<>();
    private Map<Integer, Integer[]> map = new TreeMap<>();

    public SemiMagicSquare() {
    }

    public SemiMagicSquare(StringBuilder originalSemiMagicSquare,
                           StringBuilder semiMagicSquareString,
                           StringBuilder lowestCost,
                           StringBuilder sideSize) {

        this.originalSemiMagicSquare = String.valueOf(originalSemiMagicSquare);
        this.newSemiMagicSquare = String.valueOf(semiMagicSquareString);
        this.lowestCost = Integer.parseInt(String.valueOf(lowestCost));
        this.sideSize = Integer.parseInt(String.valueOf(sideSize));
    }

    public void getResult() throws Exception {
        sumSideSize = (sideSize * ((int) Math.pow(sideSize, 2) + 1)) / 2;
        semiMagicSquare = convertStringToArray();

        generateAllPossibleOptions(semiMagicSquare.length, new LinkedList<>());
        calculateLowestCost();

        for (Map.Entry<Integer, Integer[]> array : map.entrySet()) {
            // Берем самый первый вариант, ведь он наименьший
            lowestCost = array.getKey();
            newSemiMagicSquare = Arrays.stream(array.getValue())
                    .mapToInt(integer -> integer)
                    .mapToObj(integer -> integer + "")
                    .reduce((accum, str) -> accum + "," + str).get();
            break;
        }
    }

    // Генерируем все возможные варианты
    private void generateAllPossibleOptions(int sizeArray, Deque<Integer> listNumbersUsed) {
        if (listNumbersUsed.size() == sizeArray) {
            checkingSidesOfSquare(listNumbersUsed.toArray(new Integer[0]));
            return;
        }
        for (int i = 1; i <= sizeArray; i++) {
            if (!(listNumbersUsed.contains(i))) {
                listNumbersUsed.addLast(i);
                generateAllPossibleOptions(sizeArray, listNumbersUsed);
                listNumbersUsed.removeLast();
            }
        }
    }

    private Integer[] convertStringToArray() throws Exception {
        List<Integer> futureArray = Arrays.stream(originalSemiMagicSquare.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Object[] arrayForChecking = futureArray.stream().sorted().toArray();
        // Проверка на правильность ввода цифр (от 1 по порядку до размера стороны в квадрате)
        if (!((int) arrayForChecking[0] == 1 && (int) arrayForChecking[arrayForChecking.length - 1] == Math.pow(sideSize, 2)))
            throw new Exception("Неверно введенные данные");

        return futureArray.toArray(new Integer[0]);
    }

    // Проверка на полу магический квадрат, в случае удачной проверки добавляем в лист
    private void checkingSidesOfSquare(Integer[] currentArray) {

        for (int i = 0; i < Math.pow(sideSize, 2); i += 3) {

            int sum = 0;
            for (int j = i; j < sideSize + i; j++) {
                sum += currentArray[j];
            }
            if (!(sum == sumSideSize)) {
                return;
            }
        }
        for (int i = 0; i < sideSize; i++) {

            int sum = 0;
            for (int j = 0; j < Math.pow(sideSize, 2); j += 3) {
                sum += currentArray[j];
            }
            if (!(sum == sumSideSize)) {
                return;
            }
        }

        countSemiMagicSquare++;
        listSemiMagicSquare.add(currentArray);
    }

    // Проверка стоимость всех квадратов от исходного
    private void calculateLowestCost() {
        lowestCost = 100;
        for (Integer[] currentArray : listSemiMagicSquare) {
            int sumLowestCost = 0;

            for (int i = 0; i < currentArray.length; i++) {
                sumLowestCost += Math.abs(semiMagicSquare[i] - currentArray[i]);
            }

            if (sumLowestCost < lowestCost) {
                lowestCost = sumLowestCost;
                map.put(sumLowestCost, currentArray);
            }
        }
    }

    // Создаем квадрат на основе данных из файла
    public static SemiMagicSquare createObjectFromFile(List<String> futureObjectFromFile) throws Exception {
        StringBuilder originalSemiMagicSquare = new StringBuilder(),
                         newSemiMagicSquare = new StringBuilder(),
                             lowestCost = new StringBuilder(),
                                  sideSize = new StringBuilder();

        int switchArrays = 0;

        for (String line : futureObjectFromFile) {
            if ("Semi magic square".equals(line.trim()) ||
                    "//----------//".equals(line.trim()) ||
                    "//------------/------------//".equals(line.trim())) {
                switchArrays++;
                continue;
            }

            if (switchArrays == 1) originalSemiMagicSquare.append(line);
            else if (switchArrays == 2) newSemiMagicSquare.append(line);
            else if (switchArrays == 3) lowestCost.append(line);
            else sideSize.append(line.charAt(0));
        }
        if (switchArrays <= 3) throw new Exception("File error");

        return new SemiMagicSquare(originalSemiMagicSquare, newSemiMagicSquare, lowestCost, sideSize);
    }

    public String getOriginalSemiMagicSquare() {
        return originalSemiMagicSquare;
    }

    public void setOriginalSemiMagicSquare(String originalSemiMagicSquare) {
        this.originalSemiMagicSquare = originalSemiMagicSquare;
    }

    public Integer[] getSemiMagicSquare() {
        return semiMagicSquare;
    }

    public void setSemiMagicSquare(Integer[] semiMagicSquare) {
        this.semiMagicSquare = semiMagicSquare;
    }

    public int getSideSize() {
        return sideSize;
    }

    public void setSideSize(int sideSize) {
        this.sideSize = sideSize;
    }

    public int getSumSideSize() {
        return sumSideSize;
    }

    public void setSumSideSize(int sumSideSize) {
        this.sumSideSize = sumSideSize;
    }

    public long getCountSemiMagicSquare() {
        return countSemiMagicSquare;
    }

    public void setCountSemiMagicSquare(long countSemiMagicSquare) {
        this.countSemiMagicSquare = countSemiMagicSquare;
    }

    public List<Integer[]> getListSemiMagicSquare() {
        return listSemiMagicSquare;
    }

    public void setListSemiMagicSquare(List<Integer[]> listSemiMagicSquare) {
        this.listSemiMagicSquare = listSemiMagicSquare;
    }

    public Map<Integer, Integer[]> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer[]> map) {
        this.map = map;
    }

    public String getNewSemiMagicSquare() {
        return newSemiMagicSquare;
    }

    public void setNewSemiMagicSquare(String newSemiMagicSquare) {
        this.newSemiMagicSquare = newSemiMagicSquare;
    }

    public int getLowestCost() {
        return lowestCost;
    }

    public void setLowestCost(int lowestCost) {
        this.lowestCost = lowestCost;
    }
}


