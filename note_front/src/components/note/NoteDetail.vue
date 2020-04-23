<template>
  <div>
    <el-row>
      <el-button type="primary" icon="el-icon-back"  class="back" @click="back"></el-button>
    </el-row>
    <el-row style="margin-top: 10px">
      <el-col :span="bookmarkCol" >
        <bookmark ref="bookmark"></bookmark>
      </el-col>
      <el-col :span="noteCol" style="border-left: 1px solid #585858;">
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
            <div v-html="note.contentHtml" class="text note-html markdown-body" @click="handleHtmlClick"></div>
          </el-scrollbar>
        </el-card>
      </el-col>

      <el-col :span="quoteCol" style="border-left: 1px solid #585858;">
        <el-card class="box-card note" shadow="never" v-model="quote">
          <div slot="header" class="clearfix">
            <span>{{quote.name}}</span>
            <el-button style="float: right; padding: 3px 0" type="text">
              <i class="el-icon-edit" @click="edit(quote.id)"></i>
            </el-button>
            <el-button style="float: right; padding: 3px 0" type="text">
              <i class="el-icon-close" @click="closeQuote"></i>
            </el-button>
          </div>
          <el-scrollbar style="height: 100%" id="quoteArea">
            <div v-html="quote.contentHtml" class="text note-html markdown-body"></div>
          </el-scrollbar>
        </el-card>
      </el-col>
    </el-row>
  </div>

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
          quote:{
          },
          quoteLink:'',
          bookmarkStatus:true,
          quoteStatus:false,
          bookmarkCol:3,
          noteCol:21,
          quoteCol:0,
        }
      },
      mounted() {
        if(this.$route.query.noteId){
          this.loadNote(this.$route.query.noteId)
        }
      },

      watch:{
        quoteLink(){
          var _this = this
          if(_this.quoteLink !== ''){
            this.$nextTick(function () {
              $('#quoteArea').children(":first").animate({
                scrollTop:document.getElementById(_this.quoteLink).offsetTop
              },1000)
            })
          }
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
                _this.note.contentHtml = _this.removeTarget(newHtml)
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
              let operationBtn = document.createElement("i")
              operationBtn.setAttribute("class","el-icon-arrow-down")

              operationBtn.style.cursor = "pointer"
              operationBtn.style.marginLeft = "5px"
              operationBtn.style.fontSize = "20px"
              doc[i].appendChild(operationBtn)
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
              const level = parseInt(doc[i].nodeName[1])
              let stars = ""
              for(var j=0;j<level;j++){
                stars += " * "
              }
              titles.push({
                href:"#"+doc[i].children[0].getAttribute("id"),
                name:stars+doc[i].innerText,
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
            if(this.quoteStatus){
              this.colSwitch(0,14,10)
            }
            else{
              this.colSwitch(0,24,0)
            }
          }
          else{
            if(this.quoteStatus){
              this.colSwitch(3,11,10)
            }
            else{
              this.colSwitch(3,21,0)
            }
          }
          this.bookmarkStatus = !this.bookmarkStatus
        },

        closeQuote() {
          if(this.bookmarkStatus){
            this.colSwitch(3,21,0)
          }
          else{
            this.colSwitch(0,24,0)
          }
          this.quoteStatus = false
          this.quote = {}
        },

        removeTarget(contentHtml){
          let div = document.createElement("div")
          div.innerHTML = contentHtml
          let doc = div.children
          for(var i=0;i<doc.length;i++){
            if(doc[i].nodeName === "P"){
              for(var j=0;j<doc[i].children.length;j++){
                if(doc[i].children[j].nodeName === "A"){
                  if(doc[i].children[j].getAttribute("href").indexOf("notelink://") !== -1){
                    doc[i].children[j].removeAttribute("target")
                  }
                }
              }
            }

          }
          return div.innerHTML
        },

        handleHtmlClick(ev){
          if(ev.target.nodeName === "A" && ev.target.getAttribute("href").indexOf("notelink://") !== -1){
            this.quoteNote(ev)
          }
          else if(ev.target.nodeName === "I"){
            this.foldSwitch(ev)
          }
        },


        colSwitch(b,n,q){
          this.bookmarkCol = b!=null?b:this.bookmarkCol
          this.noteCol = n!=null?n:this.noteCol
          this.quoteCol = q!=null?q:this.quoteCol
        },

        back(){
          this.$router.go(-1);
        },

        /**html点击，激活引用笔记**/
        quoteNote(ev){
          var _this = this
          var tempLink = (_this.quoteLink).toString()
          _this.quoteLink = ''
          var href = ev.target.getAttribute("href")
          var noteId = href.match(/(\d+)[&]/)[1]
          var titleId = href.match(/[&](.*)/)[1]
          if(_this.quote.id !== undefined && noteId === _this.quote.id.toString()){
            _this.quoteLink = titleId
            //console.log("same")
            $('#quoteArea').children(":first").animate({
              scrollTop:document.getElementById(_this.quoteLink).offsetTop
            },1000)
          }
          else {
            _this.axios.get('note/'+noteId.toString())
              .then(function (response) {
                if(response.data.status === 200){
                  _this.quote = response.data.object
                  _this.colSwitch(null,11,10)
                  _this.quoteStatus = true
                  _this.quoteLink = titleId
                }
              })
              .catch(function (error) {
                console.log(error)
              })
          }
        },

        /**html点击，章节折叠**/
        foldSwitch(ev){
          let foldBtn = ev.target
          const foldTarget = foldBtn.parentNode
          const foldLevel = parseInt(foldTarget.nodeName[1])
          console.log(foldLevel)
          if(foldBtn.getAttribute("class") === "el-icon-arrow-down"){
            foldBtn.setAttribute("class","el-icon-arrow-up")
            let itElement = foldTarget
            while(true){
              itElement = itElement.nextElementSibling
              if(itElement == null){
                break
              }
              const itLevel = itElement.nodeName[0] === "H"?parseInt(itElement.nodeName[1]):9
              if(itLevel <= foldLevel){
                break
              }
              itElement.style.display = "none"
              if(itElement.getAttribute("flag") === null){
                itElement.setAttribute("flag", foldLevel.toString());
              }

            }
          }
          else{
            foldBtn.setAttribute("class","el-icon-arrow-down")
            let itElement = foldTarget
            while(true){
              itElement = itElement.nextElementSibling
              if(itElement == null){
                break
              }
              const itLevel = itElement.nodeName[0] === "H"?parseInt(itElement.nodeName[1]):9
              if(itLevel <= foldLevel){
                break
              }
              if(itElement.getAttribute("flag") === foldLevel.toString()){
                itElement.style.display = ""
                itElement.removeAttribute("flag")
              }


            }
          }
        }

      }
    }
</script>

<style scoped>
  .note {
    border: 0px solid #EBEEF5!important;
    width: 95%;
    text-align: left;
    margin: auto;
  }
  .note-html{
    height: 450px;
  }
  .back{
    background-color: #585858!important;
    border: 0px;
    position: absolute;
    margin-top: 10px;
    margin-bottom: 5px;
    left: 10px;
    padding: 5px 5px;
    z-index: 99;
  }
  .op-btn{
    cursor: pointer;
  }
</style>
