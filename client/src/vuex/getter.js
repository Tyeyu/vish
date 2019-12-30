const getter = {
    getforcelegend:function(state){
        return state.forcelegend;
    },
    getCforcedata:function(state){
        return state.Cforcedata;
    },
    getforceSvg(state){
        return state.forceSvg;
    },
    getforceColorScale(state){
        return state.forceColorScale;
    },
    getforceedgescale(state){
        return state.forceedgescale;
    },
    getCaClickdate(state){
        return state.CaClickdate;
    },
    getSTspend(state){
        return state.STspend;
    },
    getprofessionSpend(state){
        return state.professionSpend;
    },
    getAllflow(state){
        return state.Allflow;
    }
}

export default getter;