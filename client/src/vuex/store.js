import Vue from "vue";
import Vuex from "vuex";
import mutation from "./mutation";
import action from "./action";
import getter from "./getter";
Vue.use(Vuex);

const store=new Vuex.Store({
    state:{
        Cforcedata:{},
        forcelegend:null,
        forceSvg:null,
        forceColorScale:null,
        forceedgescale:null,
        CaClickdate:null,
        CaClicktable:null,
        STspend:false,
        professionSpend:false,
        Allflow:true,
        selectdaydata:null,
        selectedMajor:'18软件技术'
    },
    mutations:mutation,
    actions:action,
    getters:getter

})
export default store;