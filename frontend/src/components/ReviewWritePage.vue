
<template>

    <form method="post" @submit.stop.prevent="reviewSubmit">
        <input type="text" name="title" required v-model="review.title" placeholder="제목을 입력해보세요"> <br/>
        <!-- <input type="text" name="writer" required v-model="review.writer" placeholder="작성자"> <br/> -->
            <b-form-textarea 
            id="textarea-rows"
            rows="8"
            name="content"
            required v-model="review.content"
            placeholder="리뷰 내용을 입력해보세요"
            ></b-form-textarea>
            <input type="number" name="rate" required v-model="review.rate">
        <div>
            <b-button type="submit" variant="outline-primary">upload</b-button>
        </div>
    </form>

</template>


<script>
export default{
    data () {
        return{
            review : { 
                id : "",
                writer : this.$session.get('userName'),
                title : "",
                content : "",
                rate : "", 
                movie_id: ""
            },
            response: ""
        };
    },

    methods : {
        reviewSubmit() {
            let id = this.$route.params.id;
            this.$http.post(`/api/movies/review/upload/${id}`,this.review)
            .then(res => {
                console.log(' 리뷰 upload success!');
                alert('리뷰가 등록 되었습니다!');
            })
            .catch(err => {
                console.log(' 리뷰 upload fail!');
            });
            this.$router.push({name:"reviews"});
        }
    },
    created(){
        this.$http.get(`/api/reviews/movie/reviews`).then(response => {
            const reviews = response.data;
            this.review.id = reviews.length + 1;
        });
    }
}

</script>

