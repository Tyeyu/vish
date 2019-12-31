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
                  that.drawline(result);
                }
               
            })
        },
        drawline:function(data){
             var svg = d3
                .select("#calendarline").attr("width",550).attr("height",300)
                .append("svg")
                .attr("width", 550)
                .attr("height", 300);
        }
    },
    computed: {
        CaClickdate () {
            return this.$store.getters.getCaClickdate;
        },
        CaClicktable () {
            return this.$store.getters.getCaClicktable;
        }
    },
    watch:{
         CaClickdate:function(newdata,olddata){
          if(newdata!=null)
          {
              this.CalendarStudentdata();
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
</style>