<template>
    <div id="bar"></div>
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
            color1:["#1E90FF","Tomato"]
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
            let yindex=0,xindex=0;
            const me = this;
            const x = d3.scaleLinear()
                .domain([0,200])
                .range([0, 150]);
            const svg=d3.select("#bar")
                .append("svg")
                .attr("width","650")
                .attr("height","300");
            let gs=svg.append("g")
                .selectAll(".con")
                .data(this.stu)
                .enter()
                .append("g")
                .attr("transform",function(d,i){
                    return "translate("+(parseInt(i/14)*180+100)+","+((i%14)*17+20)+")";
                });
            gs.append("rect")
                .attr("x","0")
                .attr("y","0")
                .attr("width",function(d,i){
                    return x(d.male+d.female)
                })
                .attr("height","15")
                .attr("fill",me.color1[0])
                .attr("fill-opacity","0.4")
                .style("cursor","pointer")
                .on("click",function(){
                    gs.selectAll("rect")
                        .transition()
                        .duration(500)
                        .attr("width","0")
                        .remove();
                    //点击事件
                    me.addfun(gs,x,me);
                });
            gs.append("rect")
                .attr("x","0")
                .attr("y","0")
                .attr("width",function(d,i){
                    return x(d.rate[0]+d.rate[1]);
                })
                .attr("height","15")
                .attr("fill",me.color1[0])
                .attr("fill-opacity","1");
            //图例
            svg.append("text")
                .text("日平均消费人数")
                .attr("transform","translate(0,13)")
                .attr("style","font-size:15px")
            svg.selectAll(".t")
                .data(this.stu)
                .enter()
                .append("text")
                .text(function(d,i){
                    return d.major;
                })
                .attr("transform",function(d,i){
                    //return "translate("+0+","+(i*17+33)+")";
                    return "translate("+(parseInt(i/14)*190)+","+((i%14)*17+33)+")";
                })
                .on("mouseover",function(){
                    d3.select(this)
                        .attr("style","fill:red")
                })
                .on("mouseout",function(){
                    d3.select(this)
                        .attr("style","fill:black")
                })
                .on("click",function(){
                    //
                })
        },
        addfun:function(gs,x,me){
            gs.append("rect")
                .attr("x","0")
                .attr("y","0")
                .transition()
                .duration(800)
                .attr("width",function(d,i){
                    return x(d.male)
                })
                .attr("height","15")
                .attr("fill",me.color1[0])
                .attr("fill-opacity","0.4")
                .style("cursor","pointer");
                
            
            gs.append("rect")
                .attr("x",function(d,i){
                    return x(d.male)+5;
                })
                .attr("y","0")
                .transition()
                .duration(800)
                .attr("width",function(d,i){
                    return x(d.female);
                })
                .attr("height","15")
                .attr("fill",me.color1[1])
                .attr("fill-opacity","0.4")
                .style("cursor","pointer");
            gs.append("rect")
                .attr("x","0")
                .attr("y","0")
                .transition()
                .duration(800)
                .attr("width",function(d,i){
                    return x(d.rate[0])
                })
                .attr("height","15")
                .attr("fill",me.color1[0]);
            gs.append("rect")
                .attr("x",function(d,i){
                    return x(d.male)+5;
                })
                .attr("y","0")
                .transition()
                .duration(800)
                .attr("width",function(d,i){
                    return x(d.rate[1])
                })
                .attr("height","15")
                .attr("fill",me.color1[1]);
        }
    },
    created() {},
    mounted() {
        this.getData();
    }
};
</script>
<style>
#bar{
    width: 250px;
    height: 300px;
    position: absolute;
    top: calc(2% + 580px);
    left: 1%;
    z-index: 2;
    
}
#bar text{
    font-weight: 100;
    font-size: 12px ;
    cursor: pointer;
}
</style>