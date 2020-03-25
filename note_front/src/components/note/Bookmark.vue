<template>
    <div style="margin-top: 100px;margin-right: 10px;margin-left: 10px">
      <el-menu class="el-menu-vertical-demo bookmark-area">
        <el-menu-item v-for="(item,i) in titles" :key="i" class="bookmark-item">
          <a :class="'title-index title-h'+item.type" @click="goTo(item.href)" >{{item.name.substr(0,5)}}</a>
          <span slot="title" style="vertical-align: center"></span>
        </el-menu-item>
      </el-menu>
    </div>
</template>

<script>
    export default {
      name: "Bookmark",
      data() {
        return {
          titles: []
        }
      },
      methods: {
        goTo(id){
          document.querySelector(id).scrollIntoView(true)
        }
      }
    }
</script>

<style>
  .el-scrollbar__wrap{
    overflow-x: hidden;
  }
  .bookmark-area{
    border-right: 0px;
    border: 1px solid black;
    border-radius: 10px;
    height: 200px;
    padding-top: 5px;
    overflow-y: auto;
    overflow-x: hidden;
   }
  .bookmark-area::-webkit-scrollbar{
    width: 0px;
  }
  .bookmark-area::-webkit-scrollbar-thumb{
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 2px rgba(0,0,0,0.2);
    background: #535353;
  }
  .bookmark-area::-webkit-scrollbar-track{
    -webkit-box-shadow: inset 0 0 2px rgba(0,0,0,0.2);
    border-radius: 10px;
    background: #EDEDED;
    height: 150px;
  }
  .bookmark-item{
    padding-left: 5px!important;
    padding-right: 5px;
    border: 1px solid white;
    border-radius: 3px;
    height: 20px;
    line-height: 20px;
    text-align: left;
  }
  .title-index{
    color: #2c3e50;
    font-style:normal;
    font-weight: bolder;
    font-size: 11px;
    text-decoration: none;
  }
  .title-index:hover{
    font-size: 12px
  }
  .el-tooltip{
    padding: 0px!important;
  }
  .title-h1 {
    padding-left: 0px;
  }
  .title-h2 {
    padding-left: 3px;
  }
  .title-h3 {
    padding-left: 6px;
  }
  .title-h4 {
    padding-left: 9px;
  }
  .title-h5 {
    padding-left: 12px;
  }
  .title-h6 {
    padding-left: 15px;
  }

</style>
