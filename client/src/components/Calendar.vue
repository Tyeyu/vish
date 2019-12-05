<template>
<div></div>
</template>
<script>
import $ from 'jquery'
import * as d3 from 'd3';
export default {
    name:"calendar",
    methods:{
        drawCalendar:function(){

            var datas={
            'date':"2019/4/1 "
            }
            datas=JSON.stringify(datas);
            var x=$.ajax({
                type: "post",
                url: "http://localhost/Calen",
                dataType:"json",
                data: datas,
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    console.log(result); 
                    var nests= d3.nest()
                        .key(function(d) { return d.accessCarDNo; })
                        .entries(result);
                    console.log(nests);
                }
               
            })
        }
    },
    mounted() {
      this.drawCalendar()
    }

}
</script>
<style>

</style>