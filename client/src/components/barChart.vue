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
            /* data:[5,6,4,2,1,5,15,16,14,10,10,10,15,16,14,30,14,20,5,6,4,2,1,5,15,16,0,5,4,6,1], */
            height:null,
        };
    },
    computed: {
        selectedMajor () {
            return this.$store.getters.getSelectedMajor;
        }
    },
    watch: {
        selectedMajor:function(newval,oldval){
             if(newval){
                d3.select("#barChart").selectAll("svg").remove();
                this.data=this.stu[newval];
                this.draw();
             }
            
         }
    },
    methods: {
        getData:function(){
            this.axios.get("static/barChart.json").then((data)=>{
                this.stu=data.data;
                this.data=this.stu['18软件技术'];
                this.draw();
            })
        },
        draw:function(){
            console.log(233);
            const me = this;
            const svg = d3
                .select("#barChart")
                .append("svg")
                .attr("width", 460)
                .attr("height", this.height);
            const x = d3.scaleLinear()
                .domain([-1,30])
                .range([0, 420]);
            const y = d3.scaleLinear()
                .domain([0,30])
                .range([this.height-40, 0]);
            const xAxis = d3.axisBottom(x)
                .ticks(30)
                .tickFormat((d)=>{
                    return d;
                });
            const yAxis=d3.axisLeft(y).ticks(5);
            //坐标轴
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(20,"+(this.height-20)+")")
                .attr("class","axis");
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(20,20)")
                .attr("class","axis");
            //
            svg.selectAll("bar")
                .data(this.data)
                .enter().append("rect")
                .attr("transform","translate(20,20)")
                .style("fill", "#1f78b4")
                .attr("x", function(d,i) { return x(i)-1; })
                .attr("width", "2")
                .attr("y", me.height-40)
                .transition()
                .duration(500)
                .attr("y", function(d,i) { return y(d); })
                .attr("height", function(d) { return me.height-40 - y(d); });
            let c=svg.selectAll("circle")
                .data(this.data)
                .enter().append("circle")
                .attr("transform","translate(20,20)")
                .attr("cx",function(d,i) { return x(i); })
                .attr("cy",function(d,i) { return y(d); })
                .attr("r","4")
                .style("fill", "#1f78b4")
                .style("cursor", "pointer")
                .on("mousemove",function(d,i){
                    d3.select(this)
                        .transition()
                        .duration(100)
                        .attr("r",6)
                        .style("fill","Tomato")
                })
                .on("mouseout",function(){
                    d3.select(this)
                        .transition()
                        .duration(100)
                        .attr("r",4)
                        .style("fill","#1f78b4")
                })
            c.append("title")
                .text(function(d){return d})
            //图例
            svg.append("text")
                .text("本月消费天数/人数")
                .attr("transform","translate(60,13)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
                .attr("fill","antiquewhite");
            svg.append("text")
                .text("人")
                .attr("transform","translate(0,10)")
                .attr("style","font-weight:100")
                .attr("style","font-size:10px")
                .attr("fill","antiquewhite");
            }
            
    },
    created() {},
    mounted() {
        //this.getData();
        var s=document.getElementById("downpage")
        this.height=s.offsetHeight-40;
        this.getData();
    }
};
</script>
<style>
#barChart{
    height: 280px;
    position: absolute;
    left: calc(2% + 860px);
    top: calc(2% + 585px);
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