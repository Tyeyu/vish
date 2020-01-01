<template>
    <div id="histogram"></div>
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
            //测试数据
            data:[0,2,3,4,5,6,7,24,9,30,11,6,8,7,7,12,10,3,7],
            height:null,
        };
    },
    computed: {},
    watch: {},
    methods: {
        getData:function(){
            this.axios.get("static/consume_rate.json").then((data)=>{
                this.stu=data.data.data;
                this.draw();
            })
        },
        draw:function(){
            const me = this;
            const svg = d3
                .select("#histogram")
                .append("svg")
                .attr("width", 240)
                .attr("height", this.height);
            const x = d3.scaleLinear()
                .domain([0,30])
                .range([0, 200]);
            const y = d3.scaleLinear()
                .domain([0,20])
                .range([0,this.height-60]);
            
            const yAxis=d3.axisLeft(y).ticks(5);
            const xAxis=d3.axisTop(x).ticks(5);
            //坐标轴
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(30,50)")
                .attr("class","axis");
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(30,50)")
                .attr("class","axis");
            //
            svg.selectAll("bar")
                .data(this.data)
                .enter().append("rect")
                .attr("transform","translate(30,50)")
                .style("fill", "#a6cee3")
                .attr("y", function(d,i) { return y(i); })
                .attr("height", "10")
                .attr("x","1")
                .attr("width", function(d) { return x(d); });
            //图例
            svg.append("text")
                .text("本月日平均消费/人数")
                .attr("transform","translate(30,23)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
                .attr("fill","antiquewhite");
            svg.append("text")
                .text("人")
                .attr("transform","translate(225,23)")
                .attr("style","font-weight:100")
                .attr("style","font-size:10px")
                .attr("fill","antiquewhite");
            }
            
    },
    created() {},
    mounted() {
        var s=document.getElementById("downpage")
        this.height=s.offsetHeight-50;
        this.getData();
    }
};
</script>
<style>
#histogram{
    width: 240px;
    height: 300px;
    position: absolute;
    left: calc(2% + 1320px);
    top: calc(2% + 570px);
    z-index: 2;
}
#histogram path,line{
    stroke:antiquewhite ;
}
#histogram text{
    font-size: 10px;
    font-weight: 100;
    fill: antiquewhite ;
}
</style>