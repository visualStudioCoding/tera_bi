/* 차트 - 지역별 경제성장률 */
const regionChartDom = document.getElementById("regionGrowthGraph");
const regionChart = echarts.init(regionChartDom);
let regionChartOp;
let colors = ["#393939", "#f5b031", "#fad797", "#59ccf7", "#c3b4df"];

// 데이터 호출 함수
window.onload = function () {
    getEnmcGrrt();
    getCovidEconomicGrowth();

}

// 데이터 배열
let ctyNm = [];
let unit = [];
let rate = [];

let covidGrowth = [];
let covidYear = [];

// 경제성장률 AJAX
function getEnmcGrrt() {

    let callBackFn = function (data) {
        fnRegionChartOp(data);
    }
    getApiResult("/front/economicGrowth/api/getEconomicGrowth", callBackFn, "get", null, errorMsg);
}

// 코로나 시기 경제성장률 AJAX
function getCovidEconomicGrowth() {

    let callBackFn = function (data) {
        fnCovidChartOp(data);
    }
    getApiResult("/front/economicGrowth/api/getCovidEconomicGrowth", callBackFn, "get", null, errorMsg);
}

// 1인당 국민 총 소득 AJAX
function getCovidEconomicGrowth() {

    let callBackFn = function (data) {
        fnCovidChartOp(data);
    }
    getApiResult("/front/economicGrowth/api/getStateDebt", callBackFn, "get", null, errorMsg);
}

// 국가 채무 현황 AJAX
function getCovidEconomicGrowth() {

    let callBackFn = function (data) {
        fnCovidChartOp(data);
    }
    getApiResult("/front/economicGrowth/api/getCovidEconomicGrowth", callBackFn, "get", null, errorMsg);
}

// 경제성장률 배열 데이터 추가 및 차트 선언
function fnRegionChartOp(data) {

    for (var i = 0; i < data.emncGrrt.length; i++) {
        ctyNm.push(data.emncGrrt[i].cty_nm);
        unit.push(data.emncGrrt[i].unit);
        rate.push(data.emncGrrt[i].val);
    }

    for (var i = 0; i < ctyNm.length; i++) {
        if (ctyNm[i] == '전국') {
            $("#wholeRegion").text(rate[i] + "%")
        }
    }

    regionChartOp = {
        dataset: {
            source: [
                ["성장률", "지역"]
            ],
        },
        grid: {containLabel: true, x: 0, y: 0, y2: 0},
        xAxis: {
            name: "성장률",
            axisLabel: {
                margin: 20,
                color: "#292b2c",
            },
        },
        yAxis: {
            type: "category",
            axisLabel: {
                color: "#292b2c",
            },
        },
        series: [
            {
                type: "bar",
                encode: {
                    // Map the "amount" column to X axis.
                    x: "성장률",
                    // Map the "product" column to Y axis
                    y: "지역",
                },
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: "right",
                            color: "#7a8489",
                        },
                        color: "#1e70e7",
                    },
                },
            },
        ],
        textStyle: {
            fontFamily: "NanumSquare",
        },
    };
    console.log(ctyNm)
    for (let i = 0; i < ctyNm.length; i++) {
        if(ctyNm[i] == "전국"){
            continue;
        }
        regionChartOp.dataset.source.push([rate[i], ctyNm[i]]);
    }

    regionChart.setOption(regionChartOp);
}

/* 차트 - 코로나 시기 성장률 */
const covidChartDom = document.getElementById("covidGrowthGraph");
const covidChart = echarts.init(covidChartDom);
let covidChartOp;

function fnCovidChartOp(data){

    for (var i = 0; i < data.covidGrowth.length; i++) {
        covidGrowth.push(data.covidGrowth[i].val);
        covidYear.push(data.covidGrowth[i].yr_dt);
    }

    console.log(covidYear)
    console.log(covidGrowth)

    covidChartOp = {
        grid: {
            containLabel: true,
            left: 0,
            top: 10,
            right: 0,
            bottom: 0,
        },
        label: {
            show: true,
        },
        xAxis: {
            type: "category",
            data: [],
        },
        yAxis: {
            type: "value",
            axisLabel: {
                formatter: "{value}%",
            },
        },
        series: [
            {
                data: [],
                type: "line",
            },
        ],
        textStyle: {
            fontFamily: "NanumSquare",
        },
    };

    covidChartOp.xAxis.data = covidYear;
    covidChartOp.series[0].data = covidGrowth

    covidChart.setOption(covidChartOp);
}

/* 차트 - 물가 상승 추이 */
const inflChartDom = document.getElementById("inflationGraph");
const inflChart = echarts.init(inflChartDom);
let inflChartOp;

$.get("../js/inflChartTemp.json", function (_rawData) {
    run(_rawData);
});
function run(_rawData) {
    const category = ["소비", "근원", "생활"];
    const datasetWithFilters = [];
    const seriesList = [];
    echarts.util.each(category, function (ctg) {
        var datasetId = "dataset_" + ctg;
        datasetWithFilters.push({
            id: datasetId,
            fromDatasetId: "dataset_raw",
            transform: {
                type: "filter",
                config: {
                    and: [
                        { dimension: "Year", gte: 2018 },
                        { dimension: "Category", "=": ctg },
                    ],
                },
            },
        });
        seriesList.push({
            type: "line",
            datasetId: datasetId,
            showSymbol: true,
            name: ctg,
            endLabel: {
                show: true,
                formatter: function (params) {
                    return params.value[1] + ": " + params.value[0] + "%";
                },
            },
            labelLayout: {
                moveOverlap: "shiftY",
            },
            emphasis: {
                focus: "series",
            },
            encode: {
                x: "Year",
                y: "inflPer",
                label: ["Category", "inflPer"],
                itemName: "Year",
                tooltip: "inflPer",
            },
        });
    });

    inflChartOp = {
        animationDuration: 2000,
        dataset: [
            {
                id: "dataset_raw",
                source: _rawData,
            },
            ...datasetWithFilters,
        ],
        tooltip: {
            order: "valueDesc",
            trigger: "axis",
        },
        xAxis: {
            type: "category",
            nameLocation: "middle",
        },
        yAxis: {
            name: "물가상승률(%)",
        },
        grid: {
            containLabel: true,
            top: 30,
            left: 25,
            right: 20,
            bottom: 0,
        },
        textStyle: {
            fontFamily: "NanumSquare",
        },
        series: seriesList,
    };
    inflChart.setOption(inflChartOp);
}

/* 차트 - GDP 대비 국가채무 */
const gdpDeptChartDom = document.getElementById("gdpDeptGraph");
const gdpDeptChart = echarts.init(gdpDeptChartDom);
let gdpDeptGraphOp;

gdpDeptGraphOp = {
    grid: { containLabel: true, left: 10, right: 40, top: 60, bottom: 0 },
    tooltip: {
        trigger: "axis",
        axisPointer: {
            type: "cross",
            crossStyle: {
                color: "#999",
            },
        },
    },
    toolbox: {
        feature: {
            dataView: { show: false, readOnly: false },
            magicType: { show: false, type: ["line", "bar"] },
            restore: { show: false },
            saveAsImage: { show: false },
        },
    },
    legend: {
        data: ["GDP(명목)", "GDP(실질)", "국가채무"],
        padding: 0,
    },
    xAxis: [
        {
            type: "category",
            data: ["2017", "2018", "2019", "2020", "2021"],
            axisPointer: {
                type: "shadow",
            },
        },
    ],
    yAxis: [
        {
            type: "value",
            name: "GDP(조원)",
        },
        {
            type: "value",
            name: "국가채무(조원)",
        },
    ],
    series: [
        {
            name: "국가채무",
            type: "bar",
            tooltip: {
                valueFormatter: function (value) {
                    return value + "조";
                },
            },
            data: [660.2, 680.5, 723.2, 846.6, 965.3],
        },
        {
            name: "GDP(명목)",
            type: "line",
            tooltip: {
                valueFormatter: function (value) {
                    return value + "조";
                },
            },
            data: [1835, 1898, 1924, 1940, 2071],
        },
        {
            name: "GDP(실질)",
            type: "line",
            tooltip: {
                valueFormatter: function (value) {
                    return value + "조";
                },
            },
            data: [1760, 1812, 1852, 1839, 1915],
        },
    ],
    textStyle: {
        fontFamily: "NanumSquare",
    },
};
gdpDeptChart.setOption(gdpDeptGraphOp);
