<template>
  <div class="nav-bar">
    <el-menu
      :default-active="'/home'"
      class="el-menu-demo"
      mode="horizontal"
      router
      active-text-color="#25567A">
      <el-menu-item  v-for="(item,i) in navList" :index="item.url" :key="i">
        {{ item.name }}
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
    export default {
      name: "NavMenu",
      data() {
        return {
          navList:[
            {name:'首页',url:'/home'},
            {name:'书架',url:'/bookshelf'},
          ],
        };
      },
      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        }
      }
    }
</script>

<style>
  .nav-bar{
    width: 100%;
    left: 0px;
    top: 0px;
  }
  .el-menu-item{
    font-weight: bolder;
  }

</style>
