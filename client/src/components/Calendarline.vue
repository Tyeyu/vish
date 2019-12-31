<template>
    <div id="calendarline">
    </div>
</template>
<script>
import $ from 'jquery'
import * as d3 from 'd3';
export default {
    methods:{
         CalendarStudentdata:function(){
            let that=this;
            var datas={
            'date':that.$store.getters.getCaClickdate+" ",
            'table':that.$store.getters.getCaClicktable
            }
            datas=JSON.stringify(datas);
            var x=$.ajax({
                type: "post",
                url: "http://localhost/CalenStudent",
                dataType:"json",
                data: datas,
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    // console.log(result)
                    that.commit("selectdaydata_state",result);
                    that.drawline(result);
                }
               
            })
        },
        drawline:function(data){
            var width=550,height=300,left=30;
            let that=this;
            
            var lenkeys=this.$store.getters.getforcelegend;
            var addressnets=d3.nest()
                        .key(function(d) { return d.address; })
                        .entries(data)
           
            var timescale = d3.scaleTime().domain([d3.min(data,function(d){
                return new Date(d.date);
            }),d3.max(data,function(d){
                return new Date(d.date);
            })]).range([0,(width-left)])
            var  addressMap=d3.map();
            for(var i=0;i<addressnets.length;i++){
                       if(lenkeys.get(addressnets[i].key)==1){
                           addressMap.set(addressnets[i].key,addressnets[i].values)
                       } 
                       
                }
            var address=addressMap.keys();
           
            for(var i=0;i<address.length;i++){
                var ad=d3.nest()
                        .key(function(d) { return d.date; })
                        .entries(addressMap.get(address[i]))
                var admap=d3.map();
                for(var j=0;j<ad.length;j++){
                        admap.set(ad[j].key,ad[j].values)
                }
                addressMap.set(address[i],admap)
            }
            // console.log(linedatas);
            
            var yscale=d3.scaleLinear().domain([0,d3.max(address,function(d){
               
                var smap=addressMap.get(d);
                return d3.max(addressMap.get(d).keys(),function(d){
                  
                    return smap.get(d).length;
                });
            })])
            .range([(height-left),0])

            var colorScale=this.$store.getters.getforceColorScale;
            // console.log(addressMap)
            var svg = d3
                .select("#calendarline").attr("width",550).attr("height",300)
                .append("svg")
                .attr("width", width)
                .attr("height", height);
            var xAxis = d3.axisBottom(timescale)
                .ticks(5)
                .tickFormat((d)=>{
                    return d3.timeFormat("%H:%M:%S")(d);
                });
            var yAxis=d3.axisLeft(yscale);
            //坐标轴
            svg.append("g")
                .call(xAxis)
                .attr("transform", "translate(25,275)")
                .attr("class","axis");
            svg.append("g")
                .call(yAxis)
                .attr("transform", "translate(25,2)")
                .attr("class","axis");
            var line=d3.line()
                .x(function(d){
                    return timescale(new Date(d.date));
                })
                .y(function(d){
                    return yscale(d.value)
                });
            var lines=svg.append("g").selectAll("g").data(address).enter().append("g");
                lines
                .attr("class","line")
                .append("path")
                .attr("fill","none")
                .style("stroke",d => colorScale(d))
                .attr("stroke-width", 1.2)
                .style("opacity",1)
                .attr("transform", "translate(25,2)")
                .attr("d",function(d){
                    var linedatas=[];
                    var k=0;
                    var keys=addressMap.get(d).keys();
                    for(var i=0;i<keys.length;i++){
                         linedatas[k]={
                            date:keys[i],
                            value:addressMap.get(d).get(keys[i]).length
                        }
                        k++;
                    }
                    linedatas.sort(
                        (a,b)=>{
                             return (new Date(a.date) < new Date(b.date)) ? -1 : (new Date(a.date) >new Date(b.date)) ? 1 : 0
                        }
                    );
                    // console.log(linedatas)
                    return line(linedatas)
                })
                .on("mouseover",function(d){
                    var s=d;
                    d3.select("#calendarline").selectAll(".line").style("opacity",function(d){
                        if(d!=s)
                        return 0.1
                    });
                    // d3.select(this).style("opacity",1);
                })
                .on("mouseout",function(d){
                     d3.select("#calendarline").selectAll(".line").style("opacity",1);
                })
                .append("title")
                .text(function(d){
                    return d;
                });
        }
    },
    computed: {
        CaClickdate () {
            return this.$store.getters.getCaClickdate;
        },
        CaClicktable () {
            return this.$store.getters.getCaClicktable;
        },
        Calegend(){
            return this.$store.getters.getforcelegend;
        }
    },
    watch:{
         CaClickdate:function(newdata,olddata){
          if(newdata!=null)
          {
              d3.select("#calendarline").selectAll("svg").remove();
              this.CalendarStudentdata();
          }
         
       },
       Calegend:function(newval,oldval){
           if(this.$store.getters.getAllflow&&this.$store.getters.getCaClickdate!=null){
               if(newval!=null){

               }
           }
       }
    }
}
</script>
<style>
#calendarline{
    position: absolute;
    left:calc(13% + 560px);
    top: 1%;
}
#calendarline .axis path,
#calendarline .axis line {
	fill: none;
	stroke: white;
	shape-rendering: crispEdges;
	}
			
#calendarline .axis text {
		font-family: sans-serif;
		font-size: 11px;
	}
#calendarline .tick line,
#calendarline .tick text{
    fill:none ;
    stroke:aliceblue;
}
</style>