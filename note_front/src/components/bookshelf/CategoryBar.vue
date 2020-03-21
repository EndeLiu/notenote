<template>
    <div style="margin-left: 15px;">
      <el-tabs v-model="currentCid" @tab-click="handleClick">
        <el-tab-pane v-for="(item,i) in categories"  :label="item.name" :key="i" :name="item.id.toString()"></el-tab-pane>
      </el-tabs>
    </div>
</template>

<script>
    export default {
      name: "CategoryBar",
      data() {
        return {
          currentCid:"1",
          categories:[]
        }
      },
      mounted() {
        var _this = this;
        this.axios.get("/categories")
          .then(function (response) {
            if(response.status === 200) {
              _this.categories = response.data
            }
          })
      },
      methods: {
        handleClick() {
          this.$emit('categorySelect')
        }
      }
    }
</script>

<style scoped>

</style>
