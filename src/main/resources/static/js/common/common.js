/******************** header *********************/
/* 인디케이터 애니메이션 */
const indicator = document.querySelector(".indicator");
const navLis = document.querySelectorAll(".nav li");
let activedNav = document.querySelector(".nav-link.on");

indicator.style.transform = "translateX(" + activedNav.offsetLeft + "px)";

navLis.forEach((li, idx) => {
    let navLiWidth = li.clientWidth;

    li.addEventListener("mouseover", function () {
        indicator.style.transform = "translateX(" + navLiWidth * idx + "px)";
    });
    li.addEventListener("mouseleave", function () {
        indicator.style.transform = "translateX(" + activedNav.offsetLeft + "px)";
    });
});
window.onload = function () {
    /******************** 본문 공통 *********************/
    /* 초기 화면 진입 시, 숫자 카운트 애니메이션 (0 이상의 정수) */
    /* 적용하고 싶은 텍스트에 'count-ani' 클래스 추가하면 됨 */
    const count = document.querySelectorAll(".count-ani");

    count.forEach((cnt) => {
        let endNum = parseFloat(cnt.textContent.replace(",", ""));
        let now = endNum;

        let counter = setInterval(() => {
            cnt.textContent = Math.ceil(endNum - now).toLocaleString("ko-KR");

            // 카운트 증가 애니메이션
            if (now < 1) {
                clearInterval(counter);
            }
            const step = now / 10;
            now -= step;
        }, 30);
    });
    /* 초기 화면 진입 시, 숫자 카운트 애니메이션 (소수점 2자리 실수 + %) */
    /* 적용하고 싶은 텍스트에 'count-ani-per' 클래스 추가하면 됨 */
    const countFloat = document.querySelectorAll(".count-ani-per");

    countFloat.forEach((cnt) => {
        let endNum = parseFloat(cnt.textContent.replace(",", ""));
        let now = endNum;

        let counter = setInterval(() => {
            cnt.textContent = (Math.ceil((endNum - now) * 100) / 100 + 1).toFixed(2); // 데이터 오차 없애기 위해 +1 처리

            // 카운트 증가 애니메이션
            if (now < 1) {
                clearInterval(counter);
            }
            const step = now / 100 - 0.01; // 데이터 오차 없애기 위해 -0.01 처리
            now -= step;
        }, 1);
    });
}
/******************** 공통 *********************/
/* 기간 선택 */
$("input[name='term']").click(function (e) {
    if (e.target.id !== "termDatePick") {
        term.attr("disabled", true);
    } else if (e.target.id === "termDatePick") {
        term.attr("disabled", false);
    }
});

/* date range picker */
const term = $("input[name='termDatePicker']");
term.daterangepicker({
    locale: {
        format: "YYYY-MM-DD",
        daysOfWeek: ["월", "화", "수", "목", "금", "토", "일"],
        monthNames: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
        ],
        applyLabel: "확인",
        cancelLabel: "취소",
    },
    showDropdowns: true, // 년월 수동 설정 여부
});
/* 지역 선택 */
const loc = $("input[name='ecLoc']");
const locSlct = $(".economic-growth .box .select-group select");
loc.click(function (e) {
    if (e.target.id === "ecLocAll") {
        locSlct.attr("disabled", true);
    } else if (e.target.id === "ecLocSlcts") {
        locSlct.attr("disabled", false);
    }
});
