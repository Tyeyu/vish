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
                addranges[i+1]=(i+1)/addressNets.length
            }
            var scaleOrdinal = d3.scaleOrdinal()
                                .domain(addressArry)
                                .range(addranges);
            let compute = d3.interpolate('#01BDD6','#FFEB3C')
            //封装颜色比例尺
            var colorscale=function(d){
                return compute(scaleOrdinal(d))
            }
            var edgescale=d3.scaleLinear()
            .domain([d3.min(drawdata,function(d){
                return parseInt(d.count)
            }), d3.max(drawdata,function(d){
                return parseInt(d.count)
            })])
            .range([0, 1])
            //console.log(edgescale(parseInt("160")))
            // for(var i=0;i<addressArry.length;i++)
            // {
            //      console.log(colorscal(addressArry[i]))
            // }
           
            var svg = d3
                .select("#calendar")
                .append("svg")
                .attr("width",width)
                .attr("height", height);
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
                .datum(d3.timeFormat("%Y/%m/%d"))
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
                        transform: "translate(" + cellSize + "," + 0 + ")"
                   }
                   that.drawForce(fonfig);
                   return cellSize;
               })

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
           var svg = config.svg.append("svg").style("position","absolute")
                .attr("viewBox", [0, 0, config.width, config.height])
                .style("left",config.position.x-cellSize*11.95)
                .style("top",config.position.y+cellSize*1.75)
                .attr("width",config.width)
                .attr("height",config.height);
           var link= svg.append("g")
                        .attr("stroke", "#999")
                        .attr("stroke-opacity", 0.6)
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