<template>
    
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
import echarts from "echarts";

export default {
    name: "flowChart",
    components: {},
    data() {
        return {};
    },
    computed: {},
    watch: {},
    methods: {
        initData:function(){
            this.axios.get('/static/flow.json').then((result)=>{
                let timeData=[];
                for(let i=0;i<30;i++){
                    timeData[i]=i+1;
                }
                this.timeData=timeData;
                
                this.data=result.data.data;
                this.drawflowChart();
            })
            
        },
        drawflowChart: function() {
            d3.select("#flowChart").remove();
            d3.select("#app").append("div").attr("id","flowChart");
            var timeData = this.timeData;
            var data1=this.data;
            // console.log(data1)
            var dom = document.getElementById("flowChart");
            var myChart = echarts.init(dom);
            var option = {
                color:["#FFCCCC","#99CC33","#66CCCC","#CCCCFF","#6699CC"],
                title: {
                    text: "各食堂收入趋势",
                    x: "right",
                     textStyle:{
                        color:"antiquewhite"
                    }
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        animation: false
                    }
                },
                legend: {
                    data: ["第一食堂","第二食堂","第三食堂","第四食堂","第五食堂"],
                    x: "left",
                    textStyle:{
                        color:"antiquewhite"
                    }
                },
                
                axisPointer: {
                    link: { xAxisIndex: "all" }
                },
                grid: [
                    {
                        left: 50,
                        right: 50,
                        height: "70%"
                    }
                ],
                xAxis: [
                    {
                        type: "category",
                        name:"日",
                        boundaryGap: false,
                        axisLine: { onZero: true ,lineStyle:{
                            color: "antiquewhite"
                        }},
                        data: timeData
                    }
                ],
                yAxis: [
                    {
                        name: "金额(元)",
                        type: "value",
                        max:12000,
                         axisLine: { onZero: true ,lineStyle:{
                            color: "antiquewhite"
                        }},
                    }
                ],
                series: [
                    {
                        name: "第四食堂",
                        type: "line",
                        symbolSize: 0,
                        hoverAnimation: false,
                        data: data1[3]
                    },
                    {
                        name: "第二食堂",
                        type: "line",
                        symbolSize: 0,
                        hoverAnimation: false,
                        data: data1[1]
                    },
                    {
                        name: "第五食堂",
                        type: "line",
                        symbolSize: 0,
                        hoverAnimation: false,
                        data: data1[4]
                    },
                    {
                        name: "第三食堂",
                        type: "line",
                        symbolSize: 0,
                        hoverAnimation: false,
                        data: data1[2]
                    },
                    {
                        name: "第一食堂",
                        type: "line",
                        symbolSize: 0,
                        hoverAnimation: false,
                        data: data1[0]
                    }
                ]
            };
            myChart.setOption(option, true);
        }
    },
    created() {},
    mounted() {
        // this.initData();
    },
   computed: {
       STSpend () {
            return this.$store.getters.getSTspend;
        }
    },
    watch:{
         STSpend:function(newval,oldval){
             if(newval){
              this.initData();
             }
            
         }
    }
};
</script>
<style>
#flowChart {
    position: absolute;
    border: 1px solid black;
    top: calc(2% + 300px);
    left:calc(6% + 550px);
    width: calc(94% - 550px);
    height: 260px;
}
</style>