<template>
   
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
import echarts from "echarts";

export default {
    name: "sunburst",
    components: {},
    data() {
        return {};
    },
    computed: {},
    watch: {},
    methods: {
        drawSunburst: function() { 
            d3.select("#sunburst").remove();
            d3.select("#app").append("div").attr("id","sunburst");
            var dom = document.getElementById("sunburst");
            var myChart = echarts.init(dom);
            var app = {};
            let option = null;
            var data = [
                {
                    name: "第一食堂",
                    value: 81504.6,
                    children: [
                        { name: "早", value: 19326.2 },
                        { name: "中", value: 30774.9 },
                        { name: "晚", value: 22360.0 }
                    ]
                },
                {
                    name: "第二食堂",
                    value: 188933.9,
                    children: [
                        { name: "早", value: 46820.5 },
                        { name: "中", value: 65837.8 },
                        { name: "晚", value: 54511.9 }
                    ]
                },
                {
                    name: "第三食堂",
                    value: 127923.7,
                    children: [
                        { name: "早", value: 1256.7 },
                        { name: "中", value: 67096.9 },
                        { name: "晚", value: 57130.8 }
                    ]
                },
                {
                    name: "第四食堂",
                    value: 218708.9,
                    children: [
                        { name: "早", value: 1031.5 },
                        { name: "中", value: 113311.9 },
                        { name: "晚", value: 98963.0 }
                    ]
                },
                {
                    name: "第五食堂",
                    value: 167062.3,
                    children: [
                        { name: "早", value: 34140.6 },
                        { name: "中", value: 75004.8 },
                        { name: "晚", value: 53175.0 }
                    ]
                }
            ];
            option = {
                color:["#FFCCCC","#99CC33","#66CCCC","#CCCCFF","#6699CC"],
                series: {
                    type: "sunburst",
                    highlightPolicy: "ancestor",
                    data: data,
                    radius: [0, "90%"],
                    label: {
                        rotate: "radial",
                        fontSize: "10px"
                    }
                }
            };
        myChart.setOption(option, true);
        }
    },
    created() {},
    mounted() {
        // this.drawSunburst();
    },
    computed: {
       STSpend () {
            return this.$store.getters.getSTspend;
        }
    },
    watch:{
         STSpend:function(newval,oldval){
             if(newval){
               this.drawSunburst();
             }
            
         }
    }
};
</script>
<style>
#sunburst {
    position: absolute;
    top: 1%;
    left: calc(12% + 1050px);
    width: 330px;
    height: 300px;
}
</style>