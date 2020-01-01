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
    getCaClicktable(state){
        return state.CaClicktable;
    },
    getSTspend(state){
        return state.STspend;
    },
    getprofessionSpend(state){
        return state.professionSpend;
    },
    getAllflow(state){
        return state.Allflow;
    },
    getselectdaydata(state){
        return state.selectdaydata;
    },
    getSelectedMajor(state){
        return state.selectedMajor;
    }
}

export default getter;