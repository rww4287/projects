var express = require('express');
var router = express.Router();
const models = require('../models');
const crypto = require('crypto');
//var session = require('express-session');
/* GET users listing. */
var client_id = 'z_4YTauqT6Bd_VjSHIPR';
var client_secret = 'z_4YTauqT6Bd_VjSHIPR';
var state = "RAMDOM_STATE";
var redirectURI = encodeURI("http://localhost:3000");
var api_url = "";

var app = express();

// app.use(session({
//   secret: 'keyboard cat',
//   resave: true,
//   saveUninitialized: true,
// }));

router.post('/signUp', function(req, res, next) {
  console.log("sign up node ")
  // body 정보가져와서 등록해주기. 
  let body = req.body;

  // 비밀번호에 쏠트 치기 
  let inputPassword = body.password;

  let salt = Math.round((new Date().valueOf() * Math.random())) + "";
  let hashPassword = crypto.createHash("sha512").update(inputPassword + salt).digest("hex");
  
  models.user.create({
    name : body.name, 
    email: body.email,
    password: hashPassword,
    salt : salt
  })
  .then(result=>{
    console.log("회원가입 성공");
  }).catch(err=>{
    console.log("회원 가입 에러");
    console.log(err);
  })

});

router.post('/login',function(req, res, next){
  console.log("login node");
  console.log("email="+req.body.email);
  console.log("passowrd="+req.body.password);
  let body = req.body;
  var user =[];

  models.user.findOne({
    where : {email : body.email}
  })
  .then (result => {
   // console.log("dsfsdf");
    let dbPassword = result.dataValues.password;
    let inputPassword = body.password;
    let salt = result.dataValues.salt;

    let hashPassword = crypto.createHash("sha512").update(inputPassword+salt).digest("hex");

    if(dbPassword == hashPassword){
      console.log("비밀 번호 일치! 로그인이 완료 되었습니다.");
      //req.session.email = body.email;
      // req.session.user = {
      //   "name" : result.dataValues.name,
      //   "email" : body.email,
      //   "password" : dbPassword
      // }
      // req.session.save();
      user.push(result.dataValues.name);
      user.push(body.email);
      
      res.json(user);
      // 이름을 보내주기 ... 세션에 set 할수 있도록 ! -> 나중에 글 작성시 사용가능 
    }
    else {
      console.log("비밀 번호 불 일치! 로그인에 실패 하였습니다.");
    }

  })
  .catch(err=> {
    console.log("error! 존재하지 않는 이메일 , 혹은 이메일을 찾지 못하였습니다.");
    console.log(err);

  });
});

// router.get('/naverlogin', function(req, res){
//   api_url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + client_id + '&redirect_uri=' + redirectURI + '&state=' + state;
//    res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
//    res.end("<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>");
//  });



module.exports = router;
