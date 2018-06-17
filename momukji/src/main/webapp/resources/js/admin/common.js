/******
		2016.11.17 ~ 
		Coded by Rune
						*****/

$(document).ready(function(){
	
	//var contentHeight = $('#content').height();
	//$('#wrapper').css({'height':contentHeight+35});
	//$('#side_menu').css({'height':contentHeight});
	
	var winWidth = $(window).width();
	var sideNavWidth = $('#side_menu').width();
	$('#content').css({'width':winWidth-sideNavWidth-64});
});

$(window).load(function(){

	if($('.content_header > div h2 img').length > 0){
		var clientLogoHeight = $('.content_header > div h2 img').height();
		if(clientLogoHeight > 35){
			var minusResult = clientLogoHeight - 35;
			$('.content_header > div h2 img').css({'margin-top':-minusResult/2});
		}
	}

});

$(window).resize(function(){
	
	var winWidth = $(window).width();
	var sideNavWidth = $('#side_menu').width();
	$('#content').css({'width':winWidth-sideNavWidth-64});
	
});

//업장양식검증
function resFormValidation(){

	/*
	if($.trim($('#mr_Id').val()) == ''){
   		alert("이메일을 입력하세요.");
   		$('#mr_Id').focus();
   		return false;
  	}else{
   		var emailExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
   		if(!emailExp.test($('#mr_Id').val())){
			alert("이메일 주소가 유효하지않습니다.");
			$('#mc_email').focus();
    		return false;
   		}
  	}
  	*/
	
	if($.trim($('#mr_Name').val()) == ''){
		alert("업장명을 입력하세요.");
   		$('#mr_Name').focus();
	    return false;
  	}
  	
  	if($.trim($('#mfc_No option:selected').val()) == ''){
  		alert("음식 카테고리를 선택하세요.");
   		$('#mfc_No').focus();
   		return false;
  	}
  	
  	if($.trim($('#mlc2_No option:selected').val()) == ''){
  		alert("지역 카테고리를 선택하세요.");
   		$('#mlc1_No').focus();
   		return false;
  	}
  	
  	if($.trim($('#mr_Address').val()) == ''){
  		alert("주소를 입력하세요.");
   		$('#mr_Address').focus();
   		return false;
  	}
  	
  	if($.trim($('#mr_Phone').val()) == ''){
  		alert("연락처를 입력하세요.");
   		$('#mr_Phone').focus();
   		return false;
  	}
  	
  	if($.trim($('#mr_Position_Lat').val()) == ''){
  		alert("위도를 입력하세요.");
   		$('#mr_Position_Lat').focus();
   		return false;
  	}
  	
  	if($.trim($('#mr_Position_Lng').val()) == ''){
  		alert("경도를 입력하세요.");
   		$('#mr_Position_Lng').focus();
   		return false;
  	}
  	
  return true;
  
}

//메뉴양식검증
function menuFormValidation(){

	if($.trim($('#mm_Name').val()) == ''){
		alert("메뉴명을 입력하세요.");
   		$('#mm_Name').focus();
	    return false;
  	}
  	if($.trim($('#mm_Price').val()) == ''){
  		alert("가격을 입력하세요.");
   		$('#mm_Price').focus();
   		return false;
  	}
  	if($.trim($('#mmc_No option:selected').val()) == ''){
  		alert("메뉴 카테고리를 선택하세요.");
   		$('#mmc_No').focus();
   		return false;
  	}
  	if($.trim($('#mr_No').val()) == ''){
  		alert("업장고유번호가 없습니다.");
   		return false;
  	}
  	
  return true;
  
}

