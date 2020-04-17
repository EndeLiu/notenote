<template>
  <div style="margin-left: 10px">
    <category-bar @categorySelect="getNotes" ref="categoryBar"></category-bar>
    <notes ref="notes" @editInfo="handleEditInfo" @addNote="handleAddNote" @updateInfo="getNotes" @editCategory="handleEditCategory"></notes>
    <note-edit-form ref="noteEditForm" @updateInfo="getNotes"></note-edit-form>
  </div>
</template>

<script>
  import Notes from "./Notes";
  import CategoryBar from "./CategoryBar";
  import NoteEditForm from "./NoteEditForm";

    export default {
      name: "Bookshelf",
      components:{Notes,CategoryBar,NoteEditForm},
      data() {
        return {

        }
      },
      methods: {
        getNotes(){
          var _this = this
          var currentCid = this.$refs.categoryBar.currentCid
          this.$router.push({
            query:{
              cid:currentCid
            }
          }).catch(data => {  })
          this.axios.get('categories/'+currentCid+'/notes')
          .then(function (response) {
            if(response.status === 200){
              _this.$refs.notes.showNotes(response.data)
            }
          })
        },

        handleEditInfo(noteInfo){
          this.$refs.noteEditForm.dialogFormVisible = true
          this.$refs.noteEditForm.isCreate = false
          this.$refs.noteEditForm.form = noteInfo
        },

        handleAddNote(){
          var currentCid = this.$refs.categoryBar.currentCid
          this.$refs.noteEditForm.dialogFormVisible = true
          this.$refs.noteEditForm.isCreate = true
          this.$refs.noteEditForm.form = {}
          this.$refs.noteEditForm.cid = this.$refs.categoryBar.currentCid
        },

        handleEditCategory(){
          this.$refs.categoryBar.edit()
        },





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
