// 천단위 ',' 추가
function addComma(val) {
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}