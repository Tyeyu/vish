<template>
    <div id="downpage"></div>
</template>
<script>
export default {
    
}
</script>
<style>
#downpage{
    position: absolute;
    z-index: 1;
    width: 99%;
    max-height: 90%;
    left: 1%;
    top: calc(2% + 560px);
    height: calc(97% - 560px);
    border-radius: 0.3em;
    box-shadow: 0 0 0 1px hsla(0, 0%, 100%, 0.3) inset,
    0 0.5em 1em rgba(0, 0, 0, 0.6);
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px);
    font-size: 13px;
}
</style>