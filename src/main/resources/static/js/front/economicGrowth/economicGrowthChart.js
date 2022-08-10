/* 차트 - 지역별 경제성장률 */
function fnRegionChartOp(data) {

    const regionChartDom = document.getElementById("regionGrowthGraph");
    const regionChart = echarts.init(regionChartDom);
    let regionChartOp;
    let colors = ["#393939", "#f5b031", "#fad797", "#59ccf7", "#c3b4df"];

    console.log(data)
    let dataList = [];
    let ctyNm = [];
    let rate = [];
    let unit = [];

    for (var i = 0; i < data.emncGrrt.length; i++) {
        ctyNm.push(data.emncGrrt[i].cty_nm);
        unit.push(data.emncGrrt[i].unit);
        rate.push(data.emncGrrt[i].val);
    }

    for (let i = 0; i < ctyNm.length; i++) {
        if(i === 0){
            dataList.push(["성장률", "지역"]);
        }
        if(ctyNm[i] === '전국'){
            continue;
        }
        dataList.push([rate[i], ctyNm[i]]);
    }

    console.log(dataList)

    for (var i = 0; i < ctyNm.length; i++) {
        if (ctyNm[i] === '전국') {
            $("#wholeRegion").text(rate[i] + "%")
        }
    }

    regionChartOp = {
        dataset: {
            source: [
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
    for (let i = 0; i < ctyNm.length; i++) {
        if(ctyNm[i] === "전국"){
            continue;
        }
        regionChartOp.dataset.source = dataList;
    }

    regionChart.setOption(regionChartOp);
}

/* 차트 - 코로나 시기 성장률 */
const covidChartDom = document.getElementById("covidGrowthGraph");
const covidChart = echarts.init(covidChartDom);
let covidChartOp;

function fnCovidChartOp(data){

    let covidGrowth = [];
    let covidYear = [];

    for (var i = 0; i < data.covidGrowth.length; i++) {
        covidGrowth.push(data.covidGrowth[i].val);
        covidYear.push(data.covidGrowth[i].yr_dt);
    }

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
function run(_rawData) {

const inflChartDom = document.getElementById("inflationGraph");
const inflChart = echarts.init(inflChartDom);
let inflChartOp;

    console.log(_rawData);
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
                        { dimension: "Year", gte: 1980 },
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
            right: 45,
            bottom: 0,
        },
        textStyle: {
            fontFamily: "NanumSquare",
        },
        series: seriesList,
    };
    inflChart.setOption(inflChartOp);
}

function fngdpDeptGraphOp(data) {
/* 차트 - GDP 대비 국가채무 */
const gdpDeptChartDom = document.getElementById("gdpDeptGraph");
const gdpDeptChart = echarts.init(gdpDeptChartDom);
let gdpDeptGraphOp;

    let period = [];
    let realGDP = [];
    let nmnlGDP = [];
    let debt = [];
    let unit = null;

    for (var i = 0; i < data.gdp.length; i++) {
        period.push(data.gdp[i].yr_dt);
        realGDP.push(data.gdp[i].real_val * 0.001);
        nmnlGDP.push(data.gdp[i].nmnl_val * 0.001);
        unit = "조원";
    }

    for (var i = 0; i < data.debt.length; i++) {
        debt.push(data.debt[i].val);
    }

    gdpDeptGraphOp = {
        grid: {containLabel: true, left: 10, right: 40, top: 60, bottom: 0},
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
                dataView: {show: false, readOnly: false},
                magicType: {show: false, type: ["line", "bar"]},
                restore: {show: false},
                saveAsImage: {show: false},
            },
        },
        legend: {
            data: ["GDP(명목)", "GDP(실질)", "국가채무"],
            padding: 0,
        },
        xAxis: [
            {
                type: "category",
                data: [],
                axisPointer: {
                    type: "shadow",
                },
            },
        ],
        yAxis: [
            {
                type: "value",
                name: "GDP(" + unit + ")",
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
                data: [],
            },
            {
                name: "GDP(명목)",
                type: "line",
                tooltip: {
                    valueFormatter: function (value) {
                        return value + unit;
                    },
                },
                data: [],
            },
            {
                name: "GDP(실질)",
                type: "line",
                tooltip: {
                    valueFormatter: function (value) {
                        return value + unit;
                    },
                },
                data: [],
            },
        ],
        textStyle: {
            fontFamily: "NanumSquare",
        },
    };

    gdpDeptGraphOp.xAxis[0].data = period
    gdpDeptGraphOp.series[0].data = debt
    gdpDeptGraphOp.series[1].data = nmnlGDP
    gdpDeptGraphOp.series[2].data = realGDP


    gdpDeptChart.setOption(gdpDeptGraphOp);
}