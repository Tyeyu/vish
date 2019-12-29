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
    }
}

export default getter;