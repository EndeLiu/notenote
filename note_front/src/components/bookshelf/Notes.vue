<template>
    <div style="text-align: initial;">
      <el-row>
        <el-card shadow="hover" v-for="(item,i) in visibleNotes" :key="i"  class="box-card" >
          <div slot="header" class="clearfix">
            <span class="note-title">{{item.name}}</span>
            <el-tooltip transition="0s"  class="item" effect="dark" content="编辑笔记内容" placement="top-start">
                <el-button class="note-operation" type="text" @click="editNote(item.id)"><i class="el-icon-edit"></i></el-button>
            </el-tooltip>
            <el-tooltip transition="0s" class="item" effect="dark" content="查看笔记内容" placement="top-start">
              <el-button class="note-operation" type="text" @click="readNote(item.id)"><i class="el-icon-more"></i></el-button>
            </el-tooltip>
            <el-tooltip transition="0s" class="item" effect="dark" content="编辑笔记标题与简介" placement="top-start">
              <el-button class="note-operation" type="text" @click="editInfo(item)"><i class="el-icon-edit-outline"></i></el-button>
            </el-tooltip>
            <el-tooltip transition="0s" class="item" effect="dark" content="删除此笔记" placement="top-start">
              <el-button class="note-operation" type="text" @click="deleteNote(item.id)"><i class="el-icon-delete"></i></el-button>
            </el-tooltip>
          </div>
          <div class="text item note-abs">
            {{restrict(item.abs)}}
          </div>
        </el-card>
      </el-row>

      <el-row style="position: fixed;bottom: 10px;right: 10px">
        <el-button type="success" circle @click="editCategory">
          <i class="el-icon-edit"></i>
        </el-button>
      </el-row>

      <el-row style="position: fixed;bottom: 70px;right: 10px">
        <el-button type="primary" circle @click="addNote">
          <i class="el-icon-document-add"></i>
        </el-button>
      </el-row>

      <el-row class="page">
        <el-pagination
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="notes.length"
          @current-change="handelPageChange">
        </el-pagination>
      </el-row>

    </div>
</template>

<script>
    export default {
      name: "Notes",
      data() {
        return {
          notes:[],
          visibleNotes:[],
          currentPage:1,
          pageSize:12,
        }
      },
      methods: {
        showNotes(notes){
          this.notes = notes
          //0-11
          //12-24
          this.visibleNotes = []
          for(var i=this.pageSize*(this.currentPage-1);i<(this.pageSize)*(this.currentPage);i++){
            if(i>=this.notes.length){
              break
            }
            this.visibleNotes.push(this.notes[i])
          }
          console.log(this.visibleNotes)
        },

        restrict(abs){
          if(abs.length<48){
            return abs
          }
          else {
            return abs.substring(0,48) + '...'
          }
        },

        editInfo(note){
          this.$emit('editInfo',note)
        },

        editNote(id){
          this.$router.push({
            path:'/note/edit',
            name:'NoteEdit',
            query:{
              noteId:id
            }
          })
        },

        readNote(id){
          this.$router.push({
            path:'/note/detail',
            name:'NoteDetail',
            query:{
              noteId:id
            }
          })
        },

        addNote(){
          this.$emit('addNote')
        },

        deleteNote(id){
          var _this = this
          this.$confirm('此操作将永久删除该笔记, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.axios.get('note/delete/'+id.toString())
              .then(function (response) {
                if(response.data.status === 200){
                  _this.$message({
                    type: 'success',
                    message: '删除成功!'
                  });
                  _this.$emit('updateInfo')
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

        },

        editCategory(){
          this.$emit('editCategory')
        },

        handelPageChange(pageBack){
          this.currentPage = pageBack
          this.visibleNotes = []
          for(var i=this.pageSize*(this.currentPage-1);i<(this.pageSize)*(this.currentPage);i++){
            if(i>=this.notes.length){
              break
            }
            this.visibleNotes.push(this.notes[i])
          }
          console.log(this.visibleNotes)
        },


      }
    }
</script>

<style>
  .box-card{
    display: inline-block;
    width: 23%;
    margin-left: 15px;
  }
  .note-title{
    font-weight: bolder;
  }
  .note-abs{
    font-size: 12px;
    height: 50px;
  }
  .note-operation{
    float: right;
    padding: 3px 0;
    margin-right: 2px;
    margin-left: 2px!important;
  }

  .el-card__header {
    padding: 9px 20px;
  }
  .page{
    position:fixed;
    margin:auto;
    left:0;
    right:0;
    bottom:0;
    width:200px;
    height:50px;
  }


</style>
