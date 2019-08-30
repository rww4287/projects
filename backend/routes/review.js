var express = require('express');
var router = express.Router();
var reviews = require('../reviews.json');
const models = require('../models');

const fs = require('fs');
const path = require('path');

router.get('/movie/reviews',function(req,res,next){
  
  models.review.findAll().then(function(results){
    res.json(results);
  }).catch(function(err){
    console.log("데이터 불러오기 실패");
  //  console.err();
  });


});

router.get('/movie/reviews/:id', function (req, res, next) {
    let pId = parseInt(req.params.id, 10);
    // console.log("id==="+id);
    // var review = reviews.filter(function (review) {
    //   return review.id == id;
    // });
    // res.send(review)
    models.review.findAll({
        where: {id : pId}
    }).then(function(result){
      console.log("a");
      res.json(result);
      console.log("b");
    }).catch(function(err){
      console.log("리뷰 데이터 조회 실패");
      console.log(err);
    });
  });


router.post('/movie/reviews/upload', function(req,res){
  let review = req.body;

  //get path
  const myPath = path.join(__dirname, '..', 'reviews.json');

  //read path
  fs.readFile(myPath, 'utf-8', (err,data)=> {
   // console.log("data name = " +data[1].name);
    let parsedData = JSON.parse(data);
    parsedData.push(review);
    fs.writeFile(myPath, JSON.stringify(parsedData), (err)=>{
      if(err) throw err;
      console.log('reviews update complete!');
      res.send('end');
    });
  });

  

});

module.exports = router;
