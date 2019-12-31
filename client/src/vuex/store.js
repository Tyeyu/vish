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
        selectdaydata:null
    },
    mutations:mutation,
    actions:action,
    getters:getter

})
export default store;