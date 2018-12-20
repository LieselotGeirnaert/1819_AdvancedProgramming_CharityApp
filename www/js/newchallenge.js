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
			errormessages += "Gelieve een categorie te kiezen";	
			
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
		var start = document.getElementById('start');
		var end = document.getElementById('end');
		var result = document.getElementById('result');
		var amount = document.getElementById('amount');
		var charity = document.getElementById('charity')

		var challCheck = validateChallenge();

		// check count
		if (count.value <= '0') {
			allOk = false;
			errName.innerHTML = 'gelieve een naam in te vullen';		
		}

		if (count.value == '') {
			allOk = false;
			errName.innerHTML = 'gelieve een naam in te vullen';		
		}
		
		// check charity
		if (charity.value <= '0') {
			allOk = false;
			errCountry.innerHTML = 'Gelieve een charity te kiezen';			
		}

		// check 

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
			var start = document.getElementById('start');
			var end = document.getElementById('end');
			var result = document.getElementById('result');
			var amount = document.getElementById('amount');
			var charity = document.getElementById('charity')

			$.ajax({
				url: 'http://10.129.32.15:8080/charity',
				type: 'post',
				dataType: 'json',
				beforeSend: function(xhr, settings) { 
					var access_token = readCookie("access_token");
					xhr.setRequestHeader('Authorization','Bearer ' + access_token);
				},
				data: {
					"amountToComplete": count.value,
					"amountToDonate": amount.value,
					"challenge": {
					  "id": challenge.value,
					},
					"charity": {
					  "id": charity.value,
					},
					"completeToDonate": result.value,
					"completed": false,
					"deadlineDate": start.value,
					"startDate": end.value
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