var express = require('express');
var router = express.Router();
var movies = require('../movies.json');
const models = require('../models');

const fs = require('fs');
const path = require('path');

router.get('/movie',function(req,res,next){
    //res.send(movies);
    console.log("~~~ get home page");
    // res.sendFile(path.join(__dirname, '../public', 'index.html'));
    //  var isUser = false;
    //  if (req.session.user){
    //    isUser = true;
    //    console.log(isUser);
    //    res.json(isUser);
    //  }
    //  console.log(isUser);
    //  res.json(isUser);

    models.movie.findAll().then(function(results){
        res.json(results);
    }).catch(function(err){
      console.log("데이터 불러오기 실패");
      console.err();
    });
});

router.get('/movie/:id', function (req, res) {
  // var id = parseInt(req.params.id, 10);
  // //var id = req.params
  // console.log("id==="+id);
  // var movie = movies.filter(function (movie) {
  //   return movie.id == id;
  // });
  // res.send(movie)
  var results = [];
  let pramid = parseInt(req.params.id, 10);
  console.log("movie id = " + pramid);

  models.movie.findOne({
      where: {id: pramid}
  }).then(function(result1){
        //res.json(result); // 리턴 받은 쿼리에 대한 실행
        results.push(result1);
  }).catch(function(err){
    console.log("데이터 조회 실패");
    console.log(err);
  });
  models.review.findAll({
    where: {movieId: pramid}
  }).then(function(result2){
      //res.json(result2);
      results.push(result2);
      res.json(results);

  }).catch(function(err){
    console.log("리뷰 조회 실패");
    console.log(err);
  });
});


router.post('/movie/upload', function(req,res){
  // let movie = req.body;
  // movie.year = Number(movie.year);

  // //get path
  // const myPath = path.join(__dirname, '..', 'movies.json');

  // //read path
  // fs.readFile(myPath, 'utf-8', (err,data)=> {
  //  // console.log("data name = " +data[1].name);
  //   let parsedData = JSON.parse(data);
  //   parsedData.push(movie);
  //   fs.writeFile(myPath, JSON.stringify(parsedData), (err)=>{
  //     if(err) throw err;
  //     console.log('movies update complete!');
  //     res.send('end');
  //   });
  // });
  
  let body = req.body;
  body.year = Number(body.year);
  models.movie.create({
    name: body.name,
    year: body.year,
    director: body.director,
    poster: body.poster
  })
  .then(result => {
    console.log("데이터 추가 완료");
     
  })
  .catch(err=>{
    console.log("데이터 추가 실패");
  })

});

router.get('/movie/edit/:id', function(req,res){

  console.log("edit get")
  let pid = parseInt(req.params.id, 10);

  
  models.movie.findAll({
    where : {id: pid}
  })
  .then(result =>{
    res.json(result);
  })
  .catch(err=>{
    console.log("업데이트 할 데이터 조회 실패");
    console.err();
  })

});
router.put('/movie/edit/:id',function(req, res, next){

  console.log("edit put");
  let pId = req.params.id;


  console.log("edit put id="+pId);
  
  req.body.edityear = Number(req.body.year);
  
  console.log("edit year = " + req.body.year);
  console.log("edit name = " + req.body.name);

  models.movie.update({
    name: req.body.name,
    year: req.body.year,
    director: req.body.director,
    poster: req.body.poster
  },{
    where: {id : pId}
  })
  .then(result => {
    console.log("데이터 업데이트 완료");
    res.json(result);
     
  })
  .catch(err=>{
    console.log("데이터 업데이트 실패");
  })

});
router.delete('/movie/delete/:id',function(req, res, next){

  console.log("delete! ");
  let deId = req.params.id;
  
  console.log("delete id = "+deId);

  models.movie.destroy({
    where: {id : deId}
  })
  .then(result => {
    console.log("데이터 삭제 완료오오옹");
  }).catch(err=>{
    console.log("데이터 삭제 실패ㅠㅠ");
  });

});
//리뷰 작성 
router.post('/review/upload/:movieId', function(req,res){
  
  console.log("리뷰작성 js")
  let movieId = req.params.movieId;
  let body = req.body;

  body.year = Number(body.year);
  models.review.create({

    review_writer: body.writer,
    review_title: body.title,
    review_content: body.content,
    review_rate: body.rate,
    movieId: movieId,
  })
  .then(result => {
    console.log("리뷰 등록 완료");
     
  })
  .catch(err=>{
    console.log("리뷰 등록 실패");
  })

});
module.exports = router;
