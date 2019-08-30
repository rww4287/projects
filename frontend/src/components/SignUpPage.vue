<template>
    <form method="post" @submit.stop.prevent="signUpSubmit">
        이름 <input type="text" name="name" required v-model="user.name" placeholder="이름을 입력 해주세요"> <br/>
        email <input type="text" name="email" required v-model="user.email" placeholder="id를 입력 해주세요"> <br/>
        password <input type="password" name="password" required v-model="user.password" placeholder="비밀 번호를 입력해주세요"> <br/>

        <div>
            <b-button type="submit" variant="outline-primary">SignUp</b-button>
        </div>
    </form>
</template>


<script>
export default{
    data () {
        return{
            user : { 
                name : "",
                email : '"',
                password : "",
            },
            response: ""
        };
    },

    methods : {
        signUpSubmit() {
            this.$http.post(`/user/signUp`,this.user)
            .then(res => {
                console.log('signUp success!');
                alert('회원이 되신걸 축하 드립니다!');
            })
            .catch(err => {
                console.log('signUp fail!');
            });
            this.$router.push({name : "index"});
        }
    },
    created(){
    }
}

</script>
