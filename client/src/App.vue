<template>
  <div id="app">
    <!-- <Main></Main> -->
    <control ></control>
    <calendar></calendar>
    <rose></rose>
    <downpage></downpage>
    <lineChart></lineChart>
    <bar></bar>
    <pie></pie>
    <barChart></barChart>
    <histogram></histogram>
    <sunburst></sunburst>
  </div>
</template>

<script>
import Main from '@/components/main'
import calendar from '@/components/Calendar'
import lineChart from '@/components/lineChart'
import bar from '@/components/bar'
import pie from '@/components/pie'
import rose from '@/components/rose'
import barChart from "@/components/barChart"
import control from'@/components/Control'
import downpage from "@/components/downpage"
import histogram from "@/components/histogram"
import sunburst from "@/components/sunburst"
export default {
  name: 'App',
  components:{
    Main,
    calendar,
    lineChart,
    bar,
    pie,
    control,
    downpage,
    rose,
    barChart,
    histogram,
    sunburst
  }
}
</script>

<style>
 html,body{
    padding: 0;
    margin: 0;
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
