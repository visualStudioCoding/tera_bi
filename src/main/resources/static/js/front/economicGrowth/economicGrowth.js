/******************** 경제성장지표 *********************/
// 데이터 호출 함수
// window.onload = function(){
//     getExchangeRate();
// }

let errorMsg = "데이터 호출 에러";

function getExchangeRate(){
    let current;
    let past;
    let diff;

    let callBackFn = function( data ) {
        console.log(data.current)
        console.log(data.past)
        console.log(data.differ)

        current = data.current
        past = data.past
        diff = data.differ

        $("#currentExChange").text(current)

        // document.getElementById("currentExChange").value = current
        // $("#currentExChange").innerHTML+='<h1>TEST TEST</h1>'
    }

    getApiResult("/front/economicGrowth/api/getExchangeRate",callBackFn, "get", null, errorMsg);

}