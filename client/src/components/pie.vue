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
    computed: {
        selectedMajor () {
            return this.$store.getters.getSelectedMajor;
        }
    },
    watch: {
         selectedMajor:function(newval,oldval){
             if(newval){
                 d3.select("#pie").selectAll("svg").remove();
                this.draw(this.data[newval]);
             }
            
         }
    },
    methods: {
        getData:function(){
            this.axios.get("static/depconsume.json").then((data)=>{
                this.data=data.data;
                this.initData();
            })
        },
        initData:function(){
            //对数据进行处理 
            this.draw(this.data["18软件技术"]);
        }
        ,
        draw:function(data){
            //画饼图 
             const svg = d3
                .select("#pie")
                .append("svg")
                .attr("width", 250)
                .attr("height", 250);
            let pie=d3.pie().value(d=>{
                    return d.money
                })(data);
            var arc_generator = d3.arc()
                .innerRadius(50)
                .outerRadius(80);
            let gs=svg.append("g")
                .attr("transform","translate(80,150)");
            let san=gs.selectAll("g")
                .data(pie)
                .enter()
                .append("path")
                .attr("d",function(d){
                    return arc_generator(d);
                })
                .attr("fill",function(d,i){
                    return d3.schemePaired[i%12];
                })
                .attr("style","cursor:pointer");
            san.append("title")
                .text(function(d,i){return d.data.Dept+":"+d.data.money})
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
                        return "translate("+175+","+(i*14+40)+")"
                    })
                    .attr("fill",function(d,i){
                        return d3.schemePaired[i%12];
                    });
            svg.append("text")
                .text(this.selectedMajor)
                .attr("transform","translate(0,13)")
                .attr("style","font-weight:100")
                .attr("style","font-size:15px")
                .attr("fill","antiquewhite");
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
    width: 250px;
    height: 250px;
    position: absolute;
    left: calc(2% + 610px);
    top: calc(2% + 585px);
    z-index: 2;
}
</style>