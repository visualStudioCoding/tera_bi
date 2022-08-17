/* 차트 - 행정구역별 매매거래 */
function fnAdmDivTradeChart(data) {

    let region = data.region;
    let datas = data.datas;
    let graphColors = [
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
    ].reverse();
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
                        color: graphColors[idx],
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
                splitNumber: 4,
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
                        distance: 20,
                        align: "center",
                        verticalAlign: "middle",
                        fontSize: 10,
                    },
                    data: chartData,
                },
            ],
            media: [
              {
                query: {
                  minWidth: 315,
                },
                option: {
                  xAxis: {
                      type: "value",
                      name: "건수(건)",
                      splitNumber: 4,
                  },
                },
              },
              {
                query: {
                  maxWidth: 360,
                },
                option: {
                  xAxis: {
                      type: "value",
                      name: "",
                      splitNumber: 2,
                  },
                  grid: {
                    containLabel: true,
                    left: "1%",
                    right: "8%",
                  },
                },
              }
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
                subtext: datas.length < 1 ? "no data" : "",
                subtextStyle: {
                  lineHeight: 100,
                },
                left: "center",
                padding: 0,
                textStyle: {
                    fontSize: 13,
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
                padding: 25,
                itemWidth: 18,
                itemHeight: 10,
            },
            xAxis: [
                {
                    type: "category",
                    axisTick: {show: false},
                    data: [],
                    axisLabel: { fontSize: 11 },
                },
            ],
            yAxis: [
                {
                    type: "value",
                    name: "동(호)",
                    show: datas.length < 1 ? false : true,
                    splitNumber: 3,
                    axisLabel: { fontSize: 11 },
                },
            ],
            series: [
                {
                    name: "아파트",
                    type: "bar",
                    barGap: 0,
                    barWidth: 16,
                    label: labelOption,
                    data: [],
                    itemStyle: {
                      color: "#1e70e7",
                    },
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
            media: [
              // pc (xl)
              {
                query: {
                  minWidth: 455,  // chart's parent container width, not window innerWidth
                },
                option: {
                  series: [
                    {
                        label: {
                          show: true,
                        },
                    }
                  ],
                },
              },
              // mobile (sm)
              {
                query: {
                  maxWidth: 454,
                },
                option: {
                  series: [
                    {
                        label: {
                          show: false,
                        },
                    }
                  ],
                }
              },
            ],
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
    let dataMax = [];
    let dataMin = [];

    for (let i = 0; i < builtYear.length; i++) {
        if (i === 0) {
            chartData.push(["amount", "years"])
        }
        chartData.push([datas[i], builtYear[i]])
        dataMax.push(datas[i])
        dataMin.push(datas[i])
    }
    console.log(chartData)
    dataMax = Math.max.apply(null, dataMax);
    dataMin = Math.min.apply(null, dataMin);

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
                    fontSize: 13,
                },
            },
            dataset: {
                source: [],
            },
            grid: {containLabel: true, left: "1%", right: 10, bottom: 0, top:"45%"},
            yAxis: {name: "동수(동)", splitNumber: 2, axisLabel: { fontSize: 11 } },
            xAxis: {type: "category", axisTick: { alignWithLabel: true },  axisLabel: { fontSize: 11 } },
            visualMap: {
                show: true,
                type: "continuous",
                orient: "horizontal",
                top: "15%",
                left: "center",
                bottom: 0,
                min: dataMin,
                max: dataMax,
                text: ["최대", "최소"],
                itemHeight: "80%",
                itemWidth: 11,
                dimension: 0,
                textStyle: {
                  fontSize: 11,
                },
                inRange: {
                  color: ["#626d7c", "#8ab5f2", "#1e70e7"],
                },
            },
            series: [
                {
                    type: "bar",
                    barWidth: 16,
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
            media: [
              // pc (xl)
              {
                query: {
                  minWidth: 455,  // chart's parent container width, not window innerWidth
                },
                option: {
                  series: [
                    {
                        label: {
                          show: true,
                        },
                    }
                  ],
                },
              },
              // mobile (sm)
              {
                query: {
                  maxWidth: 454,
                },
                option: {
                  series: [
                    {
                        label: {
                          show: false,
                        },
                    }
                  ],
                }
              },
            ],
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
        chartData.value = parseInt(datas[i]);
        dataList.push(chartData);
    }
    console.log(dataList)

    /* 차트 - 지역별 인구수 */
    const popRegionChartDom = document.getElementById("popRegionGraph");
    if (popRegionChartDom) {
        const popRegionChart = echarts.init(popRegionChartDom);
        let popRegionGraphOp;

        popRegionGraphOp = {
            color: ["#bcd5f8", "#6aa2ef", "#1e70e7"],
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
                    data: [],
                    top: "top",
                    name: "전체",
                    roam: false,
                    label: {
                        fontSize: 10,
                        position: "inside",
                        formatter: (params) => {
                          return `${params.data.name}\n${params.data.value.toLocaleString("ko-KR")}`;
                        },
                        lineHeight: 11,
                    },
                    breadcrumb: {
                        itemStyle: {},
                    },
//                    levels: [
//                        {
//                            itemStyle: {
//                                gapWidth: 3,
//                            },
//                        },
//                    ],
                    itemStyle: {
                        gapWidth: 3,
                    },
                    colorMappingBy: "value",
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

    /* 차트 - 소비자물가별 미분양주택 */
    const cmHousingChartDom = document.getElementById("cmHousingGraph");
    if (cmHousingChartDom) {
        let cmHousingChart = echarts.init(cmHousingChartDom);
        let cmHousingGraphOp;

        // prettier-ignore
        let timeData = period;

        cmHousingGraphOp = {
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
//            dataZoom: [
//                {
//                    show: true,
//                    realtime: true,
//                    xAxisIndex: [0, 1],
//                },
//            ],
            xAxis: [
              {
                type: "category",
                boundaryGap: false,
                axisLine: { onZero: true },
                data: timeData,
                axisLabel: {
                  fontSize: 11,
                },
              },
              {
                gridIndex: 1,
                type: "category",
                boundaryGap: false,
                axisLine: { onZero: true },
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
                splitNumber: 4,
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
                    name: "소비자물가",
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
                      // 최소값, 최대값 label 별도 스타일
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
                    // prettier-ignore
                    data: [],
                },
                {
                    name: "미분양주택(호)",
                    type: "line",
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    showSymbol: false,
                    symbol: "circle",
                    symbolSize: 8,
                    lineStyle: {
                      width: 2,
                      color: "#fac858",
                      shadowColor: "rgba(0,0,0,0.3)",
                      shadowBlur: 8,
                      shadowOffsetY: 4,
                    },
                    itemStyle: {
                      color: "#fac858",
                      borderWidth: 2,
                      borderColor: "#fff",
                    },
                    areaStyle: {
                      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        {
                          offset: 0,
                          color: "rgba(255,255,255,0.5)",
                        },
                        {
                          offset: 1,
                          color: "rgba(250,200,88,0.5)",
                        },
                      ]),
                    },
                    markPoint: {
                      // 최소값, 최대값 label 별도 스타일
                      data: [
                        {
                          type: "max",
                          name: "Max",
                          itemStyle: { color: "#fac858" },
                          symbolRotate: 180,
                          symbolSize: 36,
                          label: {
                            position: ["-8%", "50%"],
                            color: "#424a54",
                            fontSize: 8,
                          },
                        },
                      ],
                    },
                    // prettier-ignore
                    data: [],
                },
            ],
            textStyle: {
                fontFamily: "NanumSquare",
            },
            media: [
              // pc (xl)
              {
                query: {
                  minWidth: 578.5,  // chart's parent container width, not window innerWidth
                  minAspectRatio: 1,
                },
                option: {
                  dataZoom: [
                      {
                        show: true,
                        realtime: true,
                        xAxisIndex: [0, 1],
                        height: "85%",
                        top: "middle",
                        right: 10,
                        orient: "vertical",
                      },
                  ],
                  grid: [
                    {
                      containLabel: true,
                      top: 34,
                      left: 27,
                      right: "12%",
                      height: "34%",
                    },
                    {
                      containLabel: true,
                      left: 5,
                      right: "12%",
                      top: "55%",
                      height: "34%",
                    },
                  ],
                },
              },
              // laptop (lg)
              {
                query: {
                  maxWidth: 578,
                },
                option: {
                  dataZoom: [
                      {
                        show: true,
                        realtime: true,
                        xAxisIndex: [0, 1],
                        width: "90%",
                        top: "auto",
                        bottom: 5,
                        left: "center",
                        orient: "horizontal",
                      },
                  ],
                  grid: [
                    {
                      containLabel: true,
                      top: 34,
                      left: 27,
                      right: "3%",
                      height: "32%",
                    },
                    {
                      containLabel: true,
                      left: 5,
                      right: "3%",
                      top: "48%",
                      height: "32%",
                    },
                  ],
                }
              },
              // mobile (sm)
              {
                query: {
                  maxWidth: 352,
                },
                option: {
                  dataZoom: [
                      {
                        show: true,
                        realtime: true,
                        xAxisIndex: [0, 1],
                        width: "100%",
                        top: "auto",
                        bottom: 5,
                        left: "center",
                        orient: "horizontal",
                        height: 25,
                      },
                  ],
                  grid: [
                    {
                      containLabel: true,
                      top: 34,
                      left: 27,
                      right: "3%",
                      height: "32%",
                    },
                    {
                      containLabel: true,
                      left: 5,
                      right: "3%",
                      top: "48%",
                      height: "32%",
                    },
                  ],
                }
              },
            ],
        };
        cmHousingGraphOp.series[0].data = cnsmrList;
        cmHousingGraphOp.series[1].data = unsoldList;
        cmHousingChart.setOption(cmHousingGraphOp);

        /* 반응형 */
        function chartRpsv(height) {
          echarts.dispose(cmHousingChart);
          cmHousingChart = echarts.init(cmHousingChartDom, null, {height: height});
          cmHousingChart.setOption(cmHousingGraphOp);
        }
        if (window.innerWidth <= 1280 && window.innerWidth > 650) {
          chartRpsv(304);
        } else {
          chartRpsv(224);
        }
        window.addEventListener("resize", function(){
          if (window.innerWidth <= 1280 && window.innerWidth > 650) {
            chartRpsv(304);
          } else {
            chartRpsv(224);
          }
        })
    }
}

function fnPopulationByGenderChartDom(data) {
    /* 차트 - 소유자 비율 (성별) -> 인구 비율 */
    const ownerByGenderChartDom = document.getElementById("ownerByGenderGraph");
    if (ownerByGenderChartDom) {
        const ownerByGenderChart = echarts.init(ownerByGenderChartDom);
        let ownerByGenderGraphOp;

        ownerByGenderGraphOp = {
            color: [
              "#1e70e7",
              "#3ba272",
            ],
            title: {
                text: "성별",
                left: "center",
                textStyle: {
                    fontSize: 13,
                },
            },
            tooltip: {
                trigger: "item",
                valueFormatter: function (value) {
                    return value.toLocaleString("ko-KR") + "명";
                },
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
                        color: "#fff",
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
            media: [
              {
                query: {
                  minWidth: 347,
                },
                option: {
                  series: [
                    {
                      center: ["50%", "57%"],
                      radius: ["30%", "80%"],
                    }
                  ],
                  legend: {
                      top: "center",
                      left: "right",
                      orient: "vertical",
                  },
                }
              },
              {
                query: {
                  maxWidth: 346,
                },
                option: {
                  series: [
                    {
                      center: ["50%", "55%"],
                      radius: ["30%", "75%"],
                    }
                  ],
                  legend: {
                      top: "center",
                      left: "right",
                      orient: "vertical",
                  },
                }
              },
              {
                query: {
                  maxWidth: 320,
                },
                option: {
                  series: [
                    {
                      center: ["50%", "50%"],
                      radius: ["25%", "65%"],
                    }
                  ],
                  legend: {
                    orient: "horizontal",
                    left: "center",
                    bottom: 0,
                    top: "auto",
                  }
                }
              },
            ],
        };
        ownerByGenderGraphOp.series[0].data = data
        ownerByGenderChart.setOption(ownerByGenderGraphOp);
    }
}

/* 차트 - 소유자 비율 (연령별) */
function fnOwnerByAgeChartDom(data) {
    const ownerByAgeChartDom = document.getElementById("ownerByAgeGraph");
    const ownerByAgeChart = echarts.init(ownerByAgeChartDom);
    let ownerByAgeGraphOp;

    ownerByAgeGraphOp = {
        color: [
          "#1e70e7",
          "#3ba272",
          "#fac858",
          "#ee6666",
          "#fc8452",
          "#5aa7de",
          "#007a70",
          "#7a8489",
        ],
        title: {
            text: "연령별",
            left: "center",
            textStyle: {
                fontSize: 13,
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
            textStyle: {
              fontSize: 11,
            },
            itemGap: 4,
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
                    textStyle: {
                      fontSize: 11,
                    },
                    color: "#fff",
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: "14",
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
        media: [
          {
            query: {
              minWidth: 347,
            },
            option: {
              series: [
                {
                  center: ["50%", "57%"],
                  radius: ["30%", "80%"],
                  label: {
                    formatter: "{b}\n {d}%",
                    textStyle: {
                      fontSize: 11,
                    },
                  },
                }
              ],
            }
          },
          {
            query: {
              maxWidth: 346,
            },
            option: {
              series: [
                {
                  center: ["50%", "55%"],
                  radius: ["30%", "75%"],
                }
              ],
              legend: {
                  top: "center",
                  left: "right",
                  orient: "vertical",
                  itemWidth: 26,
                  itemHeight: 14,
              },
            }
          },
          {
            query: {
              maxWidth: 320,
            },
            option: {
              title: {
                padding: 0,
              },
              series: [
                {
                  center: ["50%", "45%"],
                  radius: ["25%", "65%"],
                  label: {
                    formatter: "{d}%",
                    textStyle: {
                      fontSize: 10,
                    },
                  }
                }
              ],
              legend: {
                orient: "horizontal",
                left: "center",
                bottom: 0,
                top: "auto",
                itemWidth: 12,
                itemHeight: 8,
              },
            }
          },
        ],
    };
    ownerByAgeGraphOp.series[0].data = data;
    ownerByAgeChart.setOption(ownerByAgeGraphOp);
}