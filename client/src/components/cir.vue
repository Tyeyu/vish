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
            data:[0, 0, 0, 0, 0, 0, 28, 1931, 134, 438, 111, 2402, 1155, 47, 62, 22, 490, 662, 1250, 262, 47, 204, 66, 9]
        };
    },
    computed: {},
    watch: {},
    methods: {
        draw:function(){
            let pie=d3.pie().value(d=>{
                return 1
            })(this.data);
            var arc_generator = d3.arc()
                .innerRadius(20)
                .outerRadius(function(d){
                    return d.data/31+20;
                });
            const svg=d3.select("#cir")
                .append("svg")
                .attr("width","200")
                .attr("height","200");
            let gs=svg.append("g")
                .attr("transform","translate(100,100)")
                .selectAll("g")
                .data(pie)
                .enter()
                .append("path")
                .attr("d",function(d){
                    return arc_generator(d);
                })
                .attr("fill",function(d,i){
                    return d3.schemePaired[parseInt(i%12)];
                })
                .on("mouseover",function(d){
                    d3.select(this).attr("fill","none");
                })
                .on("mouseout",function(d,i){
                    d3.select(this).attr("fill",d3.schemePaired[parseInt(i%12)]);
                })
        },
    },
    created() {},
    mounted() {
        this.draw();
    }
};
</script>
<style scoped>
#cir {
    position: absolute;
    top: 50%;
    left: 40%;
    width: 200px;
    height: 200px;
    border:1px solid black;
}
</style>