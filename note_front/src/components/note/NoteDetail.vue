<template>
    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <el-card class="box-card note" shadow="never" v-model="note">
          <div slot="header" class="clearfix">
            <span>{{ note.name }}</span>
          </div>
          <el-scrollbar style="height: 800px">
            <div v-html="note.contentHtml" class="text note-html" style="height: 500px"></div>
          </el-scrollbar>
        </el-card>
      </el-col>
    </el-row>
</template>

<script>
    export default {
      name: "NoteDetail",
      data() {
        return {
          note:{
          }
        }
      },
      mounted() {
        if(this.$route.query.noteId){
          this.loadNote(this.$route.query.noteId)
        }
      },
      methods: {
        loadNote(id){
          var _this = this
          this.axios.get('note/'+id.toString())
            .then(function (response) {
              if(response.data.status === 200){
                _this.note = response.data.object
              }
            })
            .catch(function (error) {
              console.log(error)
            })
        },
      }
    }
</script>

<style scoped>
  .note {
    width: 90%;
    height: 500px;
    text-align: left;
    margin: 15px auto 0 auto
  }
  .note-html{
    height: auto;
  }
</style>
