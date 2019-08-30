<template>
  <div id="app">
    <ul class="nav nav-tabs">
      <li @click="isActive= !isActive" :class="{active : isActive}"><router-link :to="{name : 'index'}">Movie</router-link></li>
      <li @click="isActive= !isActive" :class="{active : isActive}"><router-link :to="{name : 'reviews'}">Reviews</router-link></li>
      
      <li v-if="!this.$session.exists()" @click="isActive= !isActive" :class="{active : isActive}"><router-link :to="{name : 'signUp'}">SignUp</router-link></li>
      <li v-if="!this.$session.exists()" @click="isActive= !isActive" :class="{active : isActive}"><router-link :to="{name : 'login'}">Login</router-link></li>
      
    </ul>
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App'


}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
