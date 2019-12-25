<template>
    <div id="line_chart">
    </div>
    
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    name: "lineChart",
    components: {},
    data() {
        return {
        };
    },
    computed: {},
    watch: {},
    methods: {
        getData:function(){
            this.axios.get('/static/ave_consum.json').then((result)=>{
                console.log(result.data)
                this.lines=result.data;
                this.drawLineChart();
            })
        },
        drawLineChart: function() {
            //折线图
            const svg = d3
                .select("#line_chart")
                .append("svg")
                .attr("width", 650)
                .attr("height", 350);
            const x = d3.scaleLinear()
                .domain([0,29])
                .range([0, 600]);
            const y = d3.scaleLinear()
                .domain([8,20])
                .range([300, 0]);
            const xAxis = d3.axisBottom(x)
                .ticks(30)
                .tickFormat((d)=>{
                    return d+1;
                });
            const yAxis=d3.axisLeft(y);
            //坐标轴
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(25,325)")
                .attr("class","axis");
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(25,25)")
                .attr("class","axis");
            //线条生成器
            const line=d3.line()
                .x(function(d,i){
                    return x(i);
                })
                .y(function(d){
                    return y(d)
                });
            //n条线
            for(let key in this.lines){
                
                let data=this.lines[key];
                let gs=svg.append("g")
                    .attr("transform", "translate(25,25)")
                    .attr("class",key);
                gs.append("g")
                    .attr("class","line")
                    .append("path")
                        .attr("d",line(data.slice(0,27)));
                gs.append("g")
                    .attr("class","line")
                    .attr("transform","translate(580,0)")
                    .append("path")
                        .attr("d",line(data.slice(28,30)));
                gs.selectAll("circle")
                    .data(data)
                    .enter()
                    .append("circle")
                        .attr("cx",function(d,i){
                            return x(i);
                        })
                        .attr("cy",function(d){
                            return y(d);
                        })
                        .attr("r",4);
            }
            //图例
            let le=[{"boy":"男"},{"ave":"平均"},{"girl":"女"}];
            let legend=svg.append("g")
                .attr("class"," legend")
                .selectAll("g")
                .data(le)
                .enter()
                .append("g")
                .attr("class",function(d){return Object.keys(d)[0]});
            
            legend.append("line")
                .attr("x1",585)
                .attr("y1",function(d,i){return i*15+10})
                .attr("x2",615)
                .attr("y2",function(d,i){return i*15+10});
            legend.append("circle")
                .attr("cx",600)
                .attr("cy",function(d,i){
                    return i*15+10;
                })
                .attr("r",4);
            legend.append("text")
                .attr("x",625)
                .attr("y",function(d,i){return i*15+13})
                .text(function(d){
                    return d[Object.keys(d)[0]]
                })
            svg.append("text")
                .attr("x",40)
                .attr("y",15)
                .text("日平均消费折线图")
                .style("font-size","15px")
        }
    },
    created() {},
    mounted() {
        this.getData();
    }
};
</script>
<style>
#line_chart{
     position: absolute;
     top: 5%;
     left:5%
}
#line_chart *{
    stroke: aliceblue;
}
#line_chart .line path{
    fill:none;
    stroke-width: 1.5px;
    stroke: aliceblue;
}
#line_chart text{
    font-size: 10px;
    font-weight: 100;
}
#line_chart circle{
    cursor: pointer;
}
#line_chart .boy{
    fill: aqua;
}
#line_chart .girl{
    fill: chocolate;
}
#line_chart .ave{
    fill:cornsilk;
}
</style>