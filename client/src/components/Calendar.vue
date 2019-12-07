<template>
<div>
<div id="calendar"></div>
</div>

</template>
<script>
import $ from 'jquery'
import * as d3 from 'd3';
var cellSize=80;
let width=500,height=650;
export default {
    name:"calendar",
    methods:{
        CalendarAllNodedata:function(){
            var datas={
            'date':"2019"
            }
            datas=JSON.stringify(datas);
            let that=this;
            var x=$.ajax({
                type: "post",
                url: "http://localhost/CalenAll",
                dataType:"json",
                data: datas,
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    that.drawCalendar(result)
                }
               
            })
      
        },
        drawCalendar:function(drawdata){
            let that=this;
            
            var datenets=d3.nest()
                        .key(function(d) { return d.date; })
                        .entries(drawdata)
            var datesMap= d3.map();
            for(var i=0;i<datenets.length;i++){
                        datesMap.set(datenets[i].key,datenets[i].values)
                }
            var addressNets=d3.nest()
                        .key(function(d) { return d.address; })
                        .entries(drawdata)
          
            let addressArry=['学生']
            let addranges=[0]
            for(var i=0;i<addressNets.length;i++){
                addressArry[i+1]=addressNets[i].key
                // addranges[i+1]=(i+1)/addressNets.length
                addranges[i+1]=i+1
            }
            var scaleOrdinal = d3.scaleOrdinal()
                                .domain(addressArry)
                                .range(addranges);
            let computes=[]
            var colors=["#FFFFCC", "#FFCCCC", "#CCCCFF", "#CCFF99", 
            "#6699CC", "#66CCCC", "#99CC33", "#993366", 
            "#CC9999", "#CC6600", "#FF6666", "#FF0033", 
            "#666666", "#0099FF", "#669999", "#666699", 
            "#FF9933", "#0ad811", "#bbd9fd", "#fe6cfe", 
            "#297192", "#d1a09c", "#78579e", "#81ffad", 
            "#739400", "#ca6949", "#d9bf01", "#646a58", 
            "#d5097e", "#bb73a9", "#ccf6e9", "#9cb4b6", 
            "#b6a7d4", "#9e8c62", "#6e83c8", "#01af64",
             "#a71afd", "#cfe589", "#d4ccd1","#fd4109"];
            var index=0;
          
            var colorscale=function(d){
                var value=scaleOrdinal(d)
               
                return colors[value]
                
            }
            var edgescale=d3.scaleLinear()
            .domain([d3.min(drawdata,function(d){
                return parseInt(d.count)
            }), d3.max(drawdata,function(d){
                return parseInt(d.count)
            })])
            .range([0, 1])
          
            var days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
            var svg = d3
                .select("#calendar")
                .append("svg")
                .attr("width",width)
                .attr("height", height);
              //添加星期
            svg.append("g")
            .selectAll("text")
            .data(days)
            .enter()
            .append("text")
            .style("fill", "rgb(170,170,170)")
            .attr("transform", function(d, i) {
                if (i <= 6) {
                return "translate(30," + (cellSize * 3.5 + cellSize / 1.5) + ")";
                }
                })

            .attr("font", "10px Microsoft YaHei")
            .attr("fill", "white")
            .attr("y", function(d, i) {
                switch (i) {
                case 0:
                    return -4 * cellSize*0.9 ;
                case 1:
                    return -3 * cellSize*0.85  ;
                case 2:
                    return -2* cellSize *0.8 ;
                case 3:
                    return -1*cellSize*0.6 ;
                case 4:
                    return 1 * cellSize*0.5 ;
                case 5:
                    return 1.5 * cellSize ;
                case 6:
                    return 2.5 * cellSize ;
                }
                })
            .text(function(d) {
                    return d;
                });
            // 勾勒月份的分割线
            svg
                .append("g")
                .attr("fill", "none")
                .attr("stroke", "white")
                .selectAll("path")
                .data(function(d) {
                    return d3.timeMonths(new Date(2019, 3, 1), new Date(2019, 4, 1));
                })
                .enter()
                .append("path")
                .attr(
                    "transform",
                    "translate(" + -cellSize*12 + "," + 0+ ")"
                        )
                .attr("d", pathMonth);
            var rect = svg
                .append("g")
                .attr("class", "rects")
                .attr("fill", "none")
                .attr("stroke", "#ccc")
                .attr(
                        "transform",
                        "translate(" + cellSize * -12 + "," + 0 + ")"
                    )
                .selectAll("rect")
        //计算一组小方格的数量，调用d3的timeDays方法，获取两个时间之间的天数，例如，计算从1999年的第一天到2000年的第一天,则参数为new Date(1999,0,1)到 new Date(2000,0,1)，timeDays返回天序列
                .data(function(d) {
                     return d3.timeDays(new Date(2019, 3, 1), new Date(2019, 4, 1));
                     })
                .enter()
                .append("rect")
                .attr("width", cellSize )
                .attr("height", cellSize)
                // 返回一年有多少个周，确定一组小方格的横向位置
                .attr("x", function(d) {
                            return (d3.timeWeek.count(d3.timeYear(d), d) * cellSize);
                        })
                // 返回天，确定一组小方格的纵向位置
                .attr("y", function(d) {
                             return d.getDay() * cellSize;
                })
                .on("click", function(d) {
                    })
                .attr("fill","#2b2c2c")
                .attr("opacity",0.3)
                .datum(d3.timeFormat("%Y/%m/%d"));
            rect.append("title").text(function(d){
                return d;
            })     
            rect
               .attr("width",function(d){
                   var sd=new Date(d)
                   var s=sd.getFullYear()+"/"+(sd.getMonth()+1)+"/"+sd.getDate();
                   var thisdate=datesMap.get(s)
                   var nodes=[{id:"学生",group:2}]
                   var linkes=[{source:"学生",target:"学生",value:1}]
                   for(var i=0;i<thisdate.length;i++){
                       nodes[i+1]={id:thisdate[i].address,group:1};
                       linkes[i]={source:"学生",target:thisdate[i].address,value:parseInt(thisdate[i].count)}
                   }
                   var fonfig={
                        svg:d3.select("#calendar"),
                        nodes:nodes,
                        linkes:linkes,
                        colorscale:colorscale,
                        edgescale:edgescale,
                        position:{x:(d3.timeWeek.count(d3.timeYear(sd), sd) * cellSize),y:sd.getDay() * cellSize-cellSize},
                        width:70,
                        height:70,
                        transform: "translate(" + cellSize + "," + 0 + ")",
                        class:'allnode'
                   }
                   that.drawForce(fonfig);
                   return cellSize;
               })
            var Dates=new Date(2019,4,1)
            var Dates2=new Date(2019,3,27)
            var legend = svg
                    .append("g")
                    .attr("class", "legend")
                    .attr("fill", "none")
                    .attr("stroke", "white");
            var checknum=0;
            var legendcircles = legend
                    .selectAll("circle")
                    .data(addressArry)
                    .enter()
                    .append("circle")
                    .attr(
                        "transform",
                        "translate(" + cellSize * -12 + "," + 0 + ")"
                    )
                    .attr("r",5)
                    .attr("cx", function(d,i) {
                        var x=d3.timeWeek.count(d3.timeYear(Dates), Dates) * cellSize+cellSize/5;
                        var checky=Dates2.getDay()* cellSize+cellSize
                        var nowy=Dates.getDay() * cellSize+(i+1)*cellSize/5;
                        if(nowy>checky)
                           
                           x=x+cellSize/2
                        return x;
                        })
                    .attr("cy", function(d,i) {
                        var y=Dates.getDay() * cellSize+(i+1)*cellSize/5;
                        var checky=Dates2.getDay()* cellSize+cellSize;
                        if(y>checky)
                            y-=4*cellSize
                        return y;
                    })
                    .attr("fill", function(d) {
                        if(d=="学生")
                            return "white"
                        return colorscale(d)
                    }).append("title").text(d=>{return d})
        },
        /*config={
            svg://绘图区域
            nodes://节点数据
            linkes：//节点间的关系数据
            colorscale://颜色比例尺
            edgescale://边大小比例尺
            position://位置
        }
        */
        drawForce:function(config){
            if(config==null)
                return ;
            var simulation=null;
            var started=function(d){
                if(!d3.event.active){
                    simulation.alphaTarget(0.5).restart();
                }
                d.fx=d.x;
                d.fy=d.y;
            }
            var dragged=function(d){
                d.fx=d3.event.x;
                d.fy=d3.event.y;
            }
            var ended=function(d){
                if(!d3.event.active){
                    simulation.alphaTarget(0);
                }
                d.fx=null;
                d.fy=null;
            }
            const nodes=config.nodes;
            const links=config.linkes;
            simulation = d3.forceSimulation(nodes)
    		                    .force("link",d3.forceLink(links).id(function(d){
                                    return d.id
                                }).distance(10))
    		                    .force("charge",d3.forceManyBody())
                                .force("center",d3.forceCenter(35,35));
            
            // forceSimulation.nodes(config.nodes)
            // .on("tick",ticked);
           var svg = config.svg.append("svg").attr("class",config.class).style("position","absolute")
                .attr("viewBox", [0, 0, config.width, config.height])
                .style("left",config.position.x-cellSize*11.95)
                .style("top",config.position.y+cellSize*1.75)
                .attr("width",config.width)
                .attr("height",config.height);
           var link= svg.append("g")
                        .attr("stroke", "#999")
                        .attr("stroke-opacity", 0.8)
                        .selectAll("line")
                        .data(config.linkes)
                        .join("line")
                        .attr("stroke-width", function(d){
                           
                            return config.edgescale(d.value)*20
                        });
            var node =svg.append("g")
                        .attr("stroke", "#fff")
                        .attr("stroke-width", 1.5)
                        .selectAll("circle")
                        .data(nodes)
                        .join("circle")
                        .attr("r", 3)
                        .attr("fill", function(d){
                            if(d.id=="学生")
                            {
                                return "white";
                            }
                            else{
                                return config.colorscale(d.id);
                            }
                        })
                        .call(d3.drag()
                            .on("start", started)
                            .on("drag", dragged)
                            .on("end", ended));
            simulation.on("tick", () => {
                        link
                            .attr("x1", d => d.source.x)
                            .attr("y1", d => d.source.y)
                            .attr("x2", d => d.target.x)
                            .attr("y2", d => d.target.y);

                        node
                            .attr("cx", d => d.x)
                        .attr("cy", d => d.y);
                    });
        }
    },
    mounted() {
    //   this.drawCalendar()
      this.CalendarAllNodedata()
    }

}
// 定义月份分割线路径
function pathMonth(t0) {
     var t1 = new Date(t0.getFullYear(), t0.getMonth() + 1, 0),
    d0 = t0.getDay(),
    w0 = d3.timeWeek.count(d3.timeYear(t0), t0),
    d1 = t1.getDay(),
    w1 = d3.timeWeek.count(d3.timeYear(t1), t1);
    return (
             "M" +
             ((w0 + 1) * cellSize)  +
                 "," +
             d0 * cellSize +
                "H" +
             (w0 * cellSize)  +
                 "V" +
             7 * cellSize +
             "H" +
              (w1 * cellSize)  +
                "V" +
             (d1 + 1) * cellSize +
            "H" +
             ((w1 + 1) * cellSize)  +
             "V" +
            0 +
              "H" +
            ((w0 + 1) * cellSize)  +
                "Z"
             );
        }
</script>
<style>
 .information h3 {
    font-size:10px;
    color: white;
  }

  .information p {
    font-size:10px;
    color:white;
    padding: 5px 0px;
    font-variant-ligatures:common-ligatures
    discretionary-ligatures
    historical-ligatures;
    margin-top: 5px !important;
    margin-bottom: 5px !important;
  }
#calendar{
    width: 500px;
    height: 650px;
}
  .information {
    position:absolute;
    z-index: 1;
    transform: translate(1%, 0);
    width:8%;
    left:14%;
    top:6%;
    overflow:hidden;
    border-radius:.3em;
    box-shadow:0 0 0 1px hsla(0,0%,100%,.6) inset, 0 .5em 1em rgba(0, 0, 0, .3);
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px)
  }
</style>