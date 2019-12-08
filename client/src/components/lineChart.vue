<template>
    <div id="line_chart">
        <g class="line1"></g>
    </div>
    
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
/* import axios from 'axios' */
export default {
    name: "lineChart",
    components: {},
    data() {
        return {
            msg: "first vue"
        };
    },
    computed: {},
    watch: {},
    methods: {
        getMajors: function() {
            //获取数据
            $.ajax({
                type: "post",
                url: "http://localhost/allMajors",
                dataType: "json",
                data: { tt: "ww" },
                contentType: "application/json; charset=utf-8",
                success: result => {
                    this.majors = result;
                    console.log(1);
                    this.drawLineChart();
                }
            });
        },
        getAmount:function(){
            this.axios.get('/static/1.json').then((result)=>{
                console.log(result.data)
                this.lines=result.data;
                this.drawLineChart();
            })
        },
        drawLineChart: function() {
            d3.select("#line_chart").append("div").attr("class","test")
            //折线图
            const svg = d3
                .select("#line_chart")
                .append("svg")
                .attr("width", 650)
                .attr("height", 350)
                .style("border", "1px solid black");
            const x = d3.scaleTime()
                .domain([new Date(2019,3,1),new Date(2019,3,30)])
                .range([0, 600]);
            const y = d3.scaleLinear()
                .domain([8,20])
                .range([300, 0]);
            const xAxis = d3.axisBottom(x)
                .ticks(30)
                .tickFormat((d)=>{
                    return d.getDate();
                });
            const yAxis=d3.axisLeft(y);
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(25,325)");
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(25,25)");
            const line=d3.line()
                .x(function(d){
                    let time=d.date.split("/");
                    let dd=new Date(time[0],(time[1]-1),time[2])
                    return x(dd);
                })
                .y(function(d){
                    return y(parseFloat(d.amount)/parseInt(d.number))
                });
            //n条线
            for(let key in this.lines){
                let data=this.lines[key];
                svg.append("g")
                    .attr("class","line")
                    .attr("transform", "translate(25,25)")
                    .append("path")
                        .attr("d",line(data));
                    
                svg.append("g")
                    .attr("class","circle")
                    .attr("transform", "translate(25,25)")
                    .selectAll("circle")
                    .data(data)
                    .enter()
                    .append("circle")
                        .attr("cx",function(d){
                            let time=d.date.split("/");
                            let dd=new Date(time[0],(time[1]-1),time[2])
                            return x(dd);
                        })
                        .attr("cy",function(d){
                            return y(parseFloat(d.amount)/parseInt(d.number));
                        })
                        .attr("r",5)
                        .on("mouseover",function(d){
                            d3.select(this).attr("class","choose")
                        })
                        .on("mouseout",function(d){
                            d3.select(this).attr("class","")
                        })
            }
        }
    },
    created() {},
    mounted() {
        //this.getMajors();
        this.getAmount();
    }
};
</script>
<style>
#line_chart{
     position: absolute;
     top: 5%;
     left:40%
}
#line_chart .line path{
    fill:none;
    stroke: rgb(245, 245, 248);
}
#line_chart .circle circle{
    fill: brown;
}
#line_chart .circle .choose{
    fill: green;
}
</style>