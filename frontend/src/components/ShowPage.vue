<template>
    <div>
        <h1>{{movie.name}}</h1>
    
          <img v-bind:src="movie.poster" class="poster"><br/>
          <strong>{{ movie.name }}</strong> -  <i>{{movie.director}}</i> <br/>



          <nav>
            <ul class="pager">
              <li class="previous">
                <router-link :to="{name : 'index', params : {id:movie.id}}">
                  <span aria-hidden="true">&larr;</span> 
                      previous
                </router-link></li>

              <form method="delete" @submit.stop.prevent="mySubmitDelete">
                <b-button type="submit" variant="outline-primary">Delete</b-button>
              </form>
              <router-link :to="{name : 'reviewUpload', params : {id:movie.id}}">
                <b-button type="submit" variant="outline-primary">write Review</b-button>
              </router-link>

              <li class="next">
                <router-link :to="{name : 'reviewEdit', params :{id: movie.id}}">
                  <span aria-hidden="true">&rarr;</span>
                  Update
                </router-link></li> 
            </ul>
          </nav>

          영화 리뷰 
          <li v-for="review of reviews" :key="review.id">
                {{ review.review_writer}} - {{review.review_content}} | {{review.review_rate}}
            </li>  

         

    </div>


</template>



<script>
import axios from 'axios';
export default {
  created: function() {
    var id = this.$route.params.id;
  
    this.$http.get(`/api/movies/movie/${id}`)
    .then((response) => {
      this.movie = response.data[0];
      this.reviews = response.data[1];

    });
      

  },
  methods : {
    mySubmitDelete(){
        let id = this.$route.params.id;
        axios.delete(`/api/movies/movie/delete/${id}`,this.moive)
        .then(res =>{
            console.log('delete success!');
        })
        .catch(err=>{
            console.log('delete fail!');
        });
        this.$router.push({name:"index"});


    }
  },
  data: function() {
    return {
      movie: [],
      reviews : []
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