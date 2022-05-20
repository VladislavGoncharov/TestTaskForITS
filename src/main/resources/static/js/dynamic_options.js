const task_selection = document.getElementById('task_selection');
const formIsSemiMagicSquare = document.getElementById('formIsSemiMagicSquare');
const formIsSearchSubstring = document.getElementById('formIsSearchSubstring');

function changeStyle (){
    let optionValue = task_selection.value;

    if (optionValue==="Полу магический квадрат"){
        formIsSearchSubstring.style.display = 'none';
        formIsSemiMagicSquare.style.display = 'block';
    }
    if (optionValue==="Поиск подстрок"){
        formIsSearchSubstring.style.display = 'block';
        formIsSemiMagicSquare.style.display = 'none';
    }
}

task_selection.addEventListener("change", changeStyle);
////////////////////////////////////////////////////////////
function db_switch_search_substring (){
    const db_search_substring = document.getElementById('database_is_search_substring');
    db_search_substring.style.display = db_search_substring.style.display === "block" ? "none" : "block";
}
function db_switch_semi_magic_square (){
    const db_semi_magic_square = document.getElementById('database_is_semi_magic_square');
    db_semi_magic_square.style.display = db_semi_magic_square.style.display === "block" ? "none" : "block";
}
////////////////////////////////////////////////////////////
const file_input_1 = document.getElementById('file_input_1');
const file_chosen_1 = document.getElementById('file_chosen_1');
file_input_1.addEventListener('change', function(){
    file_chosen_1.textContent = this.files[0].name
})
const file_input_2 = document.getElementById('file_input_2');
const file_chosen_2 = document.getElementById('file_chosen_2');
file_input_2.addEventListener('change', function(){
    file_chosen_2.textContent = this.files[0].name
})
