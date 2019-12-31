<template>
    <div id="cir"></div>
</template>

<script>
import $ from "jquery";
import * as d3 from "d3";
export default {
    components: {},
    data() {
        return {
            color1:["#FFFFCC", "#FFCCCC", "#CCCCFF", "#CCFF99", 
            "#6699CC", "#66CCCC", "#99CC33", "#993366", 
            "#CC9999", "#CC6600", "#FF6666", "#FF0033", 
            "#666666", "#0099FF", "#669999", "#666699", 
            "#FF9933", "#0ad811", "#bbd9fd", "#fe6cfe", 
            "#297192", "#d1a09c", "#78579e", "#81ffad", 
            "#739400", "#ca6949", "#d9bf01", "#646a58", 
            "#d5097e", "#bb73a9", "#ccf6e9", "#9cb4b6", 
            "#b6a7d4", "#9e8c62", "#6e83c8", "#01af64",
             "#a71afd", "#cfe589", "#d4ccd1","#fd4109"]
            };
    },
    computed: {},
    watch: {},
    methods: {
        draw1:function(){
            const me=this;
            var days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
            const svg=d3.select("#cir")
                .append("svg")
                .attr("width","500")
                .attr("height","600");
            svg.append("g")
                .selectAll("text")
                .data(days)
                .enter()
                .append("text")
                    .attr("font","10px Microsoft YaHei")
                    .attr("fill","antiquewhite")
                    .text(function(d){
                        return d;
                    })
                    .attr("transform",function(d,i){
                        return "translate(30,"+(i*80+45)+")"
                    });
            
            //日历
            let x=0,y=1;
            for(let j in this.data){
                let pie=d3.pie().value(d=>{
                    return 1
                })(this.data[j]);
                var arc_generator = d3.arc()
                    .innerRadius(0)
                    .outerRadius(function(d){
                        return Math.sqrt(d.data);
                    });
                let sg=svg.append("g")
                    .attr("transform",function(){
                        return "translate("+(x*80+80)+","+(y*80)+")";
                    })
                    .append("svg")
                    .attr("viewbox","0,0,80,80")
                    .attr("width","80")
                    .attr("height","80")
                y=y+1;
                if(y>=7){
                    y=0;
                    x+=1;
                }
                
                let gs=sg.append("g")
                    .attr("transform","translate(40,40)");
                gs.selectAll("g")
                    .data(pie)
                    .enter()
                    .append("path")
                    .attr("d",function(d){
                        return arc_generator(d);
                    })
                    .attr("fill",function(d,i){
                        return d3.schemePaired[i%12];
                    });
                sg.append("text")
                    .text(j)
                    .attr("transform","translate(0,15)")
                    .attr("font-size","12px")
                    .attr("fill","antiquewhite")
            }
            
        },
        draw:function(){
            
            
        },
    },
    created() {},
    mounted() {
        // this.axios.get('static/day.json').then(result=>{
        //     this.data=result.data;
        //     this.draw1();
        // })
        
    },
     computed: {
       STSpend () {
            return this.$store.getters.getSTspend;
        }
    },
    watch:{
         STSpend:function(newval,oldval){
             if(newval){
                d3.select("#calendar").selectAll("svg").remove();
                d3.select("#cir").selectAll("svg").remove();
                this.axios.get('static/day.json').then(result=>{
                this.data=result.data;
                this.draw1();
                })
             }
            
         }
    }
};
</script>
<style >
#cir{
    width: 500px;
    height: 650px;
    position: absolute;
    left: 220px;
    top: 1%;
}
#cir svg{
}
</style>