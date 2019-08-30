
<template>

    <form method="post" @submit.stop.prevent="mySubmit">
        <div>
            <label for="name">영화 제목</label>
            <input type="text" name="name" required v-model="movie.name">
        </div>
        <div>
            <label for="year">개봉 년도</label>
            <input type="number" name="year" required v-model="movie.year">
        </div>
        <div>
            <label for="director">감독</label>
            <input type="text" name="director" required v-model="movie.director">
        </div>
        <div>
            <label for="poster">포스터링크</label>
            <input type="text" name="poster" required v-model="movie.poster">
        </div>
        <div>
            <b-button type="submit" variant="outline-primary">upload</b-button>
        </div>
    </form>

</template>


<script>
export default{
    data () {
        return{
            movie : { 
                id : "",
                name : "",
                year : 0,
                director : "",
                poster : ""
            },
            response: ""
        };
    },

    methods : {
        mySubmit() {
            this.$http.post(`/api/movies/movie/upload`,this.movie)
            .then(res => {
                console.log('upload success!');
                alert('영화가 등록 되었습니다!');
            })
            .catch(err => {
                console.log('upload fail!');
            });
            this.$router.push({name : "index"});
        }
    },
    created(){
        this.$http.get(`/api/movies`).then(response => {
            const movies = response.data;
            this.movie.id = movies.length+1;
        });
    }
}

</script>

