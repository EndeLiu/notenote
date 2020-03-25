<template>
    <el-row style="margin-top: 10px">
      <el-col :span="bookmarkCol">
        <bookmark ref="bookmark"></bookmark>
      </el-col>
      <el-col :span="noteCol">
        <el-card class="box-card note" shadow="never" v-model="note">
          <div slot="header" class="clearfix">
            <span>{{note.name}}</span>
            <el-button style="float: right; padding: 3px 0" type="text">
              <i class="el-icon-edit" @click="edit(note.id)"></i>
            </el-button>
            <el-button style="float: right; padding: 3px 0;margin-right: 5px" type="text">
              <i class="el-icon-tickets" @click="bookmarkSwitch"></i>
            </el-button>
          </div>
          <el-scrollbar style="height: 100%">
            <div v-html="note.contentHtml" class="text note-html markdown-body" ></div>
          </el-scrollbar>
        </el-card>
      </el-col>
    </el-row>
</template>

<script>
  import Bookmark from "./Bookmark";
    export default {
      name: "NoteDetail",
      components: {Bookmark},
      data() {
        return {
          note:{
          },
          bookmarkStatus:true,
          bookmarkCol:2,
          noteCol:22,
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
                let newHtml = _this.appendPrefix(_this.note.contentHtml)
                _this.note.contentHtml = newHtml
                _this.getTitles(_this.note.contentHtml)
              }
            })
            .catch(function (error) {
              console.log(error)
            })
        },

        appendPrefix(contentHtml){
          let div = document.createElement("div")
          div.innerHTML = contentHtml
          let doc = div.children
          for(var i=0;i<doc.length;i++){
            if(doc[i].nodeName.indexOf("H") !== -1){
              let a = doc[i].children[0]
              let originId = a.getAttribute("id")
              a.setAttribute("id",'title_'+originId)
            }
          }
          return div.innerHTML
        },

        getTitles(contentHtml){
          let div = document.createElement("div")
          div.innerHTML = contentHtml
          let doc = div.children
          let titles = []
          for(var i=0;i<doc.length;i++){
            if(doc[i].nodeName.indexOf("H") !== -1){
              titles.push({
                href:"#"+doc[i].children[0].getAttribute("id"),
                name:doc[i].innerText,
                type:doc[i].nodeName[1]
              })
            }
          }
          this.$refs.bookmark.titles = titles
        },

        edit(id){
          this.$router.push({
            path:'/note/edit',
            name:'NoteEdit',
            query:{
              noteId:id
            }
          })
        },

        bookmarkSwitch(){
          if(this.bookmarkStatus){
            this.bookmarkCol = 0
            this.noteCol = 24
          }
          else{
            this.bookmarkCol = 2
            this.noteCol = 22
          }
          this.bookmarkStatus = !this.bookmarkStatus
        }
      }
    }
</script>

<style scoped>
  .note {
    width: 90%;
    text-align: left;
    margin: auto;
  }
  .note-html{
    height: 450px;
  }
</style>
