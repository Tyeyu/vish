<template>
    <div id="conotrlvu">
    <div class="conotrlvu_header">
        <div>
        <h3>操作栏</h3>
        <div class="line-separator-1"></div>
        </div>
        <div>
            <h4>消费</h4>
            <div class="custom-control custom-radio">
            <input
                type="radio" name="spend"  @click="STspend()"
            />
            <label class="custom-control-label">食堂消费</label>
            </div>
            <!--  <div class="custom-control custom-radio">
            <input
                type="radio" name="spend" @click="professionSpend()"
            />
            <label class="custom-control-labe2" >专业消费</label>
            </div> -->
            <div class="line-separator-1"></div>
        </div>
        <div>
            <h4>流量</h4>
            </div>
             <div class="custom-control custom-radio">
            <input
                type="radio" name="spend" checked="checked" @click="Allflow()"
            />
            <label class="custom-control-labe2" >总体情况</label>
            </div>
            <div class="line-separator-1"></div>
        </div>
        <div class="titles">
           <label  >{{message}}</label>
        </div>
    </div>
</template>
<script>
export default {
    data:function(){
       return { message:"总流量日历图"}
    },
    methods: {
        STspend:function(){
            this.$store.commit("STspend_state",true);
            this.$store.commit("professionSpend_state",false);
            this.$store.commit("Allflow_state",false);
            this.message="xxxxx";
        },
        professionSpend:function(){
            this.$store.commit("STspend_state",false);
            this.$store.commit("professionSpend_state",true);
            this.$store.commit("Allflow_state",false);
        },
        Allflow:function(){
            this.$store.commit("STspend_state",false);
            this.$store.commit("professionSpend_state",false);
            this.$store.commit("Allflow_state",true);
            this.message="总流量日历图";
        }
    }
}
</script>
<style>
#conotrlvu{
    position: absolute;
    z-index: 1;
    width: 5.5%;
    max-height: 90%;
    left: 1%;
    top: 1%;
    height: 200px;
    border-radius: 0.3em;
    box-shadow: 0 0 0 1px hsla(0, 0%, 100%, 0.3) inset,
    0 0.5em 1em rgba(0, 0, 0, 0.6);
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px);
    font-size: 13px;
}
.conotrlvu_header{
padding-top: 5px;
  color: white !important;
}
.line-separator-1 {
  height: 1px;
  background: grey;
}
.titles{
    position: absolute;
    z-index: 1;
    width: 100%;
    max-height: 90%;
    left: 1%;
    top: calc(2% + 350px);
    height: 50px;
    border-radius: 0.3em;
    /* box-shadow: 0 0 0 1px hsla(0, 0%, 100%, 0.3) inset, */
    /* 0 0.5em 1em rgba(0, 0, 0, 0.6); */
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px);
    font-size: 15px;
}
.titles label{
    color: white
}
</style>