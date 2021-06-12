<template>
<div class="bg">
<span class="text">welcome 请及时完善个人信息</span>
</div>
</template>

<script>
export default {
  name: 'Welcome'
}
</script>

<style Lang="less" scoped>
.bg{
  height: 100%;
  background: url("../../assets/bg5.jpg");
  background-size:100% 100% ;

  text-align: center;
}
.text{
  height:500px;
  font-size: 50px;
  //text-align: center;
  top: 50%;
  transform: translate(100%,150%);
}

</style>
