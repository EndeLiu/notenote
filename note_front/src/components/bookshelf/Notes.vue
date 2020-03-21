<template>
    <div style="text-align: initial;">
      <el-card shadow="hover" v-for="(item,i) in notes" :key="i"  class="box-card" >
        <div slot="header" class="clearfix">
          <span class="note-title">{{item.name}}</span>
          <el-tooltip transition="0s"  class="item" effect="dark" content="编辑笔记内容" placement="top-start">
            <el-button class="note-operation" type="text"><i class="el-icon-edit"></i></el-button>
          </el-tooltip>
          <el-tooltip transition="0s" class="item" effect="dark" content="查看笔记内容" placement="top-start">
            <el-button class="note-operation" type="text"><i class="el-icon-more"></i></el-button>
          </el-tooltip>
          <el-tooltip transition="0s" class="item" effect="dark" content="编辑笔记标题与简介" placement="top-start">
            <el-button class="note-operation" type="text"><i class="el-icon-edit-outline"></i></el-button>
          </el-tooltip>
        </div>
        <div class="text item note-abs">
          {{restrict(item.abs)}}
        </div>
      </el-card>
    </div>
</template>

<script>
    export default {
      name: "Notes",
      data() {
        return {
          notes:[]
        }
      },
      methods: {
        restrict(abs){
          if(abs.length<48){
            return abs
          }
          else {
            return abs.substring(0,48) + '...'
          }
        }
      }
    }
</script>

<style>
  .box-card{
    display: inline-block;
    width: 20%;
    margin-left: 15px;
  }
  .note-title{
    font-weight: bolder;
  }
  .note-abs{
    font-size: 12px;
  }
  .note-operation{
    float: right;
    padding: 3px 0;
    margin-right: 2px;
    margin-left: 2px;
  }
  .el-card__body{
    height: 50px;
  }
  .el-card__header {
    padding: 9px 20px;
  }


</style>
