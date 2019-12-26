<template>
    <div id="conotrlvu">
    <div class="conotrlvu_header">
        <h5>操作栏</h5>
    </div>
    </div>
</template>
<script>
export default {
    
}
</script>
<style>
#conotrlvu{
    position: absolute;
    z-index: 1;
    width: 12.5%;
    max-height: 90%;
    left: 1%;
    top: 6%;
    height: 70%;
    border-radius: 0.3em;
    box-shadow: 0 0 0 1px hsla(0, 0%, 100%, 0.3) inset,
    0 0.5em 1em rgba(0, 0, 0, 0.6);
    -webkit-backdrop-filter: blur(10px);
    backdrop-filter: blur(10px);
    font-size: 13px;
}
.conotrlvu_header{
padding-top: 5px;
  color: grey !important;
}
</style>