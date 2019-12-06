<template>
<div><P>start</P></div>
</template>

<script>
import $ from 'jquery'
import * as d3 from 'd3';
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: ' '
    }
  },
  methods:{
  }
}
//====
//tableID 取值为1,2,3，代表3张表
//============请求相关列指定范围数据
/*var datas={
    'major':"18国际金融",
    "sex":"男",
    "tableID":1
}
datas=JSON.stringify(datas);
var x=$.ajax({
  type: "post",
   url: "http://localhost/MutColumn",
   dataType:"json",
   data: datas,
   contentType: "application/json; charset=utf-8",
   success: function (result) {
     
   console.log(result); 
   }
})
*/
//============请求某几列所有数据
/*var datas={
    'Columns':"sex,cardno",
    "tableID":1
}
datas=JSON.stringify(datas);
var x=$.ajax({
  type: "post",
   url: "http://localhost/Columns",
   dataType:"json",
   data: datas,
   contentType: "application/json; charset=utf-8",
   success: function (result) {
     
   console.log(result); 
   }
})*/
//============请求所有数据
/*var datas={
    "tableID":1
}
datas=JSON.stringify(datas);
var x=$.ajax({
  type: "post",
   url: "http://localhost/All",
   dataType:"json",
   data: datas,
   contentType: "application/json; charset=utf-8",
   success: function (result) {
     
   console.log(result); 
   }
})*/
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #ebf5f0;
}
</style>
