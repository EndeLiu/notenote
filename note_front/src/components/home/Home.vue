<template>
    <div class="container">
      <svg viewBox="0 0 1000 300">
        <symbol id="line-text">
          <text text-anchor="middle" x="50%" y="50%" dy="0.4em">
            Note Note
          </text>
        </symbol>
        <use xlink:href="#line-text" class="text"></use>
        <use xlink:href="#line-text" class="text"></use>
        <use xlink:href="#line-text" class="text"></use>
        <use xlink:href="#line-text" class="text"></use>
      </svg>
    </div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>
  .container {
    z-index: -1;
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top:0;
    background-image: url("../../../static/homeMask.png");
    background-size: cover;

    font-size: 120px;
    font-weight: bolder;
    text-transform: uppercase;
  }
  svg{
    position: fixed;
    left: 0;
    width: 100%;
    height: 100%;
  }
  .text{
    fill: none;
    stroke-width: 5;
    stroke-dasharray: 0 240;
    stroke-dashoffset: 0;
  }
  .text:nth-child(4n+1){
    stroke:#FFFFFF;
    animation: text1 4s ease-in-out forwards;
  }
  .text:nth-child(4n+2){
    stroke:#E0E0E0;
    animation: text2 4s ease-in-out forwards;
  }
  .text:nth-child(4n+3){
    stroke:#A8A8A8;
    animation: text3 4s ease-in-out forwards;
  }
  .text:nth-child(4n+4){
    stroke:#787878;
    animation: text4 4s ease-in-out forwards;
  }
  @keyframes text1 {
    100% {
      stroke-dasharray: 60 180;
      stroke-dashoffset: 1000;
    }
  }
  @keyframes text2 {
    100% {
      stroke-dasharray: 60 180;
      stroke-dashoffset: 1060;
    }
  }
  @keyframes text3 {
    100% {
      stroke-dasharray: 60 180;
      stroke-dashoffset: 1120;
    }
  }
  @keyframes text4 {
    100% {
      stroke-dasharray: 60 180;
      stroke-dashoffset: 1180;
    }
  }

</style>
