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
        Calendardata:function(){
            var datas={
            'date':"2019"
            }
            datas=JSON.stringify(datas);
            var x=$.ajax({
                type: "post",
                url: "http://localhost/Calen",
                dataType:"json",
                data: datas,
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    // console.log(result);
                    // let morning=[] 
                    //  for(var i=0;i<result.length;i++){
                    //      var ds=result[i].date;
                        
                    //      result[i].date= ds.split(" ")[0]
                    //  }
                    // var datenets=d3.nest()
                    //     .key(function(d) { return d.date; })
                    //     .entries(result)
                    // var datesMap= d3.map();
                    // for(var i=0;i<datenets.length;i++){
                    //     datesMap.set(datenets[i].key,datenets[i].values)
                    // }
                    //  console.log(datesMap);
                   
                }
               
            })
      
        },
        drawCalendar:function(drawdata){
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
                    return d3.timeMonths(new Date(2019, 4, 1), new Date(2019, 5, 1));
                })
                .enter()
                .append("path")
                .attr(
                    "transform",
                    "translate(" + -cellSize*16 + "," + 0+ ")"
                        )
                .attr("d", pathMonth);
            var rect = svg
                .append("g")
                .attr("class", "rects")
                .attr("fill", "none")
                .attr("stroke", "#ccc")
                .attr(
                        "transform",
                        "translate(" + cellSize * -16 + "," + 0 + ")"
                    )
                .selectAll("rect")
        //计算一组小方格的数量，调用d3的timeDays方法，获取两个时间之间的天数，例如，计算从1999年的第一天到2000年的第一天,则参数为new Date(1999,0,1)到 new Date(2000,0,1)，timeDays返回天序列
                .data(function(d) {
                     return d3.timeDays(new Date(2019, 4, 1), new Date(2019, 5, 1));
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
                .datum(d3.timeFormat("%Y-%m-%d"))
                    .append("title")
                // 定义小方格的title属性文本为 日期后面加小方格value对应的的百分比格式
                .text(function(d) {
                    return d;
                });
        }
    },
    mounted() {
      this.drawCalendar()
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