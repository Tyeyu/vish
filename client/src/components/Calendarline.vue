<template>
    <div id="calendarline">
    </div>
</template>
<script>
import $ from 'jquery'
import * as d3 from 'd3';
import echarts from "echarts";
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
                    that.$store.commit("selectdaydata_state",result);
                    that.drawline(result);
                }
               
            })
        },
        drawline:function(data){
            // console.log(data)
            var width=950,height=300,left=30;
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
            var addressMap1=d3.map();;
            for(var i=0;i<addressnets.length;i++){
                       if(lenkeys.get(addressnets[i].key)==1){
                           addressMap.set(addressnets[i].key,addressnets[i].values)
                           addressMap1.set(addressnets[i].key,addressnets[i].values)
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
                .on("click",function(d){
                    var arry=addressMap1.get(d);
                    var nets=d3.nest()
                        .key(function(d) { return d.major; })
                        .entries(arry);
                    var edata={ 
                        legendData: [],
                        seriesData:[],
                        selected:[]};
                    var ek=0;
                    var s=[];
                    var sk=0;
                    var amap=d3.map();
                    for(var i=0;i<nets.length;i++){
                        s[sk]={name:nets[i].key,value:nets[i].values.length};
                        sk++;
                        amap.set(nets[i].key,nets[i].values);
                    }
                    s.sort( (a,b)=>{
                             return a.value < b.value ? 1 : a.value >b.value ? -1 : 0
                        })
                    var sex=[];
                    for(var i=0;i<s.length;i++){
                        edata.legendData[ek]=s[i].name;
                        edata.seriesData[ek]={name:s[i].name,value:s[i].value};
                        
                        edata.selected[s[i].name]=true;
                        var nan=0;
                        var nv=0;
                        var values=amap.get(s[i].name);
                        for(var j=0;j<values.length;j++){
                            if(values[j].sex=="男"){
                                nan++;
                            }
                            else{
                                nv++;
                            }
                        }
                        sex[ek]={product:s[i].name,"男":nan,"女":nv};
                        ek++;

                        if(i>=5){
                           break;
                        }
                    }
                    that.edraw(edata,d);
                    that.ebardraw(sex,d);
                    // console.log(edata);
                })
                .append("title")
                .text(function(d){
                    return d;
                });
        },
        edraw:function(data,title){
            d3.selectAll("#ec").remove();
            var dom=d3.select("#app").append("div").attr("id","ec").style("width","500px").style("height","260px");
            var dom1=document.getElementById("ec");
            var myChart = echarts.init(dom1);
            var option = {
                title : {
                text: title+"各专业人流量",
                // subtext: '纯属虚构',
                x:'center',
                textStyle:{
                //文字颜色
                    color:'white',
                    }
            },
            tooltip : 
            {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right: 10,
                    top: 20,
                    bottom: 20,
                    data: data.legendData,
                    selected: data.selected,
                    textStyle:{
                        color:'white'
                    }
                },
                series : 
                [
                    {
                        name: '专业',
                        type: 'pie',
                        radius : '55%',
                        center: ['40%', '50%'],
                        data: data.seriesData,
                        itemStyle: {
                        emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            myChart.setOption(option, true);
        },
        ebardraw:function(data,title){
            d3.selectAll("#ebar").remove();
            var dom=d3.select("#app").append("div").attr("id","ebar").style("width","500px").style("height","260px");
            var dom1=document.getElementById("ebar");
            var myChart = echarts.init(dom1);
            var myChart = echarts.init(dom1);
            var option = {
                color:["#1E90FF","Tomato"],
                legend: {
                    right: 10,
                    top: 20,
                    textStyle:{
                        color:'white'
                    }
                },
                title : {
                    text: title+"各专业不同性别人流量",
                    // subtext: '纯属虚构',
                    x:'center',
                    textStyle:{
                    //文字颜色
                        color:'white',
                    }
                },
                tooltip: {},
                dataset: {
                    dimensions: ['product', '男', '女'],
                    source: data
                    },
                xAxis: {
                    type: 'category',
                    axisLine: {
                    lineStyle: {
                    color: 'white'
                     }
                    },
                },
                yAxis: {
                    axisLine: {
                    lineStyle: {
                    color: 'white'
                        }
                    }
                },
                // Declare several bar series, each will be mapped
                // to a column of dataset.source by default.
                series: [
                    {type: 'bar'},
                    {type: 'bar'}
                    ]
                };
            myChart.setOption(option, true);
        }
    },
     mounted() {
    //   this.drawCalendar()
    //   this.edraw();
    },
    computed: {
        CaClickdate () {
            return this.$store.getters.getCaClickdate;
        },
        CaClicktable () {
            return this.$store.getters.getCaClicktable;
        },
        Calegend(){
            return this.$store.state.forcelegend;
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
           console.log(newval)
           if(this.$store.getters.getAllflow&&this.$store.getters.getCaClickdate!=null){
               if(newval!=null){
                   d3.select("#calendarline").selectAll("svg").remove();
                   
                    this.drawline(this.$store.getters.getselectdaydata);
               }
           }
       }
    }
}
</script>
<style>
#ebar{
    position: absolute;
    left:calc(6% + 1070px);
    top: calc(2% + 300px);
}
#ec{
    position: absolute;
    left:calc(6% + 550px);
    top: calc(2% + 300px);
}
#calendarline{
    position: absolute;
    left:calc(6% + 560px);
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