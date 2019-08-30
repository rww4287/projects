<template>
  <div>
    <table>
      <tr>
        <th>NO</th>
        <th>TITLE</th>
        <th>RATE</th>
        <th>WRITER</th>
      </tr>
      <tr v-for="review in reviews" :key="review.id">
        <td>{{ review.id }}</td>
        <router-link :to="{name: 'reviewDetail', params: {id:review.id}}"><td>{{ review.review_title }}</td></router-link>
        <td>{{ review.review_rate }}</td>
        <td>{{ review.review_writer }}</td>
      </tr>
    </table>
    <nav>
        <ul class="pager">
            <li><router-link :to="{name : 'reviewUpload'}">글쓰기</router-link></li>
        </ul>
    </nav>

  </div>
</template>

<script>
export default{
    created() {
        this.$http.get(`/api/reviews/movie/reviews`)
        .then((response)=>{
            this.reviews = response.data;
        });
    },
    data () {
        return{
            reviews: []
            //,movies: []
        }
    }
}

</script>
