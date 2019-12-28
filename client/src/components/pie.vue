<template>
    <div id="pie"></div>
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
           
        };
    },
    computed: {},
    watch: {},
    methods: {
        getData:function(){
            this.axios.get("static/depconsume.json").then((data)=>{
                this.data=data.data;
                this.initData();
            })
        },
        initData:function(){
            //对数据进行处理 
            this.draw(this.data["18软件技术"])
        }
        ,
        draw:function(data){
            //画饼图 
             const svg = d3
                .select("#pie")
                .append("svg")
                .attr("width", 300)
                .attr("height", 300);
            let pie=d3.pie().value(d=>{
                    return d.money
                })(data);
            var arc_generator = d3.arc()
                .innerRadius(70)
                .outerRadius(100);
            let gs=svg.append("g")
                .attr("transform","translate(110,150)");
            gs.selectAll("g")
                .data(pie)
                .enter()
                .append("path")
                .attr("d",function(d){
                    return arc_generator(d);
                })
                .attr("fill",function(d,i){
                    return d3.schemePaired[i%12];
                })
                .attr("style","cursor:pointer")
                .on("mousemove",function(d){
                    console.log(d)
                })
                .on("mouseleave",function(){
                    d3.selectAll(".hl").remove();
                });
            //图例 
            svg.append("g")
                .selectAll("text")
                .data(data.slice(0,10))
                .enter()
                .append("text")
                    .text((d)=>{
                        return d.Dept;
                    })
                    .attr("style","font-size:11px")
                    .attr("transform",function(d,i){
                        return "translate("+220+","+(i*14+20)+")"
                    })
                    .attr("fill",function(d,i){
                        return d3.schemePaired[i%12];
                    });
            svg.append("text")
                .text("18软件技术")
                .attr("transform","translate(10,30)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
        }
    },
    created() {},
    mounted() {
        this.getData();
    }
};
</script>
<style>
#pie{
    width: 300px;
    height: 300px;
    position: absolute;
    left: 800px;
}
</style>