const input = document.getElementById("input_semi_magic_square");

let sizeInput = input.value.length;

// Работа в самом инпуте
input.addEventListener("input", function () {

    let isDelete = sizeInput > input.value.length;
    let lastChar = input.value.substring(input.value.length - 1);

    // Фильтрация на только на цифры
    if (!parseInt(lastChar) && !isDelete) {
        input.value = input.value.substring(0, input.value.length - 1);
        sizeInput = input.value.length;
    }// Чтобы после последней цифры не ставилась запятая
    else if (sizeInput === 16 && !isDelete) {
        sizeInput = input.value.length;
    } // При удалении последней цифры не удалялась последняя запятая
    else if (sizeInput === 17 && lastChar === ',' && isDelete) {
        sizeInput = input.value.length;
    } // Ограничение по вводу символов (не более 17)
    else if (sizeInput === 17 && lastChar !== ',') {
        input.value = input.value.substring(0, input.value.length - 1);
        sizeInput = input.value.length;
    } else {
        // При удалении запятой, удаляется еще цифра
        if (lastChar !== ',' && isDelete) {
            input.value = input.value.substring(0, input.value.length - 1);
            sizeInput = input.value.length;
        } // При добавлении цифры, добавляется запятая
        else if (lastChar !== ',' && !isDelete) {
            input.value = input.value + ',';
            sizeInput = input.value.length;
        }
    }
})

let arrayCell = [
    document.getElementById("cell_№1"),
    document.getElementById("cell_№2"),
    document.getElementById("cell_№3"),
    document.getElementById("cell_№4"),
    document.getElementById("cell_№5"),
    document.getElementById("cell_№6"),
    document.getElementById("cell_№7"),
    document.getElementById("cell_№8"),
    document.getElementById("cell_№9"),
]
let sizeInput2 = input.value.length;
////////////////////////////////////////////////////////////////////////
// Работа с визуальным квадратом
input.addEventListener("input", function () {

    let numberCurrentCell = (input.value.length / 2) - 1;
    let isDelete = sizeInput2 > input.value.length;
    let currentValue = input.value.substring(input.value.length - 2, input.value.length - 1);

    // Добавление последней цифры в квадрат
    if (input.value.length === 17 && !isDelete) {
        arrayCell[8].innerText = input.value.substring(input.value.length - 1);
        sizeInput2 = input.value.length;
    }// Удаление последней цифры из квадрата
    else if (sizeInput2 === 17 && isDelete) {
        arrayCell[8].innerText = "";
        sizeInput2 = input.value.length;
    }// Добавление цифр в квадрат
    else if (!isDelete) {
        arrayCell[numberCurrentCell].innerText = currentValue;
        sizeInput2 = input.value.length;
    }// Удаление цифр из квадрата
    else {
        arrayCell[numberCurrentCell + 1].innerText = "";
        sizeInput2 = input.value.length;
    }
})
////////////////////////////////////////////////////////////////////////

