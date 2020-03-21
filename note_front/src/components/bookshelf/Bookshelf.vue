<template>
  <div style="margin-left: 10px">
    <category-bar @categorySelect="getNotes" ref="categoryBar"></category-bar>
    <notes ref="notes"></notes>
  </div>
</template>

<script>
  import Notes from "./Notes";
  import CategoryBar from "./CategoryBar";
    export default {
      name: "Bookshelf",
      components:{Notes,CategoryBar},
      data() {
        return {

        }
      },
      methods: {
        getNotes(){
          var _this = this
          var currentCid = this.$refs.categoryBar.currentCid
          this.axios.get('categories/'+currentCid+'/notes')
          .then(function (response) {
            if(response.status === 200){
              _this.$refs.notes.notes = response.data
            }

          })
        }
      }

    }
</script>

<style>
  .el-tabs__item {
    height: 20px;
    line-height: 20px;
    font-size: 12px;
  }

  .el-tabs__item.is-active {
    font-weight: bolder;
  }

</style>
