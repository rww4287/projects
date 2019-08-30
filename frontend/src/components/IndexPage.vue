<template>
    <div class="movies"><br/>
        <h1> Movie List </h1>
            <div class="row">
                <div class="col-xs-6 col-md-4" v-for="movie in movies" :key="movie.id">
                        <a href="#" class="thumbnail">
                            <router-link :to="{name : 'show', params : {id:movie.id}}">           
                                <img v-bind:src="movie.poster" class="poster">
                                 <strong>{{ movie.name }}</strong> <br/>  <i>감독 : {{movie.director}}</i>
                            </router-link><br>
                        </a>
                    
                 </div>

            </div>
            <nav>
                <ul class="pager">
                    <li><router-link :to="{name : 'write'}">추가하기</router-link></li>
                </ul>
            </nav>
    </div>

</template>

<script>
export default{
    created() {
        this.$http.get(`/api/movies/movie`)
        .then((response)=>{
            this.movies = response.data;
        });
    },
    data () {
        return{
            movies: []
        }
    }
}

</script>


<style>
.poster {
    width: 300px;
    height: 450px;
}
</style>

