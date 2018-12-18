$(document).ready(function() {
    $.ajax({
		url: 'http://10.129.32.15:8080/user/1/challenge',
		type: 'get',
		dataType: 'json',
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i, userChallenge){
				var chall ='<section class="challengetile"><div class="challengetile__info"><img src="' + userChallenge.challenge.linktToLogo + '" alt=""><div class="challengetile__text"><p>' + userChallenge.challenge.category.name + '</p><p>' + userChallenge.challenge.description +'</p><p>'+ userChallenge.challenge.unitToMeasure +'</p></div><div class="progressbar"><div class="progressbar__status" style="width:'+  +'%"></div></div></div><div class="challengetile__add"><p>+</p></div></section>';
				$("#challenges").append(chall); 
			});  
		},
		error : function(jqXHR, textStatus, errorThrown) {
		  console.log(errorThrown);
		}
	});
});