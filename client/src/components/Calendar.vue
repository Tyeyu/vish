<template>
<div class="information">
     <p>information</p>
</div>
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