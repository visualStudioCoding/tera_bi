/* 차트 - 지역별 경제성장률 */
function fnRegionChartOp(data) {

    const regionChartDom = document.getElementById("regionGrowthGraph");
    const regionChart = echarts.init(regionChartDom);
    let regionChartOp;

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
        color: [
          "#1e70e7",
          "#3ba272",
          "#fac858",
          "#ee6666",
          "#5aa7de",
          "#fc8452",
          "#7780e4",
          "#eb9ac9",
          "#e6838b",
          "#dd8d54",
          "#e0d666",
          "#b2e26e",
          "#007a70",
          "#00b3a4",
          "#7a8489",
          "#bfc5c9",
          "#294700",
        ],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        dataset: {
            source: [],
        },
        grid: {
          containLabel: true,
          top: 0,
          bottom: 8,
          left: "-3%",
          right: 10,
        },
        xAxis: {
            name: "성장률",
            type: "value",
            position: "bottom",
            axisLabel: {
              formatter: "{value}%",
            },
            offset: 8
        },
        yAxis: {
            type: "category",
            inverse: true,
            axisLabel: {
              interval: 1,
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
                label: {
                  show: true,
                  position: "outside",
                  textStyle: {
                    fontSize: 10,
                  },
                },
                barWidth: 10,
                colorBy: "data",
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
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
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
                symbol: "circle",
                symbolSize: 10,
                lineStyle: {
                  color: "#1e70e7",
                  width: 4,
                  type: "solid",
                  shadowColor: "rgba(0,0,0,0.3)",
                  shadowBlur: 8,
                  shadowOffsetY: 4,
                  cap: "round",
                },
                itemStyle: {
                  borderWidth: 3,
                  borderColor: "#fff",
                  color: "#1e70e7",
                  shadowColor: "rgba(0, 0, 0, 0.2)",
                  shadowBlur: 4,
                },
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
let colors = [
  "#1e70e7",
  "#3ba272",
  "#fac858",
];

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
            name: ctg,
            datasetId: datasetId,
            type: "line",
            symbol: "circle",
            symbolSize: 8,
            showSymbol: false, // hover 할때만 symbol 표시
            endLabel: {
                show: true,
                align: "right",
                verticalAlign: "top",
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
            smooth: true,
        });
    });

    // 각 series 스타일 설정
    seriesList[0].areaStyle = {
      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        {
          offset: 0,
          color: "rgba(30,112,231,1)",
        },
        {
          offset: 1,
          color: "rgba(255,255,255,0.5)",
        },
      ]),
    };
    seriesList[1].areaStyle = {
      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        {
          offset: 0,
          color: "rgba(59,162,114,1)",
        },
        {
          offset: 1,
          color: "rgba(255,255,255,0.5)",
        },
      ]),
    };
    seriesList[2].areaStyle = {
      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        {
          offset: 0,
          color: "rgba(250,200,88,1)",
        },
        {
          offset: 1,
          color: "rgba(255,255,255,0.5)",
        },
      ]),
    };

    inflChartOp = {
        title: {
          text: "물가 상승 추이",
          left: "center",
          padding: 0,
          textStyle: {
            fontSize: 14,
          },
        },
        animationDuration: 2000,
        dataset: [
            {
                id: "dataset_raw",
                source: _rawData,
            },
            ...datasetWithFilters,
        ],
        legend: {
          padding: 30,
          itemWidth: 20,
          itemHeight: 12,
          itemStyle: {
            borderWidth: 0,
          },
        },
        tooltip: {
            order: "valueDesc",
            trigger: "axis",
        },
        xAxis: {
            type: "category",
            boundaryGap: false,
            offset: 8,
        },
        yAxis: {
            name: "",
            type: "value",
            axisLabel: {
              formatter: "{value}%",
            },
        },
        grid: {
          containLabel: true,
          top: "25%",
          left: "1%",
          right: "5%",
          bottom: 8,
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
        grid: {
          containLabel: true,
          left: "1%",
          right: "4%",
          top: 50,
          bottom: 0
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "line",
          },
        },
        legend: {
          padding: 0,
          textStyle: {
            fontSize: 11,
          },
          itemWidth: 18,
          itemHeight: 11,
          itemStyle: {
            borderWidth: 0,
          },
        },
        xAxis: [
            {
                type: "category",
                data: [],
                boundaryGap: false,
                axisLabel: {
                  textStyle: {
                    fontSize: 11,
                  },
                },
            },
        ],
        yAxis: [
            {
                type: "value",
                name: "단위: " + unit,
                axisLabel: {
                  textStyle: {
                    fontSize: 11,
                  },
                },
            },
        ],
        series: [
            {
                name: "GDP(명목)",
                type: "line",
                tooltip: {
                    valueFormatter: function (value) {
                        return value + unit;
                    },
                },
                data: [],
                zlevel: 2,
                itemStyle: {
                  color: "#1e70e7",
                  borderWidth: 1,
                  borderColor: "#fff",
                },
                areaStyle: {
                  color: "#1e70e7",
                },
                symbol: "circle",
                symbolSize: 7,
                showSymbol: false, // hover 할때만 symbol 표시
                emphasis: {
                  focus: "series",
                },
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
                zlevel: 1,
                itemStyle: {
                  color: "#3ba272",
                  borderWidth: 1,
                  borderColor: "#fff",
                },
                areaStyle: {
                  color: "#3ba272",
                },
                symbol: "circle",
                symbolSize: 7,
                showSymbol: false, // hover 할때만 symbol 표시
                emphasis: {
                  focus: "series",
                },
            },
            {
                name: "국가채무",
                type: "line",
                tooltip: {
                    valueFormatter: function (value) {
                        return value + "조";
                    },
                },
                data: [],
                zlevel: 3,
                itemStyle: {
                  color: "#fac858",
                  borderWidth: 3,
                  borderColor: "#fff",
                  shadowColor: "rgba(0, 0, 0, 0.2)",
                  shadowBlur: 4,
                },
                lineStyle: {
                  width: 3,
                },
                symbol: "circle",
                symbolSize: 7,
                emphasis: {
                  focus: "series",
                },
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