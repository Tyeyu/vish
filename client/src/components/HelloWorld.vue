<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="http://localhost/hello"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
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
  color: #42b983;
}
</style>
