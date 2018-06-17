/**
 * 모먹지 Ajax
 */
//로그아웃
function logOutAjaxLoad($mc_no){
	
	$.ajax({
		url : '/customer/logout',
		data : {
				mc_no : $mc_no},
		type : "GET",
		dataType:"json",
		beforeSend:function() {
	        //show&hide 컨트롤
	        $( ".page_loading" ).show(); 
	    }, 
		success:function(args){	//작성 완료 메시지
			
			$.each(args, function(key,state) {
		     	if(key == 'result'){
		     		if(state=='success'){
		     			
		     			alert('로그아웃되었습니다.');
		     			
		     			window.location.reload();
		     			
		     		}
		     	}
		    });
			
		},
		complete: function() {
			
			$( ".page_loading" ).hide();
			
	    },
	    error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

//이메일 중복 확인
function duplicationEmailCheckAjaxLoad($mc_email){
	
	$.ajax({
		url : '/customer/duplicationEmailCheck',
		data : {
			mc_email : $mc_email},
		type : "GET",
		dataType:"json",
		beforeSend:function() {
	    }, 
		success:function(args){	//작성 완료 메시지
			$.each(args, function(key,state) {
				
		     	if(key == 'result'){
		     		if(state == 0){
		     			$('#mc_email').css({'background': '#B79D37'});
		    		  	emailchk = true;
		    		  	return;
		     		}else{
		     			$('#mc_email').css({'background': '#FF2424'});
		     			return;
		     		}
		     	}
		    });
			
		},
		complete: function() {
	    },
	    error:function(e) {
	    	return false;
	    }
	});
}

//모먹지 나우 리스트
function momukjinowajaxLoad($nowlat, $nowlng, $mainsort, $mfc_no, $path, $url, $page, $partnership) {
	
	$.ajax({
		url : $url,
		data : {nowlat : $nowlat,
				nowlng : $nowlng,
				mainsort   : $mainsort,
				mfc_no : $mfc_no,
				page   : $page,
				partnership : $partnership},
		path : $path,
		type : "GET",
		beforeSend:function() {
			$( ".page_loading" ).show();
	        
	    }, 
		success:function(html){
			
			if($page==0)
				this.path.empty();
			
			var _html = $.parseHTML(html);
			
			this.path.html(_html);
			
			nowpage = nowpage + 1;
			
		},
		complete: function() {
			
			$( ".page_loading" ).hide();
			
	    }
	});
}

//모먹지 전체 메뉴 리스트
function entiremenuAjaxLoad($mr_no, $path, $parentpath, $url) {
	
	$.ajax({
		url : $url,
		data : {
				mr_No : $mr_no,
				ajaxyn : '1'},
		path : $path,
		parentpath : $parentpath,
		type : "GET",
		beforeSend:function() {
			
	        //show&hide 컨트롤
	        console.log($('.store_detail .ajaxchange .all_menu').length);
	        if($('.store_detail .ajaxchange .all_menu').length > 0){
	        	$('.store_detail .ajaxchange').children().hide();
				$('.store_detail .ajaxchange .all_menu').show();
				return false;
			}
			
			$( ".page_loading" ).show();
	        
	    }, 
		success:function(html){
			
			this.path.children().hide();
			
			var _html = $.parseHTML(html);
			
			//console.log(_html);
			
			this.path.append(_html);
			
			//메뉴clicked
			$('.store_detail .all_menu .main_menu').on('click', function(){
				if($(this).closest('li').hasClass('show')){
					$(this).closest('li').removeClass('show')
				}else{
					$(this).closest('li').addClass('show')
				}
			});	
			
		},
		complete: function() {
			
			$( ".page_loading" ).hide();
			// �ε���hidden
			
	    }
	});
}

//모먹지 메뉴 리뷰
function menureviewAjaxLoad($mr_no, $mm_no, $path, $url, $page) {
	
	$.ajax({
		url : $url,
		data : {
				mm_No : $mm_no,
				mr_No : $mr_no,
				page : $page},
		path : $path,
		type : "GET",
		beforeSend:function() {
			
	        //show&hide 컨트롤
	        $( ".page_loading" ).show(); 
	        //console.log($(this.path).length);
	        if($('.store_detail .ajaxchange .review').length > 0 && $page == 0){
	        	$('.store_detail .ajaxchange .review').remove();
	        	$('.store_detail .ajaxchange .layer_popup_wrap write_review').remove();
			}
	        
	    }, 
		success:function(html){
			
			var _html = $.parseHTML(html);
			
			if($page > 0){
				
				$('.store_detail .review .review_list').append(_html);
				//this.path.find('ul.review_list').append(_html);
				
			}else{
			
			this.path.children().hide();
			
			this.path.append(_html);
			
			}

		},
		complete: function() {
			
			$( ".page_loading" ).hide();
			
	    }
	});
}

//모먹지 리뷰 작성 내 메뉴명 리스트
function menunamelistAjaxLoad($mr_no, $url) {
	
	$.ajax({
		url : $url,
		data : {mr_No : $mr_no},
		type : "GET",
		dataType:"json",
		beforeSend:function() {
			
	    }, 
		success:function(args){	
			console.log(args);
			$.each(args, function(key,state) {
		        $("#choice_menu").append('<option value="' + key + '">' + state + '</option>');
		    });
			
		},
		complete: function() {
			
	    },
	    error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

//리뷰 작성 제출
function submitreviewAjaxLoad($mmr_score, $mmr_contents, $mm_no, $url) {
	
	$.ajax({
		url : $url,
		data : {
				mmr_Score : $mmr_score,
				mmr_Contents : $mmr_contents,
				mm_No : $mm_no},
		type : "GET",
		dataType:"json",
		beforeSend:function() {
			
	        //show&hide 컨트롤
	        $( ".page_loading" ).show(); 
	        //console.log($(this.path).length);
	        if($('.store_detail .ajaxchange .review').length > 0){
	        	$('.store_detail .ajaxchange .review').remove();
			}
	        
	    }, 
		success:function(args){	//작성 완료 메시지
			
			$.each(args, function(key,state) {
		     	if(key == 'result'){
		     		if(state=='1'){
		     			alert('메뉴리뷰 작성이 완료되었습니다. 감사합니다.');
		     			//해당 메뉴 리뷰 리스트
		     			selectmenureview($mm_no);
		     			commonLayerClose('write_review');
		     			
		     		}else
		     			alert('리뷰 저장을 완료하지 못했습니다.');
		     	}else{
		     		alert('리뷰 작성이 실패되었습니다.');
		     	}
		    });
			
		},
		complete: function() {
			
			$( ".page_loading" ).hide();
			
	    },
	    error:function(e) {
	    	alert(e.responseText);
	    }
	});
}