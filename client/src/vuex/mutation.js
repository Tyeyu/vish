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
        console.log("ssssss")
        state.forceColorScale=payload;
    },
    forceedgescale_state(state,payload){
        state.forceedgescale=payload;
    },
    CaClickdate_state(state,payload){
        state.CaClickdate=payload;
    },
    CaClicktable_state(state,payload){
        state.CaClicktable=payload;
    },
    STspend_state(state,payload){
        state.STspend=payload;
    },
    professionSpend_state(state,payload){
        state.professionSpend=payload;
    },
    Allflow_state(state,payload){
        state.Allflow=payload;
    },
    selectdaydata_state(state,payload){
        state.selectdaydata=payload;
    }
}
export default mutation;