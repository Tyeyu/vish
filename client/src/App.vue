<template>
  <div id="app">
    <Main></Main>
    <calendar></calendar>
    <lineChart></lineChart>
    <cir></cir>
  </div>
</template>

<script>
import Main from '@/components/main'
import calendar from '@/components/Calendar'
import lineChart from '@/components/lineChart'
import cir from '@/components/cir'
export default {
  name: 'App',
  components:{
    Main,
    calendar,
    lineChart,
    cir
  }
}
</script>

<style>
 html,body{
    padding: 0;
    margin: 0;
    overflow: hidden;
    -moz-user-select:none;/*火狐*/
    -webkit-user-select:none;/*webkit浏览器*/
    -ms-user-select:none;/*IE10*/
    -khtml-user-select:none;/*早期浏览器*/
    user-select:none;
  }
#app {
    position: fixed;
    width: 100%;
    height: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    background-color: #191a1a;
  }
</style>
