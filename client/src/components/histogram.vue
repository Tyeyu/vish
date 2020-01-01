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
                d3.select("#histogram").selectAll("svg").remove();
                this.data=this.stu[newval];
                this.draw();
             }
            
         }
    },
    methods: {
        getData:function(){
            this.axios.get("static/histogram.json").then((data)=>{
                this.stu=data.data;
                this.data=this.stu['18软件技术'];
                this.draw();
            })
        },
        draw:function(){
            const me = this;
            const hei=(this.height-30)/40-2;
            const svg = d3
                .select("#histogram")
                .append("svg")
                .attr("width", 240)
                .attr("height", this.height);
            const x = d3.scaleLinear()
                .domain([0,20])
                .range([0, 200]);
            const y = d3.scaleLinear()
                .domain([0,40])
                .range([0,this.height-30]);
            
            const yAxis=d3.axisLeft(y).ticks(5);
            const xAxis=d3.axisTop(x).ticks(5);
            //坐标轴
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(30,20)")
                .attr("class","axis");
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(30,20)")
                .attr("class","axis");
            //
           let t=svg.selectAll("bar")
                .data(this.data)
                .enter().append("rect")
                .attr("transform","translate(30,20)")
                .style("fill", "#a6cee3")
                .style("cursor","pointer")
                .attr("y", function(d,i) { return y(i); })
                .attr("height", hei)
                .attr("x","1")
                .attr("width", function(d) { return x(d); })
                .on("mouseover",function(d,i){
                    d3.select(this)
                        .transition()
                        .duration(100)
                        .style("fill","Tomato")
                })
                .on("mouseout",function(d,i){
                    d3.select(this)
                        .transition()
                        .duration(100)
                        .style("fill","#a6cee3")
                });
                
                
            t.append("title")
                .text(function(d,i){return i+"元 "+d+"人"})
            //图例
            /* svg.append("text")
                .text("本月日平均消费/人数")
                .attr("transform","translate(30,23)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
                .attr("fill","antiquewhite"); */
            svg.append("text")
                .text("人")
                .attr("transform","translate(231,23)")
                .attr("style","font-weight:100")
                .attr("style","font-size:10px")
                .attr("fill","antiquewhite");
            }
            
    },
    created() {},
    mounted() {
        var s=document.getElementById("downpage")
        this.height=s.offsetHeight-30;
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