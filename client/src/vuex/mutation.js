const mutation={
    forcedata_state(state,payload){
        state.Cforcedata=payload;
    },
    forcelegend_state(state,payload){
        state.forcelegend=payload;
    },
    forceSvg_state(state,payload){
        state.forceSvg=payload;
    },
    forceColorScale_state(state,payload){
        state.forceColorScale=payload;
    },
    forceedgescale_state(state,payload){
        state.forceedgescale=payload;
    },
    CaClickdate_state(state,payload){
        console.log("commmit",payload)
        state.CaClickdate=payload;
    }
}
export default mutation;