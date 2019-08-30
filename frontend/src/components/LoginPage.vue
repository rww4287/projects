
<template>

    <form method="post" @submit.stop.prevent="loginSubmit">

        email <input type="text" name="email" required v-model="user.email" placeholder="이메일을 입력해 해주세요"> <br/>
        password <input type="password" name="password" required v-model="user.password" placeholder="비밀 번호를 입력해주세요"> <br/>

        <div>
            <b-button type="submit" variant="outline-primary">Login</b-button>
        </div>
        <!-- <div id="app">
            <KakaoLogin
            api-key="8fa3e8020ffc5a27128bdc111697ea93"
            image="kakao_login_btn_large"
            :on-success=onSuccess
            :on-failure=onFailureg
            />
        </div> -->

        <div id="kakao" v-on:click="Kakaologin">
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=d73cb499f7ebbfa8b7101e08e0cfe930&redirect_uri=http://localhost:3000/&response_type=code"><img height='50' src="../assets/kakao_account_login_btn_large_wide_ov.png"> </a>
        </div>
        
        <div id="naver" v-on:click="Naverlogin">
            <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=z_4YTauqT6Bd_VjSHIPR&redirect_uri=http://localhost:3000/movie&response_type=code"><img height='50' src='https://static.nid.naver.com/oauth/big_g.PNG'/></a>
        </div>

        
    </form>

</template>


<script>

import KakaoLogin from 'vue-kakao-login'
import Vue from 'vue'



// let onSuccess = (data) => {
//   console.log(data)
//   console.log("success")
//   this.$router.push({name : "index"});
// }
// let onFailure = (data) => {
//   console.log(data)
//   console.log("failure")
// }


export default{
    // name: 'App',
    // components: {
    //     KakaoLogin
    // },

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
        loginSubmit() {
            this.$http.post(`/user/login`,this.user)
            .then(res => {
                console.log('login success!');
                var userName = res.data[0];
                var userEmail = res.data[1];

                //this.$store.state.login = "login";
                this.$session.start()
                this.$session.set('userName', userName)
                this.$session.set('userEmail',userEmail)


                // console.log(res.data);
                // document.cookie = `accessToken=${res.data.data.accessToken}`;
                // axios.defaults.headers.common['x-access-token'] = res.data.data.accessToken      
                this.$router.push({name : "index"});      
            })
            .catch(err => {
                console.log('login fail!');
            });
            this.$router.push({name : "index"});
        },
      
        Naverlogin() {
            // let url="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=z_4YTauqT6Bd_VjSHIPR&redirect_uri=http://localhost:3000&response_type=code";
            // this.$http.get(url)
            // .then(res=>{
            //     console.log('naver login api sucess!');
            //     alert('네이버로그인 성공!');
            // })
            // .catch(err =>{
            //     console.log('naver login api fail!');
            // });
            // this.$router.push({name : "index"});
            // 콜백 처리.....
        },
        Kakaologin() {
            // 콜백 함수 예외 처리 
        }
    

    },
    created(){
    }
}

</script>
