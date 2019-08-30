<template>

    <form method="put" @submit.stop.prevent="mySubmitEdit">
        <div>
            <label for="name">영화 제목</label>
            <input type="text" name="name" required v-model="movie.name" v-bind="movie.name">
        </div>
        <div>
            <label for="year">개봉 년도</label>
            <input type="number" name="year" required v-model="movie.year" v-bind="movie.year">
        </div>
        <div>
            <label for="director">감독</label>
            <input type="text" name="director" required v-model="movie.director" v-bind="movie.director">
        </div>
        <div>
            <label for="poster">포스터링크</label>
            <input type="text" name="poster" required v-model="movie.poster" v-bind="movie.poster">
        </div>
        <div>
            <b-button type="submit" variant="outline-primary">Edit</b-button>
        </div>
    </form>

</template>



<script>
export default {
    data () {
        return{
            movie : { 
                id : "",
                name : "",
                year: 0,
                director : "",
                poster : ""
            },
            response: ""
        };
    },
  created: function() {
    var id = this.$route.params.id;
    this.$http.get(`/api/movies/movie/edit/${id}`)
    .then((response) => {
      this.movie = response.data[0];
    });
  },
 methods : { 
        mySubmitEdit() {
            let id = this.$route.params.id;
            this.$http.put(`/api/movies/movie/edit/${id}`,this.movie)
            .then(res => {
                console.log('edit success!');
            })
            .catch(err => {
                console.log('edit fail!');
            });
            this.$router.push({name : "index"});
        }
    },
  data: function() {
    return {
      movie: {}
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