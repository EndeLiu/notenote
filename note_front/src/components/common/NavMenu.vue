<template>
  <div class="nav-bar">
    <el-menu
      class="el-menu-demo"
      mode="horizontal"
      router
      active-text-color="#25567A">
      <el-menu-item  v-for="(item,i) in navList" :index="item.url" :key="i">
        {{ item.name }}
      </el-menu-item>

      <el-submenu index="2" style="float:right;">
        <template slot="title">{{userFlag.name}}</template>

        <el-menu-item v-for="(item,i) in userFlag.menuList" :index="item.url" :key="i">{{item.name}}</el-menu-item>
        <el-menu-item :style="{display:isLogin}"
                      @click="logout">注销</el-menu-item>
      </el-submenu>

      <li style="float: right;outline: none;cursor: pointer">
        <i class="el-icon-full-screen" style="height: 35px;line-height: 35px;" @click="fullScreen"></i>
      </li>


    </el-menu>
  </div>
</template>

<script>
    export default {
      name: "NavMenu",
      data() {
        return {
          isLogin:'none',
          userFlag: {
            name: '',
            menuList: []
          },

          navList: [
            {name: 'NoteNote', url: '/home'},
            {name: '书架', url: '/bookshelf'},
          ],
        };
      },

      mounted() {
        if (window.localStorage.getItem("user") != null) {
          this.userFlag.name = JSON.parse(window.localStorage.getItem("user")).username
          this.userFlag.menuList = [
            {url: '/home', name: '用户中心'},
            {url: '/home', name: '笔记管理'},
          ]
          this.isLogin = 'inline-block'
        } else {
          this.userFlag.name = "未登录"
          this.userFlag.menuList = [
            {url: '/register', name: '注册'},
            {url: '/login', name: '登录'},
          ]
          this.isLogin = 'none'
        }
      },

      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath)
        },
        logout(){
          var _this = this
          this.axios.get('/logout')
          .then(function (response) {
            if(response.data.status === 200){
              _this.$store.commit('logout')
              _this.$router.replace('/login')
            }
          })
          .catch(function (error) {
            console.log(error)
          })

        },
        fullScreen(ev) {
          const isFull=!!(document.webkitIsFullScreen || document.mozFullScreen ||
            document.msFullscreenElement || document.fullscreenElement
          )
          if(!isFull){
            var element = document.documentElement;
            if (element.requestFullscreen) {
              element.requestFullscreen()
            } else if (element.msRequestFullscreen) {
              element.msRequestFullscreen()
            } else if (element.mozRequestFullScreen) {
              element.mozRequestFullScreen()
            } else if (element.webkitRequestFullscreen) {
              element.webkitRequestFullscreen()
            }
          }
          else{
            if (document.exitFullscreen) {
              document.exitFullscreen();
            } else if (document.msExitFullscreen) {
              document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
              document.mozCancelFullScreen();
            } else if (document.webkitExitFullscreen) {
              document.webkitExitFullscreen();
            }
          }
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
    font-weight: bolder!important;
  }
  .el-menu--horizontal>.el-menu-item {
    height: 35px!important;
    line-height: 35px!important;
  }
  .el-menu--horizontal>.el-submenu .el-submenu__title {
    height: 35px!important;
    line-height: 35px!important;
  }

</style>
