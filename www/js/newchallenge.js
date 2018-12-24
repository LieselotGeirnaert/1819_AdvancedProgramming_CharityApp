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

		var category = document.getElementById('category');

		// check category
		if (category.value <= '0') {
			catOK = false;
			errormessages += "Gelieve een categorie te kiezen";	
			
		}

		return catOK;
	}

	var validateChallenge = function() {
		var cahllOK = true;

		var challenge = document.getElementById('challenge');

		// check challenge
		if (challenge.value <= '0') {
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
		var count = document.getElementById('count');
		var startDate = document.getElementById('startDate');
		var endDate = document.getElementById('endDate');
		var result = document.getElementById('result');
		var amount = document.getElementById('amount');
		var charity = document.getElementById('charity')

		var challCheck = validateChallenge();

		// check challenge
		if (!challCheck) {
			allOk = false;
		}
		// check count
		if (count.value <= '0') {
			allOk = false;
			errName.innerHTML = 'Gelieve een groter aantal dan 0 in te vullen';		
		}

		if (count.value == '') {
			allOk = false;
			errName.innerHTML = 'Gelieve een aantal in te vullen';		
		}
		
		// check charity
		if (charity.value <= '0') {
			allOk = false;
			errCountry.innerHTML = 'Gelieve een charity te kiezen';			
		}

		// check startDate

		// check endDate

		// check amount
		if (amount.value <= '0') {
			allOk = false;
			errName.innerHTML = 'Gelieve een bedrag groter dan 0 in te vullen';		
		}

		if (amount.value == '') {
			allOk = false;
			errName.innerHTML = 'Gelieve een bedrag in te vullen';		
		}

		// check result
		if (result != false || result != true){
			allOk = false;
		}

		return allOk;
	}

	// add novalidate to form
	document.getElementById('addChallenge__form').setAttribute('novalidate', 'novalidate');

	document.getElementById('addChallenge__form').addEventListener('submit', function(e) {
		// halt event
		e.preventDefault();
		e.stopPropagation();

		// form checking
		var allOk = validateForm();

		// draw conclusion
		if (allOk) {
			// input shortcuts
			var challenge = document.getElementById('challenge');
			var count = document.getElementById('count');
			var startDate = document.getElementById('startDate');
			var endDate = document.getElementById('endDate');
			var result = document.getElementById('result');
			var amount = document.getElementById('amount');
			var charity = document.getElementById('charity')
			
			$.ajax({
				url: 'http://10.129.32.15:8080/userchallenge',
				type: 'post',
				dataType: 'json',
				contentType: "application/json",
				beforeSend: function(xhr, settings) { 
					var access_token = readCookie("access_token");
					xhr.setRequestHeader('Authorization','Bearer ' + access_token);
				},
				data: {
					"amountToComplete": count.value,
					"amountToDonate": amount.value,
					"challengeId": challenge.value,
					"charityId": charity.value,
					"completeToDonate": result.value,
					"deadlineDate": start.value + ":00.000Z",
					"startDate": end.value + ":00.000Z"
				},
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
		} else {
			// show summary
			document.getElementById('summary').className = 'showSummary';
			document.getElementById('summary').style.display = 'block';
		}

	});
});