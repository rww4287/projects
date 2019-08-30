var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

// moive.js 파일 사용 가능하도록  등록하기
var movies = require('./routes/movies');
var reviews = require('./routes/review');
var methodOverride = require('method-override');
// mysql연동 
var mysql = require('mysql');

var app = express();
var server = require('http').createServer(app);
let model = require('./models/index.js');
var session = require('express-session');
var passport = require('passport')
              , LocalStrategy = require('passport-local').Strategy;
//var cookieSession = require('cookie-session');
var flash = require('connect-flash');



app.use(methodOverride('_method'));

model.sequelize.sync().then(()=>{
  console.log("db 연결 성공");
}).catch(err=>{
  console.log("연결 실패");
  console.log(err);
})

//mysql 연동하기 위한 커넥션 
var connection = mysql.createConnection({
  hostname : "localhost",
  user : "root",
  password : "1234",
  port : 3306,
  database : "movie" 
});

connection.connect(function(err){
  if(err) {
    console.error('mysql connection error');
    console.error(err);
  }else {
    console.log("연결에 성공하였습니다.");
  }
});
app.use(session({
  key: 'sid', 
  secret: 'secret',
  resave: false,
  saveUninitialized: true,
  cookie: {
    maxAge: 24000 * 60 * 60 // 쿠키 유효기간 24시간
  }
}))
app.use(flash());
app.use(passport.initialize());
app.use(passport.session());

app.use(require('connect-history-api-fallback')());

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
// app.use('/users', usersRouter);
app.use('/api/movies', movies);
app.use('/api/reviews', reviews);
app.use('/user',usersRouter);



// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
