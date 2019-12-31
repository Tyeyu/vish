<template>
    <div id="barChart"></div>
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
            //测试数据
            data:[5,6,4,2,1,5,15,16,14,10,10,10,15,16,14,30,14,20,5,6,4,2,1,5,15,16,0,5,4,6,1]
        };
    },
    computed: {},
    watch: {},
    methods: {
        getData:function(){
            this.axios.get("static/consume_rate.json").then((data)=>{
                this.stu=data.data.data;
                //this.draw();
            })
        },
        draw:function(){
            console.log(233);
            const me = this;
            const svg = d3
                .select("#barChart")
                .append("svg")
                .attr("width", 460)
                .attr("height", 280);
            const x = d3.scaleLinear()
                .domain([-1,30])
                .range([0, 420]);
            const y = d3.scaleLinear()
                .domain([0,30])
                .range([200, 0]);
            const xAxis = d3.axisBottom(x)
                .ticks(30)
                .tickFormat((d)=>{
                    return d;
                });
            const yAxis=d3.axisLeft(y).ticks(5);
            //坐标轴
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(20,240)")
                .attr("class","axis");
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(20,40)")
                .attr("class","axis");
            //
            svg.selectAll("bar")
                .data(this.data)
                .enter().append("rect")
                .attr("transform","translate(20,40)")
                .style("fill", "#1f78b4")
                .attr("x", function(d,i) { return x(i)-1; })
                .attr("width", "2")
                .attr("y", function(d,i) { return y(d); })
                .attr("height", function(d) { return 200 - y(d); });
            svg.selectAll("circle")
                .data(this.data)
                .enter().append("circle")
                .attr("transform","translate(20,40)")
                .attr("cx",function(d,i) { return x(i); })
                .attr("cy",function(d,i) { return y(d); })
                .attr("r","4")
                .style("fill", "#1f78b4");
            //图例
            svg.append("text")
                .text("本月消费天数/人数")
                .attr("transform","translate(30,13)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
                .attr("fill","antiquewhite");
            svg.append("text")
                .text("人")
                .attr("transform","translate(0,28)")
                .attr("style","font-weight:100")
                .attr("style","font-size:10px")
                .attr("fill","antiquewhite");
            svg.append("text")
                .text("天")
                .attr("transform","translate(450,255)")
                .attr("style","font-weight:100")
                .attr("style","font-size:10px")
                .attr("fill","antiquewhite");
            }
            
    },
    created() {},
    mounted() {
        //this.getData();
        this.draw();
    }
};
</script>
<style>
#barChart{
    height: 280px;
    position: absolute;
    left: calc(2% + 860px);
    top: calc(2% + 580px);
    z-index: 2;
}
#barChart path,line{
    stroke:antiquewhite ;
}
#barChart text{
    font-size: 10px;
    font-weight: 100;
    fill: antiquewhite
}
</style>