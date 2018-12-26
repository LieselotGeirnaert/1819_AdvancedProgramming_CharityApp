$(document).ready(function() {
	$.ajax({
		url: 'http://10.129.32.15:8080/category',
		type: 'get',
		dataType: 'json',
		beforeSend: function(xhr, settings) { 
            var access_token = readCookie("access_token");
            xhr.setRequestHeader('Authorization','Bearer ' + access_token);
        },
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i,category){
				var option="<option value="+category.id+">"+category.name+"</option>";
				$(option).appendTo('#category'); 
			});  
		},
		error : function(jqXhr, textStatus, errorThrown) {
			//Check if the authentication was invalid, in which case return to index
			if (jqXhr.status == 401) {
				eraseCookie("access_token");
				window.location.href = "index.html";
			}
			console.log( errorThrown );
		}
	});

	$.ajax({
		url: 'http://10.129.32.15:8080/challenge/',
		type: 'get',
		dataType: 'json',
		beforeSend: function(xhr, settings) { 
			var access_token = readCookie("access_token");
			xhr.setRequestHeader('Authorization','Bearer ' + access_token);
		},
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i,challenge){
				var option="<option value="+challenge.id+">"+challenge.title+"</option>";
				$(option).appendTo('#challenge'); 
			});  
		},
		error : function(jqXhr, textStatus, errorThrown) {
			//Check if the authentication was invalid, in which case return to index
			if (jqXhr.status == 401) {
				eraseCookie("access_token");
				window.location.href = "index.html";
			}
			console.log( errorThrown );
		}
	});

	$.ajax({
		url: 'http://10.129.32.15:8080/charity',
		type: 'get',
		dataType: 'json',
		beforeSend: function(xhr, settings) { 
            var access_token = readCookie("access_token");
            xhr.setRequestHeader('Authorization','Bearer ' + access_token);
        },
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i,charity){
				var option="<option value="+charity.id+">"+charity.name+"</option>";
				$(option).appendTo('#charity'); 
			});  
		},
		error : function(jqXhr, textStatus, errorThrown) {
			//Check if the authentication was invalid, in which case return to index
			if (jqXhr.status == 401) {
				eraseCookie("access_token");
				window.location.href = "index.html";
			}
			console.log( errorThrown );
		}
	});

	var errormessages = "";

	var validateCategory = function() {
		var catOK = true;

		var $category = $('#category');

		// check category
		if ($category.val() <= '0') {
			catOK = false;
			errormessages += "Gelieve een categorie te kiezen";	
		}

		return catOK;
	}

	var validateChallenge = function() {
		var cahllOK = true;

		var $challenge = $('#challenge');

		// check challenge
		if ($challenge.val() <= '0') {
			challOK = false;
			errormessages += "Gelieve een challenge te kiezen";	
		}

		return cahllOK;
	}

	var getMeasure = function($challenge) {
		$('#measure').html("");
		if (validateChallenge()) {
			$.ajax({
				url: 'http://10.129.32.15:8080/challenge/' + $challenge,
				type: 'get',
				dataType: 'json',
				beforeSend: function(xhr, settings) { 
					var access_token = readCookie("access_token");
					xhr.setRequestHeader('Authorization','Bearer ' + access_token);
				},
				success: function(data, textStatus, jqXHR) {
					$('#measure').html(data.unitToMeasure);
				},
				error : function(jqXhr, textStatus, errorThrown) {
					//Check if the authentication was invalid, in which case return to index
					if (jqXhr.status == 401) {
						eraseCookie("access_token");
						window.location.href = "index.html";
					}
					console.log( errorThrown );
				}
			});
		}
	}

	$("#category").change(function() {
		$('#challenge').find('option').remove();
		if (validateCategory()) {
			var $category = $(this).val();
			$.ajax({
				url: 'http://10.129.32.15:8080/challenge/' + $category + '/category',
				type: 'get',
				dataType: 'json',
				beforeSend: function(xhr, settings) { 
					var access_token = readCookie("access_token");
					xhr.setRequestHeader('Authorization','Bearer ' + access_token);
				},
				success: function(data, textStatus, jqXHR) {
					$.each(data,function(i,challenge){
						var option="<option value="+challenge.id+">"+challenge.title+"</option>";
						$(option).appendTo('#challenge'); 
					});

					var $challenge = $("#challenge").val();
					getMeasure($challenge);
				},
				error : function(jqXhr, textStatus, errorThrown) {
					//Check if the authentication was invalid, in which case return to index
					if (jqXhr.status == 401) {
						eraseCookie("access_token");
						window.location.href = "index.html";
					}
					console.log( errorThrown );
				}
			});
		}
	});

	$("#challenge").change(function() {
		getMeasure($(this).val());
	});

	var validateForm = function() {
		var allOk = true;

		// input shortcuts
		var $count = $('#count');
		var $startDate = $('#startDate');
		var $endDate = $('#endDate');
		var $result = $('#result');
		var $amount = $('#amount');
		var $charity = $('#charity')

		var challCheck = validateChallenge();

		// check challenge
		if (!challCheck) {
			allOk = false;
		}
		// check count
		if ($count.val() <= '0') {
			allOk = false;
			$errName.html('Gelieve een groter aantal dan 0 in te vullen');		
		}

		if ($count.val() == '') {
			allOk = false;
			$errName.html('Gelieve een aantal in te vullen');		
		}
		
		// check charity
		if ($charity.val() <= '0') {
			allOk = false;
			$errCountry.html('Gelieve een charity te kiezen');			
		}

		// check startDate

		// check endDate

		// check amount
		if ($amount.val() <= '0') {
			allOk = false;
			errName.innerHTML = 'Gelieve een bedrag groter dan 0 in te vullen';		
		}

		if ($amount.val() == '') {
			allOk = false;
			errName.innerHTML = 'Gelieve een bedrag in te vullen';		
		}

		// check result
		if ($result.val() != 'false' && $result.val() != 'true') {
			allOk = false;
			console.log("error result");
		}

		return allOk;
	}

	// add novalidate to form
	$('#addChallenge__form').attr('novalidate', 'novalidate');

	$('#addChallenge__form').on('submit', function(e) {
		// halt event
		e.preventDefault();
		e.stopPropagation();

		// form checking
		var allOk = validateForm();

		// draw conclusion
		if (allOk) {
			// input shortcuts
			var $challenge = $('#challenge');
			var $count = $('#count');
			var $startDate = $('#startDate');
			var $endDate = $('#endDate');
			var $result = $('#result');
			var $amount = $('#amount');
			var $charity = $('#charity')
			
			$.ajax({
				url: 'http://10.129.32.15:8080/userchallenge',
				type: 'post',
				dataType: 'json',
				contentType: "application/json",
				beforeSend: function(xhr, settings) { 
					var access_token = readCookie("access_token");
					xhr.setRequestHeader('Authorization','Bearer ' + access_token);
				},
				data: JSON.stringify({
					amountToComplete: parseInt($count.val()),
					amountToDonate: parseInt($amount.val()),
					challengeId: parseInt($challenge.val()),
					charityId: parseInt($charity.val()),
					completeToDonate: ($result.val() == 'true'),
					deadlineDate: $endDate.val() + "T00:00:00.000Z",
					startDate: $startDate.val() + "T00:00:00.000Z"
				  }),
				  processData: false,
				success: function(data, textStatus, jqXHR) {
					window.location.href = "activechallenges.html";
				},
				error : function(jqXhr, textStatus, errorThrown) {
					//Check if the authentication was invalid, in which case return to index
					if (jqXhr.status == 401) {
						eraseCookie("access_token");
						window.location.href = "index.html";
					}
					console.log( errorThrown );
				}
			});
			$('#summary').removeClass('showSummary');
		} else {
			// show summary
			$('#summary').addClass('showSummary');
			$('#summary').css('display','block');
		}

	});
});