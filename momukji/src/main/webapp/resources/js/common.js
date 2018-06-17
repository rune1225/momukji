/******
		Simbyungki /
		dung8524@naver.com
						*****/
var winHeight = $(window).height();
var winWidth = $(window).width();

//높이 100%
$('.common_container').css({'min-height': winHeight - 242});
$('.member_container').css({'min-height': winHeight});

$('document').ready(function(){
	//gnb
	$('.btn_gnb').bind('click', function(){
		showNavigation();
	});
	$('.btn_slide_nav_close').bind('click', function(){
		hideNavigation();
	});
	$('.nav_dim_area').bind('click', function(){
		hideNavigation();
	});
	
	

});

//slide nav
function showNavigation(){
	$('.nav_dim_area').fadeIn(100);
	$('.slide_nav').animate({'left':'0'}, 400, 'easeOutCubic');
	$('.slide_nav .btn_slide_nav_close').delay(200).fadeIn(200);
	
}
function hideNavigation(){
	$('.slide_nav .btn_slide_nav_close').fadeOut(100);
	$('.slide_nav').animate({'left':'-60%'}, 400, 'easeInOutCubic');
	$('.nav_dim_area').delay(100).fadeOut(100);
}

//layer popup
function commonLayerOpen(thisClass, sessionflag){
	
	if(sessionflag=='true'){

		$('.'+thisClass).fadeIn();
		
	}else{
		if (confirm("로그인 하시겠습니까?") == true){
		    goPage('/customer/login');
		}else{
		    return;
		}
	}
}
function pictureLayerOpen(thisClass, url){
	
	if(url==''){
		alert('이미지 준비중입니다.');
		
		return false;
	}
	
	$('.'+thisClass+' .layer_cont .picture').attr('src', url);
	
	$('.'+thisClass).show();
	
	var picYPosition = $('.'+thisClass).find('.picture').height() /2;
	
	$('.'+thisClass).find('.picture').css({'margin-top': -picYPosition});
	
}
function commonLayerClose(thisClass){
	$('.'+thisClass).fadeOut();
}

//�����ۼ� ���̾� ����
var nowPoint = $('.write_review .avg_point em').html();
$('.write_review .avg_point em').css({'width':(nowPoint * 10) +'%'});

//별점 +/- 클릭
$('.write_review .star_change').bind('click', function(){
	var i = parseInt($('.star_change').index(this) / 2);
	var w = 'plus';
	if($(this).hasClass('minus')){
		w = 'minus';
	}else{
		w = 'plus';
	}
	pointControl(i, w);
});


function pointControl(idx, where){
	var nowPoint = parseInt($('.write_review .star_point').eq(idx).text()*10);
	var standardPoint = 20;
	var changeWidth = nowPoint+(standardPoint*(where == 'minus' ? -1 : 1));
	if(changeWidth < 0){
		changeWidth = 0;
	}else if(changeWidth > 100){
		changeWidth = 100;
	}
	$('.write_review .star_point').eq(idx).animate({'width':changeWidth+'%'}, 200).text(changeWidth/10);
	$('.write_review .final_point').text(changeWidth/10);
}

//직접 별점 선택
$('.avg_point .direct .btn_direct').on('click', function(){
	var choiceIdx = $('.avg_point .direct .btn_direct').index(this) + 1;
	$('.write_review .star_point').animate({'width':(choiceIdx*20)+'%'}, 200).text(choiceIdx*2);
});

//custom selectbox
var select = $('select');
select.change(function(){
	var select_name = $(this).children("option:selected").text();
	console.log(select_name);
	$(this).siblings("label").text(select_name);
});

$('#submitreview').click(function() {
  console.log($('.write_review .form_box .textarea_box textarea').val());
  console.log(Number($('.write_review .form_box .point_area .avg_point em').text())/2);
  console.log($('select').children("option:selected").val());
  
  submitreviewAjaxLoad(Number($('.write_review .form_box .point_area .avg_point em').text())/2, $('.write_review .form_box .textarea_box textarea').val(), $('select').children("option:selected").val(), '../insertmenureview');
  
});

//공용 페이지 이동
function goPage(url) {
	console.log(url);
	if(url.indexOf("http") == 0){
		window.location.href = decodeURIComponent(url);
	}else{
		window.location.href = decodeURIComponent(url);
	}
}

//공용 뒤로가기
function navback() {
	
	if (history.length > 0) {
		history.go(-1);
	}
	else {
		alert('뒤로가기 할 페이지가 없습니다.');
	}
	
}