<template>
    <div>
         <h1>{{review.review_title}}</h1> 

           작성자: {{review.review_writer}} | <br/> 
           {{review.review_content}} | 별점 : {{review.review_rate}}

        <nav>
            <ul class="pager">
              <li class="previous">
                <router-link :to="{name : 'reviews', params : {id:review.id}}">
                  <span aria-hidden="true">&larr;</span> 
                      previous
                </router-link></li>
              <!-- <li class="next">
                <router-link :to="{name : 'reviews', params : {id:(review.id+=1)}}"> 
                  <span aria-hidden="true">&rarr;</span>
                  수정하기
                </router-link></li>  -->
            </ul>
          </nav>
    </div>
    


</template>



<script>
export default {
  created: function() {
    var id = this.$route.params.id;
    this.$http.get(`/api/reviews/movie/reviews/${id}`)
    .then((response) => {
      this.review = response.data[0];
    });
  },
  data: function() {
    return {
      review: []
    }
  }
}
</script>
