function fnProfitLossAndSalesChart(data) {

  /* 차트 - 손익현황 달성률 */
  const plAchvRateChartDom = document.getElementById("plAchvRateGraph");
  if (plAchvRateChartDom) {
    const plAchvRateChart = echarts.init(plAchvRateChartDom);
    let plAchvRateGraphOp;
    let profitData = [
      {
        value: data.profitLossAcheive,
        name: '달성률',
        title: {
          offsetCenter: ['0%', '-15%'],
          fontSize: 11,
        },
        detail: {
          valueAnimation: true,
          offsetCenter: ['0%', '20%']
        }
      }
    ];

    plAchvRateGraphOp = {
      series: [
        {
          type: 'gauge',
          clockwise: false,
          startAngle: 90,
          endAngle: -270,
          radius: "85%",
          center: ["50%", "55%"],
          pointer: {
            show: false
          },
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              color: "#1e70e7"
            }
          },
          axisLine: {
            lineStyle: {
              width: 15
            }
          },
          splitLine: {
            show: false,
            distance: 0,
            length: 10
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false,
            distance: 50
          },
          data: profitData,
          title: {
            fontSize: 12,
          },
          detail: {
            width: 50,
            height: 14,
            fontSize: 14,
            color: '#1e70e7',
            formatter: '{value}%'
          }
        }
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };
    plAchvRateChart.setOption(plAchvRateGraphOp);
  }

  /* 차트 - 매출현황 달성률 */
  const slAchvRateChartDom = document.getElementById("slAchvRateGraph");
  if (slAchvRateChartDom) {
    const slAchvRateChart = echarts.init(slAchvRateChartDom);
    let slAchvRateGraphOp;
    let salesData = [
      {
        value: data.salesAcheive,
        name: '달성률',
        title: {
          offsetCenter: ['0%', '-15%'],
          fontSize: 11,
        },
        detail: {
          valueAnimation: true,
          offsetCenter: ['0%', '20%']
        }
      }
    ];

    slAchvRateGraphOp = {
      series: [
        {
          type: 'gauge',
          clockwise: false,
          startAngle: 90,
          endAngle: -270,
          radius: "85%",
          center: ["50%", "55%"],
          pointer: {
            show: false
          },
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              color: "#1e70e7"
            }
          },
          axisLine: {
            lineStyle: {
              width: 15
            }
          },
          splitLine: {
            show: false,
            distance: 0,
            length: 10
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false,
            distance: 50
          },
          data: salesData,
          title: {
            fontSize: 12,
          },
          detail: {
            width: 50,
            height: 14,
            fontSize: 14,
            color: '#1e70e7',
            formatter: '{value}%'
          }
        }
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };
    slAchvRateChart.setOption(slAchvRateGraphOp);
  }
}

function fnCstmSalesChartDom(data) {
  /* 거래처별 매출현황 */

  // for (let i = 0; i < data.lenght / 3; i++) {
  //   window['array' + i] = [];
  // }

  // for (let i = 0; i < data.length; i++) {
  //   for (let j = 0; j < data.length / 3; j++) {
  //     window['array' + i] = [];
  //     window['array' + j].push(data[i].yr_dt);
  //   }
  //   i += 17
  // }
  // console.log(array15);
  const cstmSalesChartDom = document.getElementById("cstmSalesGraph");
  if (cstmSalesChartDom) {
    const cstmSalesChart = echarts.init(cstmSalesChartDom);
    let cstmSalesGraphOp;


    cstmSalesGraphOp = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          // Use axis to trigger tooltip
          type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
        }
      },
      legend: {},
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value'
      },
      yAxis: {
        type: 'category',
        data: ['2019', '2020', '2021', '2022']
      },
      series: [
        {
          name: '범우정보기술',
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: ['', '', '', 334]
        },
        {
          name: '다음정보기술',
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '포네트',
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '오픈노트',
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: [150, 212, 201, 154, 190, 330, 410]
        },
        {
          name: '한전KDN',
          type: 'bar',
          stack: 'total',
          label: {
            show: true
          },
          emphasis: {
            focus: 'series'
          },
          data: [820, 832, 901, 934, 1290, 1330, 1320]
        }
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };
    cstmSalesChart.setOption(cstmSalesGraphOp);
  }
}

function fnFinStateChartDom(data) {
  /* 재무제표 현황 */
  const finStateChartDom = document.getElementById("finStateGraph");
  if (finStateChartDom) {
    const finStateChart = echarts.init(finStateChartDom);
    let finStateGraphOp;

    finStateGraphOp = {
      // color: [
      //   "#1e70e7",
      //   "#3ba272",
      //   "#fac858",
      //   "#ee6666",
      //   "#5aa7de",
      //   "#fc8452",
      //   "#7780e4",
      //   "#eb9ac9",
      //   "#b2e26e",
      //   "#007a70",
      //   "#00b3a4",
      //   "#7a8489",
      //   "#bfc5c9",
      //   "#294700",
      // ],
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "shadow",
        },
      },
      legend: {
        top: "bottom",
        left: "center",
        textStyle: {
          fontSize: 11,
        },
        itemWidth: 14,
        itemHeight: 10,
        padding: 0,
      },
      grid: {
        top: "12%",
        left: "1%",
        right: "4%",
        bottom: "10%",
        containLabel: true,
      },
      xAxis: [
        {
          type: "category",
          data: [],
        },
      ],
      yAxis: [
        {
          type: "value",
          name: "단위: ",
        },
      ],
      series: [
        {
          name: "",
          type: "bar",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#3ba272",
          },
        },
        {
          name: "",
          type: "bar",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#007a70",
          },
        },
        {
          name: "",
          type: "bar",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#b2e26e",
          },
        },
        {
          name: "",
          type: "bar",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#fac858",
          },
        },
        {
          name: "",
          type: "bar",
          stack: "sales",
          data: [],
          barWidth: 16,
          emphasis: {
            focus: "series",
          },
          itemStyle: {
            color: "#7780e4",
          },
        },
        {
          name: "",
          type: "bar",
          stack: "sales",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#ee6666",
          },
        },
        {
          name: "",
          type: "bar",
          stack: "sales",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#5aa7de",
          },
        },
        {
          name: "",
          type: "bar",
          stack: "sales",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#eb9ac9",
          },
        },
        {
          name: "",
          type: "bar",
          emphasis: {
            focus: "series",
          },
          data: [],
          barWidth: 16,
          itemStyle: {
            color: "#1e70e7",
          },
          markLine: {
            lineStyle: {
              type: "dashed",
              color: "#424a54",
            },
            label: {
              position: "insideEndTop",
              fontSize: 10,
            },
            data: [[{type: "min"}, {type: "max"}]],
          },
        },
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };
    finStateGraphOp.xAxis[0].data = data.period;
    finStateGraphOp.yAxis[0].name += data.unit;

    finStateGraphOp.series[0].name = data.ttlAsset[0];
    finStateGraphOp.series[0].data = data.ttlAsset.splice(1);

    finStateGraphOp.series[1].name = data.capital[0];
    finStateGraphOp.series[1].data = data.capital.splice(1);

    finStateGraphOp.series[2].name = data.eqtyCptl[0];
    finStateGraphOp.series[2].data = data.eqtyCptl.splice(1);

    finStateGraphOp.series[3].name = data.crntLblts[0];
    finStateGraphOp.series[3].data = data.crntLblts.splice(1);

    finStateGraphOp.series[4].name = data.consulting[0];
    finStateGraphOp.series[4].data = data.consulting.splice(1);

    finStateGraphOp.series[5].name = data.sysdevPart[0];
    finStateGraphOp.series[5].data = data.sysdevPart.splice(1);

    finStateGraphOp.series[6].name = data.smPart[0];
    finStateGraphOp.series[6].data = data.smPart.splice(1);

    finStateGraphOp.series[7].name = data.swPart[0];
    finStateGraphOp.series[7].data = data.swPart.splice(1);

    finStateGraphOp.series[8].name = data.totalSum[0];
    finStateGraphOp.series[8].data = data.totalSum.splice(1);

    finStateChart.setOption(finStateGraphOp);
    finStateChart.setOption(finStateGraphOp);
  }
}

/* 사원 현황 */
function fnEmployeeStatusChart(data) {
  const manPowerChartDom = document.getElementById("manPowerGraph");
  if (manPowerChartDom) {
    const manPowerChart = echarts.init(manPowerChartDom);
    let manPowerGraphOp;

    manPowerGraphOp = {
      title: {
        subtext: "단위: 명",
        left: "center",
        top: "bottom",
        padding: 10,
      },
      tooltip: {
        trigger: "item",
        formatter: "{a} <br/>{b}: {c} ({d}%)",
      },
      color: [
        "#1e70e7",
        "#3ba272",
        "#fac858",
        "#ee6666",
        "#5aa7de",
        "#fc8452",
        "#7780e4",
        "#eb9ac9",
        "#b2e26e",
        "#007a70",
        "#00b3a4",
        "#7a8489",
        "#bfc5c9",
        "#294700",
      ],
      series: [
        {
          name: "부서",
          type: "pie",
          selectedMode: "single",
          radius: [0, "50%"],
          center: ["50%", "45%"],
          label: {
            position: "inner",
            fontSize: 10,
            color: "#fff",
          },
          labelLine: {
            show: false,
          },
          data: [],
        },
        {
          name: "근무지",
          type: "pie",
          radius: ["65%", "80%"],
          center: ["50%", "45%"],
          data: [],
        },
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };

    manPowerGraphOp.series[0].data = data.deptList;
    manPowerGraphOp.series[1].data = data.workList;

    manPowerChart.setOption(manPowerGraphOp);
  }
}

/* 기술자 보유 현황 */
function fnTechnicalChart(data) {

  let tchCntList = [];
  let tchLvList = [];
  let resultList = data.technicalList;

  for (let i = 0; i < resultList.length; i++) {
    tchCntList.push(resultList[i].tchCnt);
    tchLvList.push({tchLv: resultList[i].tchLv});
  }

  const techHoldChartDom = document.getElementById("techHoldGraph");
  if (techHoldChartDom) {
    const techHoldChart = echarts.init(techHoldChartDom);
    let techHoldGraphOp;

    techHoldGraphOp = {
      radar: {
        indicator: (function () {
          let res = [];
          for (let {tchLv : lv} of tchLvList) {
            res.push({ text: lv });
          }
          return res;
        })(),
        axisName: {
          fontSize: 11,
          color: "#424a54"
        },
        radius: "72%",
      },
      tooltip: {},
      series: [
        {
          name: '기술자 보유 현황',
          type: 'radar',
          label: {
            show: true,
          },
          symbol: "circle",
          symbolSize: 4,
          areaStyle: {
            color: new echarts.graphic.LinearGradient(1, 1, 0, 0, [
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
          itemStyle: {
            color: "#1e70e7"
          },
          data: [
            {
              value: tchCntList,
              name: ''
            },
          ]
        }
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };

    techHoldChart.setOption(techHoldGraphOp);
  }
}

/* 부서별 인원 현황 */
function fnDepartmentChart(data) {
  let deptList = [];
  let resultList = data.departmentList;

  for (let i = 0; i < resultList.length; i++) {
    deptList.push({deptCnt: resultList[i].deptCnt, deptNm: resultList[i].deptNm});
  }

  const deptMpChartDom = document.getElementById("deptMpGraph");
  if (deptMpChartDom) {
    const deptMpChart = echarts.init(deptMpChartDom);
    let deptMpGraphOp;

    deptMpGraphOp = {
      color: [
        "#1e70e7",
        "#3ba272",
        "#fac858",
        "#ee6666",
        "#5aa7de",
        "#fc8452",
        "#7780e4",
        "#eb9ac9",
        "#b2e26e",
        "#007a70",
        "#00b3a4",
        "#7a8489",
        "#bfc5c9",
        "#294700",
      ],
      title: {
        subtext: "단위: 명",
        left: "center",
        top: "bottom",
        padding: 10,
      },
      tooltip: {
        trigger: "item",
      },
      grid: {
        top: 0,
        bottom: 0,
        containLabel: true,
      },
      series: [
        {
          name: "부서",
          type: "pie",
          radius: [10, 80],
          center: ["50%", "40%"],
          roseType: "area",
          itemStyle: {
            borderRadius: 8,
          },
          label: {
            formatter: "{b}: {c}",
            lineHeight: 14,
          },
          data: (function () {
            let res = [];
            for (let {deptCnt : cnt, deptNm : nm} of deptList) {
              res.push({ value: cnt, name: nm });
            }
            return res;
          })(),
        },
      ],
      textStyle: {
        fontFamily: "NanumSquare",
      },
    };
    deptMpChart.setOption(deptMpGraphOp);
  }
}

function fnWorkYearsChartDom(datas) {
  let dataList = []

  for (let i = 1; i < datas.length; i++) {
    dataList.push(datas[i])
  }
  /* 차트 - 근속년수 현황 */
  const workYearsChartDom = document.getElementById("workYearsGraph");
  if (workYearsChartDom) {
    const workYearsChart = echarts.init(workYearsChartDom);
    let workYearsGraphOp;
    let workYearsData = dataList;
    let workYears = [];

    workYearsData.forEach((data) => {
      workYears.push(data[0]);
      dataMax = Math.max.apply(null, workYears);
      console.log(workYears)

      return dataMax;
    });

    workYearsGraphOp = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "line",
        },
      },
      dataset: {
        source: [
        ] ,
      },
      grid: {containLabel: true, left: 2, right: 10, bottom: 0, top: "32%"},
      yAxis: {name: "단위: 명"},
      xAxis: {
        type: "category",
        axisLabel: {
          interval: 0,
          fontSize: 10,
        },
      },
      visualMap: {
        show: true,
        type: "continuous",
        orient: "horizontal",
        top: 0,
        left: "center",
        padding: 0,
        text: ["max", "min"],
        min: 0,
        max: dataMax,
        itemHeight: "100%",
        itemWidth: "14%",
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
          encode: {
            x: "period",
            y: "amount",
          },
          barWidth: 16,
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
    workYearsGraphOp.dataset.source = datas
    console.log(workYearsGraphOp.dataset.source)
    workYearsChart.setOption(workYearsGraphOp);
  }
}