/******************** 종합주가지수 *********************/

/* 차트 - GDP */
function fnGdpChart(data) {
    // console.log(data);
    const gdpChartDom = document.getElementById("gdpGraph");
    if (gdpChartDom) {
        let gdpChart = echarts.init(gdpChartDom);
        let gdpGraphOp;

        gdpGraphOp = {
            grid: {
                containLabel: true,
                left: 3,
                right: 0,
                top: "25%",
                bottom: 0,
            },
            legend: {
                padding: 0,
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
            },
            dataset: {
                source: data,
            },
            xAxis: {type: "category"},
            yAxis: {name: "단위: 조원"},
            series: [
                {
                    type: "bar",
                    name: "명목",
                    itemStyle: {
                      color: "#ced4da",
                    },
                    barWidth: 10,
                    emphasis: {
                      focus: "series",
                    },
                },
                {
                    type: "bar",
                    name: "실질",
                    itemStyle: {
                        color: "#1e70e7",
                    },
                    barWidth: 10,
                    emphasis: {
                        focus: "series",
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        gdpChart.setOption(gdpGraphOp);

        /* 반응형 */
        function chartRpsv(height) {
          echarts.dispose(gdpChart);
          gdpChart = echarts.init(gdpChartDom, null, {height: height});
          gdpChart.setOption(gdpGraphOp);
        }
        if (window.innerWidth <= 1280 && window.innerWidth > 650) {
          chartRpsv(330);
        } else {
          chartRpsv(208);
        }
        window.addEventListener("resize", function(){
          if (window.innerWidth <= 1280 && window.innerWidth > 650) {
            chartRpsv(330);
          } else {
            chartRpsv(208);
          }
        })
    }
}

/* 차트 - 코로나 시기 KOSPI */
function fnCovidKospiChart(data) {

    let covidKospi = [];
    let kospiDate = [];

    for (let i = 0; i < data.covidKospi.length; i++) {
        covidKospi.push(data.covidKospi[i].val);
        kospiDate.push(data.covidKospi[i].kospiDate);
    }

    const covidKospiChartDom = document.getElementById("covidKospiGraph");
    if (covidKospiChartDom) {
        const covidKospiChart = echarts.init(covidKospiChartDom);
        let covidKospiGraphOp;

        covidKospiGraphOp = {
            grid: {
                containLabel: true,
                top: "9%",
                bottom: 70,
                left: 10,
                right: 6,
            },
            tooltip: {
                trigger: "axis",
            },
            xAxis: {
                type: "category",
                boundaryGap: false,
                axisLabel: {
                    padding: 10,
                },
                data: [],
            },
            yAxis: {
                type: "value",
            },
            dataZoom: [
                {
                    type: "inside",
                    start: 0,
                },
                {
                    start: 0,
                },
            ],
            series: [
                {
                    name: "KOSPI",
                    type: "line",
                    symbol: "circle",
                    symbolSize: 8,
                    showSymbol: false,
                    itemStyle: {
                        color: "#ee6666",
                        borderWidth: 2,
                        borderColor: "#fff",
                    },
                    lineStyle: {
                        width: 2.5,
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                    },
                    markPoint: {
                    data: [
                      {
                        type: "max",
                        name: "Max",
                        itemStyle: { color: "#ee6666" },
                        label: {
                          formatter: (params) => {
                            return params.value.toFixed(0);
                          },
                          color: "#fff",
                          fontSize: 9.5,
                          fontWeight: 300,
                        },
                        symbolSize: 40,
                      },
                      {
                        type: "min",
                        name: "Min",
                        symbolRotate: 180,
                        itemStyle: { color: "#7a8489" },
                        label: {
                          position: ["0", "50%"],
                          formatter: (params) => {
                            return params.value.toFixed(0);
                          },
                          color: "#fff",
                          fontSize: 9.5,
                          fontWeight: 300,
                        },
                        symbolSize: 40,
                      },
                    ],
                  },
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        covidKospiGraphOp.xAxis.data = kospiDate;
        covidKospiGraphOp.series[0].data = covidKospi

        covidKospiChart.setOption(covidKospiGraphOp);
    }
}

/* 차트 - 기준금리와 KOSPI */
function fnBaseRateAndKospi(data) {
    let baseRate = [];
    let kospi = [];
    let date = [];

    for (let i = 0; i < data.baseRateAndKospi.length; i++) {
        if(data.baseRateAndKospi[i].type === 'kospi') {
            kospi.push(data.baseRateAndKospi[i].val);
        } else {
            date.push(data.baseRateAndKospi[i].date);
            baseRate.push(data.baseRateAndKospi[i].val);
        }
    }
    const kospiChartDom = document.getElementById("baseRateKospiGraph");
    if (kospiChartDom) {
        const kospiChart = echarts.init(kospiChartDom);
        let kospiGraphOp;

        // prettier-ignore
        let timeData = date;

        kospiGraphOp = {
            tooltip: {
                trigger: "axis",
            },
            legend: {
                padding: 0,
                itemWidth: 18,
                itemHeight: 11,
                itemStyle: {
                  borderWidth: 0,
                },
            },
            axisPointer: {
                link: [
                    {
                        xAxisIndex: "all",
                    },
                ],
            },
            dataZoom: [
                {
                    show: true,
                    xAxisIndex: [0, 1],
                },
                {
                    type: "inside",
                    xAxisIndex: [0, 1],
                },
            ],
            grid: [
                {
                  top: 35,
                  left: 40,
                  right: 6,
                  height: "27%",
                },
                {
                  left: 40,
                  right: 6,
                  top: "55%",
                  height: "27%",
                },
            ],
            xAxis: [
                {
                    type: "category",
                    boundaryGap: false,
                    axisLine: { onZero: true },
                    axisLabel: {
                        fontSize: 11,
                    },
                    data: timeData,
                },
                {
                    gridIndex: 1,
                    type: "category",
                    boundaryGap: false,
                    axisLine: {onZero: true},
                    data: timeData,
                    position: "top",
                    axisLabel: {
                        fontSize: 11,
                    },
                },
            ],
            yAxis: [
                {
                    name: "",
                    type: "value",
                    splitNumber: 3,
                    axisLabel: {
                        fontSize: 11,
                    },
                },
                {
                    gridIndex: 1,
                    name: "",
                    type: "value",
                    inverse: true,
                    splitNumber: 3,
                    axisLabel: {
                        fontSize: 11,
                    },
                },
            ],
            series: [
                {
                    name: "기준금리",
                    type: "line",
                    showSymbol: false,
                    symbol: "circle",
                    symbolSize: 8,
                    lineStyle: {
                        width: 2,
                        color: "#1e70e7",
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                    },
                    itemStyle: {
                        color: "rgba(30,112,231,1)",
                        borderWidth: 2,
                        borderColor: "#fff",
                    },
                    areaStyle: {
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
                    },
                    markPoint: {
                        data: [
                          {
                            type: "max",
                            name: "Max",
                            itemStyle: { color: "#1e70e7" },
                            symbolSize: 36,
                            label: {
                              formatter: (params) => {
                                return params.value.toFixed(2);
                              },
                              color: "#fff",
                              fontSize: 8,
                              fontWeight: 300,
                            },
                          },
                        ],
                    },
                    data: [],
                    smooth: true,
                },
                {
                    name: "KOSPI",
                    type: "line",
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    showSymbol: false,
                    symbol: "circle",
                    symbolSize: 8,
                        lineStyle: {
                        color: "#ee6666",
                        width: 2,
                        shadowColor: "rgba(0,0,0,0.1)",
                        shadowBlur: 8,
                        shadowOffsetY: -4,
                    },
                    itemStyle: {
                        color: "#ee6666",
                        borderWidth: 2,
                        borderColor: "#fff",
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                          {
                            offset: 1,
                            color: "rgba(238,102,102,1)",
                          },
                          {
                            offset: 0,
                            color: "rgba(255,255,255,0.5)",
                          },
                        ]),
                    },
                    markPoint: {
                        data: [
                          {
                            type: "max",
                            name: "Max",
                            itemStyle: { color: "#ee6666" },
                            symbolRotate: 180,
                            symbolSize: 36,
                            label: {
                              position: ["0", "50%"],
                              formatter: (params) => {
                                return params.value.toFixed(0);
                              },
                              color: "#fff",
                              fontSize: 8,
                              fontWeight: 300,
                            },
                          },
                        ],
                    },
                    data: [],
                    smooth: true,
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };

        kospiGraphOp.series[0].data = baseRate;
        kospiGraphOp.series[1].data = kospi;

        kospiChart.setOption(kospiGraphOp);
    }
}

/* 차트 - 연도별 경제성장률 */
function fnInflYearChart(data) {
    let growthRate = [];
    let year = [];

    for (let i = 0; i < data.annualGrowthRate.length; i++) {
        growthRate.push(data.annualGrowthRate[i].val);
        year.push(data.annualGrowthRate[i].yrDt);
    }

    const inflYearChartDom = document.getElementById("inflationYearlyGraph");
    if (inflYearChartDom) {
        const inflYearChart = echarts.init(inflYearChartDom);
        let inflYearGraphOp;

        inflYearGraphOp = {
            grid: {
                containLabel: true,
                top: 10,
                bottom: 0,
                left: 0,
                right: 0,
            },
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
                valueFormatter: function (value) {
                    return value + "%";
                },
            },
            xAxis: {
                type: "category",
                data: [],
                axisLabel: {
                    margin: 20,
                },
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
                    name: "경제 성장률",
                    symbol: "circle",
                    symbolSize: 10,
                    label: {
                        show: true,
                        fontSize: 10,
                    },
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

        inflYearGraphOp.xAxis.data = year;
        inflYearGraphOp.series[0].data = growthRate

        inflYearChart.setOption(inflYearGraphOp);
    }
}
