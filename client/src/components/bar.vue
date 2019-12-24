<template>
    <div id="bar"></div>
</template>

<script>
/* aa */
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
            color1:["#2775b6","#f26b1f"]
        };
    },
    computed: {},
    watch: {},
    methods: {
        getData:function(){
            this.axios.get("static/stu_info.json").then((data)=>{
                this.stu=data.data.data;
                this.draw2();
            })
        },
        draw2:function(){
            const me = this;
            const x = d3.scaleLinear()
                .domain([0,200])
                .range([0, 150]);
            const svg=d3.select("#bar")
                .append("svg")
                .attr("width","250")
                .attr("height","700");
            let gs=svg.selectAll(".con")
                .data(this.stu)
                .enter()
                .append("g")
                .attr("transform","translate(100,0)")
            gs.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d.male+d.female)
                })
                .attr("height","15")
                .attr("fill","#2775b6")
                .attr("fill-opacity","0.4")
                .style("cursor","pointer")
                .on("click",function(){
                    gs.selectAll("rect")
                        .transition()
                        .duration(500)
                        .attr("width","0")
                        .remove();
                    gs.append("rect")
                        .attr("x","0")
                        .attr("y",function(d,i){
                            return 17*i;
                        })
                        .transition()
                        .duration(800)
                        .attr("width",function(d,i){
                            return x(d.male)
                        })
                        .attr("height","15")
                        .attr("fill","#2775b6")
                        .attr("fill-opacity","0.4")
                        .style("cursor","pointer");
                    gs.append("rect")
                        .attr("x",function(d,i){
                            return x(d.male)+5;
                        })
                        .attr("y",function(d,i){
                            return 17*i;
                        })
                        .transition()
                        .duration(800)
                        .attr("width",function(d,i){
                            return x(d.female);
                        })
                        .attr("height","15")
                        .attr("fill","#f26b1f")
                        .attr("fill-opacity","0.4")
                        .style("cursor","pointer");
                    gs.append("rect")
                        .attr("x","0")
                        .attr("y",function(d,i){
                            return 17*i;
                        })
                        .transition()
                        .duration(800)
                        .attr("width",function(d,i){
                            return x(d.arr[0])
                        })
                        .attr("height","15")
                        .attr("fill","#2775b6")
                        .style("cursor","pointer");
                    gs.append("rect")
                        .attr("x",function(d,i){
                            return x(d.male)+5;
                        })
                        .attr("y",function(d,i){
                            return 17*i;
                        })
                        .transition()
                        .duration(800)
                        .attr("width",function(d,i){
                            return x(d.arr[1])
                        })
                        .attr("height","15")
                        .attr("fill","#f26b1f")
                        .style("cursor","pointer");
                });
            gs.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d.arr[0]+d.arr[1])
                })
                .attr("height","15")
                .attr("fill","#2775b6");
            svg.selectAll(".t")
                .data(this.stu)
                .enter()
                .append("text")
                .text(function(d,i){
                    return d.major;
                })
                .attr("transform",function(d,i){
                    return "translate("+0+","+(i*17+13)+")";
                })
                .style("font-size","12px")
        },
        draw1:function(index){
            const me=this;
            const svg=d3.select("#bar")
                .append("svg")
                .attr("width","150")
                .attr("height","700");
            const x = d3.scaleLinear()
                .domain([0,200])
                .range([0, 150]);
            let bars=svg.append("g")
                .selectAll("g")
                .data(this.stu)
                .enter()
                .append("g");

            bars.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d[Object.keys(d)[0]][2])
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[0];
                })
                .attr("fill-opacity","0.4");
            bars.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(me.num[Object.keys(d)[0]][index])
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[0];
                })
                .on("mouseover",function(d){
                    console.log(Object.keys(d)[0])
                })
        },
        draw:function(){
            const svg=d3.select("#bar")
                .append("svg")
                .attr("width","200")
                .attr("height","700");
            const x = d3.scaleLinear()
                .domain([0,120])
                .range([0, 100]);
            let bars=svg.append("g")
                .selectAll("g")
                .data(this.stu)
                .enter()
                .append("g");

            bars.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d[Object.keys(d)[0]][0])
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[0];
                })
                .attr("fill-opacity","0.4");
            bars.append("rect")
                .attr("x","0")
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d[Object.keys(d)[0]][0])/3
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[0];
                })
            bars.append("rect")
                .attr("x",function(d,i){
                    return x(d[Object.keys(d)[0]][0])+10;
                })
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d[Object.keys(d)[0]][1])
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[1];
                })
                .attr("fill-opacity","0.4");
            bars.append("rect")
                .attr("x",function(d,i){
                    return x(d[Object.keys(d)[0]][0])+10;
                })
                .attr("y",function(d,i){
                    return 17*i;
                })
                .attr("width",function(d,i){
                    return x(d[Object.keys(d)[0]][1])/2
                })
                .attr("height","15")
                .attr("fill",(d,i)=>{
                    return this.color1[1];
                });
        }
    },
    created() {},
    mounted() {
        this.getData();
    }
};
</script>
<style scoped>
#bar{
    width: 620px;
    height: 700px;
    margin: 10px;
}
</style>