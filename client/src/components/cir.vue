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
            color1:['#5e7987','#ea517f','#b598a1','#c8adc4','#1772b4','#96c24e','#e2e1e4','#2983bb','#45b787','#e2d849','#fcc515','#806332']
        };
    },
    computed: {},
    watch: {},
    methods: {
        draw:function(){
            for(let j in this.data){
                let pie=d3.pie().value(d=>{
                    return 1
                })(this.data[j]);
                var arc_generator = d3.arc()
                    .innerRadius(0)
                    .outerRadius(function(d){
                        return Math.sqrt(d.data);
                    });
                let svg=d3.select("#cir")
                    .append("svg")
                    .attr("width","90")
                    .attr("height","90");
                let gs=svg.append("g")
                    .attr("transform","translate(40,40)")
                    .selectAll("g")
                    .data(pie)
                    .enter()
                    .append("path")
                    .attr("d",function(d){
                        return arc_generator(d);
                    })
                    .attr("fill",function(d,i){
                        return d3.schemePaired[i%12];
                    })
                    .on("mouseover",function(d,i){
                        let pos=d3.mouse(this);
                        d3.select(this.parentNode).append("text")
                            .text(i+"点:"+d.data)
                            .attr("transform","translate("+pos[0]+","+pos[1]+")")
                            .attr("class","tips");
                    })
                    .on("mouseout",function(d,i){
                        d3.select(this.parentNode).selectAll(".tips").remove();
                    })
                svg.append("text")
                    .text(j)
                    .attr("transform","translate(16,15)")
                    .attr("fill","white")
            }
        },
    },
    created() {},
    mounted() {
        this.axios.get('static/day.json').then(result=>{
            this.data=result.data;
            this.draw();
        })
        
    }
};
</script>
<style >
#cir{
    width: 720px;
}
#cir svg{
    border:1px solid white;
}
#cir text{
    fill: antiquewhite;
    font-size: 12px;
}
.tips{
    fill: azure
}
#cir svg{
    float: left;
}
</style>