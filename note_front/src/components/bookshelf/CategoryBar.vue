<template>
    <div style="margin-left: 15px;margin-top: 15px">
      <el-tabs v-model="currentCid" @tab-click="handleClick" type="card" editable @edit="editCategory">
        <el-tab-pane   v-for="(item,i) in categories"  :label="item.name" :key="i+item.name" :name="item.id.toString()" >
        </el-tab-pane>
      </el-tabs>
      <category-edit-form ref="editForm" @update="update"></category-edit-form>
    </div>
</template>

<script>
  import CategoryEditForm from "./CategoryEditForm";
    export default {
      name: "CategoryBar",
      components: {CategoryEditForm},
      data() {
        return {
          currentCid:"-1",
          categories:[]
        }
      },
      mounted() {
        this.update()
      },
      methods: {
        update(){
          var _this = this
          var cid
          if(this.$route.query.cid === undefined){
            cid = undefined
          }
          else{
            cid = this.$route.query.cid
          }

          this.axios.get("/categories")
            .then(function (response) {
              if(response.status === 200) {
                _this.categories = response.data
                if(_this.categories.length>0){
                  if(cid === undefined){
                    _this.currentCid = _this.categories[0].id.toString()
                  }
                  else{
                    _this.currentCid = cid
                  }
                  _this.$emit('categorySelect')
                }
              }
            })
        },

        edit() {
          this.$refs.editForm.dialogFormVisible = true
          this.$refs.editForm.isCreate = false
          for(var i=0;i<this.categories.length;i++){
            if(this.categories[i].id.toString() === this.currentCid){
              this.$refs.editForm.form = this.categories[i]
            }
          }
        },

        editCategory(targetName,action) {
          if (action === 'remove') {
            var _this = this
            this.$confirm('此操作将永久删除该笔记分类, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              _this.axios.get('category/delete/'+targetName.toString())
                .then(function (response) {
                  if(response.data.status === 200){
                    _this.$message({
                      type: 'success',
                      message: '删除成功!'
                    });
                    let tabs = _this.categories;
                    let activeName = _this.currentCid;
                    if (activeName === targetName) {
                      tabs.forEach((tab, index) => {
                        if (tab.id.toString() === targetName) {
                          let nextTab = tabs[index + 1] || tabs[index - 1];
                          if (nextTab) {
                            activeName = nextTab.id.toString();
                          }
                        }
                      });
                    }
                    _this.currentCid = activeName;
                    _this.categories = tabs.filter(tab => tab.id.toString() !== targetName);
                  }
                })
                .catch(function (error) {
                  console.log(error)
                })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });

          }
          if(action === 'add'){
            this.$refs.editForm.dialogFormVisible = true
            this.$refs.editForm.isCreate = true
          }


        },

        handleClick() {
          this.$emit('categorySelect')
        },
      }
    }
</script>

<style>
  .el-tabs__new-tab{
    border: 0px;
    margin: 0px 10px 0px 0px;
    font-size: 18px;
    color: crimson;
  }
  .el-tabs__nav-prev,.el-tabs__nav-next {
    line-height: 22px;
  }

</style>
