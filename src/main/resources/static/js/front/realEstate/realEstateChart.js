/* 차트 - 행정구역별 매매거래 */
function fnAdmDivTradeChart(data) {

    let region = data.region;
    let datas = data.datas;
    let graphColors = ["rgba(66, 104, 79, 1)", "rgba(45, 91, 127, 1)", "rgba(111, 20, 53, 1)", "rgba(28, 36, 108, 1)", "rgba(229, 173, 54, 1)", "rgba(218, 218, 218, 1)", "rgba(222, 52, 8, 1)", "rgba(1, 160, 198, 1)", "rgba(14, 65, 148, 1)", "rgba(175, 184, 76, 1)", "rgba(187, 37, 73, 1)", "rgba(169, 28, 158, 1)", "rgba(136, 77, 61, 1)", "rgba(234, 219, 129, 1)", "rgba(48, 50, 101, 1)", "rgba(169, 123, 89, 1)", "rgba(75, 74, 69, 1)"]
    let barData = [];

    const admDivTradeChartDom = document.getElementById("admDivTradeGraph");

    if (admDivTradeChartDom) {
        const admDivTradeChart = echarts.init(admDivTradeChartDom);
        let admDivTradeGraphOp;

        for (let i = 0; i < region.length; i++) {
            barData.push([region[i], datas[i], graphColors[i]]);
        }
        let barDataSort = barData.sort((a, b) => a[1] - b[1]); // 거래량 기준 내림차순 정렬
        const chartData = seriesData(barDataSort);

        // y축 데이터로 지역 추가
        function getYAxisData() {
            yAxisArr = [];
            for (i = 0; i < chartData.length; i++) {
                yAxisArr[i] = chartData[i].name;
            }
        }

        getYAxisData();

        function seriesData(data) {
            return data.map((val, idx) => {
                return {
                    name: data[idx][0],
                    value: data[idx][1],
                    itemStyle: {
                        color: data[idx][2],
                    },
                };
            });
        }

        admDivTradeGraphOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "건";
                },
            },
            grid: {
                left: -10,
                right: "12%",
                bottom: 0,
                top: 0,
                containLabel: true,
            },
            xAxis: {
                type: "value",
                name: "건수(건)",
            },
            yAxis: {
                type: "category",
                data: yAxisArr,
            },
            series: [
                {
                    name: "",
                    type: "bar",
                    label: {
                        show: true,
                        position: "right",
                        distance: 16,
                        align: "center",
                        verticalAlign: "middle",
                        fontSize: 10,
                    },
                    data: chartData,
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        admDivTradeChart.setOption(admDivTradeGraphOp);
    }
}

/* 차트 - 연령대별 매매거래 */
function fnAgeTradeGraphOp(data) {
    let ages = data.ages;
    let datas = data.datas;
    const ageTradeChartDom = document.getElementById("ageTradeGraph");
    if (ageTradeChartDom) {
        const ageTradeChart = echarts.init(ageTradeChartDom);
        const labelOption = {
            show: true,
            position: "top",
            distance: 12,
            align: "center",
            verticalAlign: "top",
            formatter: "{c}",
            fontSize: 10,
        };
        let ageTradeGraphOp;

        ageTradeGraphOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "동(호)";
                },
            },
            title: {
                text: "연령대별 매매거래",
                left: "center",
                padding: 0,
                textStyle: {
                    fontSize: 14,
                },
            },
            grid: {
                containLabel: true,
                left: 5,
                right: 10,
                top: 60,
                bottom: 0,
            },
            legend: {
                data: ["아파트"],
                // data: ["아파트", "주택"],
                padding: 30,
            },
            xAxis: [
                {
                    type: "category",
                    axisTick: {show: false},
                    data: [],
                },
            ],
            yAxis: [
                {
                    type: "value",
                    name: "동(호)",
                    splitNumber: 3,
                },
            ],
            series: [
                {
                    name: "아파트",
                    type: "bar",
                    barGap: 0,
                    label: labelOption,
                    data: [],
                }
                // {
                //     name: "주택",
                //     type: "bar",
                //     label: labelOption,
                //     data: [3841, 11684, 13247, 13793, 9891, 4753, 5991],
                // },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },

        };
        ageTradeGraphOp.xAxis[0].data = ages;
        ageTradeGraphOp.series[0].data = datas;
        ageTradeChart.setOption(ageTradeGraphOp);
    }
}

function fnConstTradeGraphOp(data) {

    let builtYear = data.years;
    let datas = data.datas;
    let chartData = [];

    for (let i = 0; i < builtYear.length; i++) {
        if (i === 0) {
            chartData.push(["amount", "years"])
        }
        chartData.push([datas[i], builtYear[i]])
    }
    console.log(chartData)

    /* 차트 - 건축년수별 매매거래 */
    const constTradeChartDom = document.getElementById("constTradeGraph");
    if (constTradeChartDom) {
        const constTradeChart = echarts.init(constTradeChartDom);
        let constTradeGraphOp;

        constTradeGraphOp = {
            tooltip: {
                trigger: "axis",
                axisPointer: {
                    type: "shadow",
                },
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "동";
                },
            },
            title: {
                text: "건축년수별 매매거래",
                left: "center",
                padding: 0,
                textStyle: {
                    fontSize: 14,
                },
            },
            dataset: {
                source: [],
            },
            grid: {containLabel: true, left: 2, right: 10, bottom: 0, top: 30},
            yAxis: {name: "동수(동)", splitNumber: 4},
            xAxis: {type: "category"},
            visualMap: {
                show: false,
                orient: "horizontal",
                left: "center",
                bottom: 0,
                min: 100,
                max: 1200,
                text: ["5년 미만", "60년 이상"],
                dimension: 0,
                inRange: {
                    color: ["#65B581", "#FFCE34", "#FD665F"],
                },
            },
            series: [
                {
                    type: "bar",
                    encode: {
                        x: "years",
                        y: "amount",
                    },
                    label: {
                        show: true,
                        position: "top",
                        distance: 12,
                        align: "center",
                        verticalAlign: "top",
                        fontSize: 10,
                    },
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        constTradeGraphOp.dataset.source = chartData;
        constTradeChart.setOption(constTradeGraphOp);
    }
}

function fnPopRegionGraphOp(data) {
    let region = data.region;
    let datas = data.datas;

    let dataList = [];

    for (let i = 0; i < region.length; i++) {
        let chartData = {};

        chartData.name = region[i];
        chartData.value = datas[i];
        dataList.push(chartData);
    }
    console.log(dataList)

    /* 차트 - 지역별 인구수 */
    const popRegionChartDom = document.getElementById("popRegionGraph");
    if (popRegionChartDom) {
        const popRegionChart = echarts.init(popRegionChartDom);
        let popRegionGraphOp;

        popRegionGraphOp = {
            textStyle: {
                fontFamily: "NanumSquare",
            },
            tooltip: {
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "명";
                },
            },
            series: [
                {
                    type: "treemap",
                    width: "100%",
                    height: "88%",
                    top: "top",
                    name: "전체",
                    roam: false,
                    label: {
                        fontSize: 10,
                        position: "inside",
                        formatter: function (params) {
                            let arr = [params.name, params.value.toLocaleString("ko-KR")];
                            return arr.join("\n");
                        },
                        lineHeight: 11,
                    },
                    breadcrumb: {
                        itemStyle: {},
                    },
                    levels: [
                        {
                            itemStyle: {
                                // borderWidth: 3,
                                // borderColor: "#333",
                                gapWidth: 3,
                            },
                        },
                    ],
                    itemStyle: {
                        gapWidth: 5,
                    },
                    data: [
                        // {
                        //     name: "경기도",
                        //     value: 13571450,
                        // },
                        // {
                        //     name: "충청도",
                        //     value: 3715735,
                        //     itemStyle: {
                        //         borderWidth: 0,
                        //         gapWidth: 0,
                        //     },
                        //     children: [
                        //         {
                        //             name: "충청북도",
                        //             value: 1597097,
                        //         },
                        //         {
                        //             name: "충청북도",
                        //             value: 2118638,
                        //         },
                        //     ],
                        // },
                        // {
                        //     name: "전라도",
                        //     value: 3617996,
                        //     itemStyle: {
                        //         borderWidth: 0,
                        //         gapWidth: 0,
                        //     },
                        //     children: [
                        //         {
                        //             name: "전라북도",
                        //             value: 1785392,
                        //         },
                        //         {
                        //             name: "전라남도",
                        //             value: 1832604,
                        //         },
                        //     ],
                        // },
                        // {
                        //     name: "경상도",
                        //     value: 5935748,
                        //     itemStyle: {
                        //         borderWidth: 0,
                        //         gapWidth: 0,
                        //     },
                        //     children: [
                        //         {
                        //             name: "경상북도",
                        //             value: 2624310,
                        //         },
                        //         {
                        //             name: "경상남도",
                        //             value: 3311438,
                        //         },
                        //     ],
                        // },
                        // {
                        //     name: "강원도",
                        //     value: 1538660,
                        // },
                        // {
                        //     name: "제주특별자치도",
                        //     value: 676691,
                        // },
                        // {
                        //     name: "서울특별시",
                        //     value: 9505926,
                        // },
                        // {
                        //     name: "부산광역시",
                        //     value: 3348874,
                        // },
                        // {
                        //     name: "대구광역시",
                        //     value: 2383858,
                        // },
                        // {
                        //     name: "인천광역시",
                        //     value: 2949150,
                        // },
                        // {
                        //     name: "광주광역시",
                        //     value: 1441636,
                        // },
                        // {
                        //     name: "대전광역시",
                        //     value: 1451272,
                        // },
                        // {
                        //     name: "울산광역시",
                        //     value: 1121100,
                        // },
                        // {
                        //     name: "세종특별자치시",
                        //     value: 374377,
                        // },
                    ],
                },
            ],
        };
        popRegionGraphOp.series[0].data = dataList;
        popRegionChart.setOption(popRegionGraphOp);
    }
}

function fnCMHousingChartDom(data) {
    let unsoldList = [];
    let cnsmrList = [];
    let period = [];

    for (let i = 0; i < data.cnsmr.length; i++) {
        unsoldList.push(data.unsold[i].val);
        cnsmrList.push(data.cnsmr[i].val);
        period.push(data.unsold[i].period);

    }
    console.log(unsoldList)
    console.log(cnsmrList)

    /* 차트 - 기준금리별 미분양주택 */
    const cmHousingChartDom = document.getElementById("cmHousingGraph");
    if (cmHousingChartDom) {
        const cmHousingChart = echarts.init(cmHousingChartDom);
        let cmHousingGraphOp;

        // prettier-ignore
        let timeData = period;

        cmHousingGraphOp = {
            tooltip: {
                trigger: "axis",
            },
            legend: {
                data: ["소미자물가", "미분양주택(호)"],
                center: "center",
                padding: 0,
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
                    realtime: true,
                    xAxisIndex: [0, 1],
                    height: "86%",
                    bottom: 5,
                    right: 20,
                    orient: "vertical",
                },
            ],
            grid: [
                {
                    containLabel: true,
                    top: 30,
                    left: 35,
                    right: "12%",
                    height: "38%",
                },
                {
                    containLabel: true,
                    left: 5,
                    right: "12%",
                    top: "60%",
                    height: "38%",
                },
            ],
            xAxis: [
                {
                    type: "category",
                    boundaryGap: false,
                    axisLine: {onZero: true},
                    data: timeData,
                },
                {
                    gridIndex: 1,
                    type: "category",
                    boundaryGap: false,
                    axisLine: {onZero: true},
                    data: timeData,
                    position: "top",
                },
            ],
            yAxis: [
                {
                    name: "",
                    type: "value",
                    splitNumber: 4,
                },
                {
                    gridIndex: 1,
                    name: "",
                    type: "value",
                    inverse: true,
                    splitNumber: 3,
                },
            ],
            series: [
                {
                    name: "소비자물가",
                    type: "line",
                    symbolSize: 0,
                    lineStyle: {
                        width: 2,
                        shadowColor: "rgba(0,0,0,0.3)",
                        shadowBlur: 8,
                        shadowOffsetY: 4,
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 1,
                                color: "rgb(67,203,255)",
                            },
                            {
                                offset: 0,
                                color: "rgb(115,110,254)",
                            },
                        ]),
                    },
                    // prettier-ignore
                    data: [],
                },
                {
                    name: "미분양주택(호)",
                    type: "line",
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    symbolSize: 0,
                    color: "#ff6629",
                    lineStyle: {
                        color: "#ff6629",
                        width: 2,
                        shadowColor: "rgba(0,0,0,0.1)",
                        shadowBlur: 8,
                        shadowOffsetY: -4,
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {
                                offset: 0,
                                color: "#FCCF31",
                            },
                            {
                                offset: 1,
                                color: "#F55555",
                            },
                        ]),
                    },
                    // prettier-ignore
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        cmHousingGraphOp.series[0].data = cnsmrList;
        cmHousingGraphOp.series[1].data = unsoldList;
        cmHousingChart.setOption(cmHousingGraphOp);
    }
}

function fnPopulationByGenderChartDom(data) {
    /* 차트 - 소유자 비율 (성별) -> 인구 비율 */
    const ownerByGenderChartDom = document.getElementById("ownerByGenderGraph");
    if (ownerByGenderChartDom) {
        const ownerByGenderChart = echarts.init(ownerByGenderChartDom);
        let ownerByGenderGraphOp;

        ownerByGenderGraphOp = {
            title: {
                text: "성별",
                left: "center",
                textStyle: {
                    fontSize: 14,
                },
            },
            tooltip: {
                trigger: "item",
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "명";
                },
            },
            legend: {
                top: "center",
                left: "right",
                orient: "vertical",
            },
            series: [
                {
                    name: "",
                    type: "pie",
                    center: ["50%", "57%"],
                    radius: ["30%", "80%"],
                    itemStyle: {
                        borderRadius: 4,
                        borderColor: "#fff",
                        borderWidth: 2,
                    },
                    label: {
                        show: true,
                        formatter: "{b}\n {d}%",
                        position: "inside",
                        lineHeight: 16,
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: "16",
                            fontWeight: "bold",
                        },
                    },
                    labelLine: {
                        show: false,
                    },
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        ownerByGenderGraphOp.series[0].data = data
        ownerByGenderChart.setOption(ownerByGenderGraphOp);
    }
}

/* 차트 - 소유자 비율 (연령별) */
function fnOwnerByAgeChartDom(data) {
    const ownerByAgeChartDom = document.getElementById("ownerByAgeGraph");
    if (ownerByAgeChartDom) {
        const ownerByAgeChart = echarts.init(ownerByAgeChartDom);
        let ownerByAgeGraphOp;

        ownerByAgeGraphOp = {
            title: {
                text: "연령별",
                left: "center",
                textStyle: {
                    fontSize: 14,
                },
            },
            tooltip: {
                trigger: "item",
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "명";
                },
            },
            legend: {
                top: "center",
                left: "right",
                orient: "vertical",
            },
            series: [
                {
                    name: "",
                    type: "pie",
                    center: ["50%", "57%"],
                    radius: ["30%", "80%"],
                    avoidLabelOverlap: false,
                    itemStyle: {
                        borderRadius: 4,
                        borderColor: "#fff",
                        borderWidth: 2,
                    },
                    label: {
                        show: true,
                        formatter: "{b}\n {d}%",
                        position: "inside",
                    },
                    emphasis: {
                        label: {
                            show: true,
                            fontSize: "16",
                            fontWeight: "bold",
                        },
                    },
                    labelLine: {
                        show: false,
                    },
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
        };
        ownerByAgeGraphOp.series[0].data = data;
        ownerByAgeChart.setOption(ownerByAgeGraphOp);
    }
}
