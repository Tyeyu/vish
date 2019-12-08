import Vue from "vue";
import Vuex from "vuex";
import mutation from "./mutation";
import action from "./action";
import getter from "./getter";
Vue.use(Vuex);

const store=new Vuex.store({
    state:{
        Cforcedata:{}

    },
    mutations:mutation,
    actions:action,
    getters:getter

})
export default store;