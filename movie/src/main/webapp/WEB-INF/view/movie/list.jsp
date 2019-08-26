<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/static/css/bootstrap.min.css"/>" rel="stylesheet">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
html, body, h1, p, a {padding:0;margin:0;line-height:1;font-family: 'Jeju Gothic', serif;} 
img {border: 0} 
div.imgTopic {position:relative;width:300px;height:420px;border: 1px solid #eee} 
div.imgTopic a {text-decoration: none} 
.thumbnail:hover {
opacity:0.4;
filter:alpha(opacity=40);
}
img.modalThumbnail{
    margin-top: 5px;
    margin-bottom: 20px;
    display: inline-block;
}
#modalVideo{
    margin-top: 5px;
    margin-bottom: 20px;
    z-index: 1;
    position: absolute;
    display: inline-block;
   	margin-left: 30px;

}
#content{
    margin-top: 250px;
    margin-bottom: 20px;
}
/* #modalVideoDiv{
	z-index: 1;
	position: relative;
	display:inline;
} */
#modalMovietitle {
	text-align : left;
	font-size:20px;
	font-weight: bold;
}
/* #playDiv{
	z-index: 2;
	position: absolute;
} */
#modalVideoPlay{
	z-index: 2;
	position: absolute;
	display:block;
	margin-top: 83px;
    margin-left: 340px;
}
div.title {position:absolute;z-index:1;left:10px;bottom:50px} 
div.title a{font-size:18px;font-weight:bold;color:#fff;}
div.title a:hover, a:focus {text-decoration:underline;}
p.content a {font-size:12px;} 
span.genre {position:absolute;display:block;left:0;bottom:0;width:295px;height:35px;padding:0 0 5px 15px;background:url(‘./blackOpacity.png’) repeat;color:#000000;}
</style>


<title>Movie</title>
	<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	
	<script type="text/javascript">
		$().ready(function(){
			$(".imgTopic").find(".title").hide();
			$(".imgTopic").find(".genre").hide();
			$(".thumbnail").mouseover(function(){
				$(this).closest('.imgTopic').find('.title').show();
				$(this).closest('.imgTopic').find('.genre').show();
			});
			$(".thumbnail").mouseout(function(){
				$(this).closest('.imgTopic').find('.title').hide();
				$(this).closest('.imgTopic').find('.genre').hide();
			});
			
			$("#myModal").on("show.bs.modal", function (event) {
				
				var img = $(event.relatedTarget); // Button that triggered the modal
				//console.log(img.data('link'));
				var title = img.data('title'); // Extract info from data-* attributes
				var thumbnail = img.attr('src');
				var model = img.data('model');
				var story = img.data('story');
				var audiacc = img.data('audiacc');
				var genre = img.data('genre');
				var screeninggrade = img.data('screeninggrade');
				var actor = img.data('actor');
				var director = img.data('director');
				var opendate = img.data('opendate');
				var time = img.data('time');
				var video = img.data('video');
				var reservation = img.data('reservation');
				var videolink = img.data('link');
				//var modal = $(this);
				
				$('#modalTitle').text(title);
				$('#modalThumbnail').attr('src',thumbnail);
				$('#modalMovietitle').text(title);
				$('#modalIntro').text("개봉일  "+ opendate + " | 누적관객수  "+ audiacc +" "+ genre );
				$('#modalDetail').text("상영등급 " +screeninggrade+"| 상영시간 "+ time);
				$('#modalActor').text("출연 " + actor);
				$('#modalDirector').text("감독 " + director);
				$('#modalStory').text(story);
				
				$('#modalVideo').attr('src',video);
				
				
				$('#reservationBtn').click(function(){
					window.open(reservation);
					document.location.reload();
				});
				$('#modalVideoPlay').click(function(){
					window.open(videolink);
					document.location.reload();
				});
				$('#reviewBtn').click(function(){
					// TO DO 리뷰 게시판으로 가기.
				});
				$('#modalVideoPlay').mouseover(function(){
					var video = $(this).closest('.videoDiv').find('#modalVideo');
					$(video).css('opacity','0.4');
					$(video).css('filter','alpha(opacity=40)');
				});
				$('#modalVideoPlay').mouseout(function(){
					var video = $(this).closest('.videoDiv').find('#modalVideo');
					$(video).css('opacity','1.0');
					$(video).css('filter','alpha(opacity=100)');
				});
				
			});
			
		});
	</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<a class="navbar-brand" href="#">Movie</a>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
<!-- 				<li><a href="#">리뷰</a></li>
				<li><a href="#">community</a></li> -->
			</ul>
		</div>
	</nav>
	
	<div class="container">

		<div class="row">
		
			<c:forEach items="${movieList}" var="movie">
				<div class="col-xs-4">
					<div class="imgTopic">
						<div class="title" style="font-size: 25px;">${movie.movieName}<br/></div>
							 <p class="content">
							 	<a href="#">
							 		<img class="thumbnail" src="${movie.thumbnail}" data-toggle="modal" data-target="#myModal" data-title="${movie.movieName}"
							 				data-story="${movie.story}" data-audiacc="${movie.audiAcc}" data-genre="${movie.genre}"
							 				data-screeninggrade="${movie.screeningGrade}" data-actor="${movie.actor}" data-director="${movie.director}" 
							 				data-opendate="${movie.openDate}" data-time="${movie.time}" data-video="${movie.video}"
							 				data-reservation="${movie.reservation}" data-link="${movie.videoLink}"
							 				width="300px" height="420px" />	
						  			<span class="genre">
										<c:forEach items="${movie.genre}" var="genre">
												${genre} |  
										</c:forEach><br/>
										누적 관객수 | ${movie.audiAcc}
									</span>
								</a>
							</p>
					</div>
				</div>
			
			</c:forEach>
		</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" > 
						<div class="modal-dialog"> 
							<div class="modal-content"> 
								<div class="modal-header"> 
									<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
									</button> <h4 class="modal-title" id="modalTitle"></h4> 
								</div> 
								<div class="modal-body"> 
									<img class="modalThumbnail" id="modalThumbnail" src=""  align="left"  width="180px" height="220px"/>
								    <div class="videoDiv">
									 <img id="modalVideo" width="300px" height="220px"/>
									   		<a href="#"><img src="static/img/play.png" id="modalVideoPlay" width="40px" height="40px" /></a>
									</div>
									<div id="content" style="position: relative;">
										<div id="modalMovietitle"></div><br/>
										<div id="modalIntro" align="left"></div>
										<div id="modalDetail" align="left"></div>
										<div id="modalActor" align="left"></div>
										<div id="modalDirector" align="left"></div><br/>
										<div id="modalStory" align="left"></div><br/>
									</div>
									
									
								</div> 
								<div class="modal-footer"> 
									<button type="button" class="btn btn-primary" id="reviewBtn">리뷰등록</button>
									<button type="button" class="btn btn-primary" id="reservationBtn">예매하기</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button> 
								</div> 
							</div> 
						</div> 
					</div>


	</div>
	
</body>
</html>