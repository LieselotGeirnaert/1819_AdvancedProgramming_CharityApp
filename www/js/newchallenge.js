$(document).ready(function() {
	$.ajax({
		url: 'http://10.129.32.15:8080/category',
		type: 'get',
		dataType: 'json',
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i,category){
				var option="<option value="+category.id+">"+category.name+"</option>";
				$(option).appendTo('#category'); 
			});  
		},
		error : function(jqXHR, textStatus, errorThrown) {
		  console.log(errorThrown);
		}
	});

	$.ajax({
		url: 'http://10.129.32.15:8080/charity',
		type: 'get',
		dataType: 'json',
		success: function(data, textStatus, jqXHR) {
			$.each(data,function(i,charity){
				var option="<option value="+charity.id+">"+charity.name+"</option>";
				$(option).appendTo('#charity'); 
			});  
		},
		error : function(jqXHR, textStatus, errorThrown) {
		  console.log(errorThrown);
		}
	});

	var errormessages = "";

	var validateCategory = function() {
		var catOK = true;

		var category = document.getElementById('#categorie');

		// check category
		if (category.value <= '0') {
			catOK = false;
			errormessages += "Gelieve een categorie te kiezen";	
			
		}

		return catOK;
	}

	$("#categorie").change(function() {
			$.ajax({
				url: 'http://10.129.32.15:8080/challenge',
				type: 'get',
				dataType: 'json',
				data:{ 
					// "VarA": VarA, 
					// "VarB": VarB, 
					// "VarC": VarC
				},
				success: function(data, textStatus, jqXHR) {
					$.each(data,function(i,challenge){
						var option="<option value="+challenge.id+">"+challenge.title+"</option>";
						$(option).appendTo('#challenge'); 
					});  
				},
				error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
				}
			});
		});

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



	var validateForm = function() {
		var allOk = true;

		// input shortcuts
		var count = document.getElementById('count');
		var start = document.getElementById('start');
		var end = document.getElementById('end');
		var result = document.getElementById('result');
		var amount = document.getElementById('amount');
		var charity = document.getElementById('charity')

		var catCheck = validateCategory();
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

		
		// check street and number
		if (qstStreet.value == '') {
			qstStreet.classList.add("invalid");
			allOk = false;
			errStreet.innerHTML = 'gelieve een straat en nummer in te vullen';	
			errStreet.style.display = 'block';		
		} else {
			qstStreet.classList.add("valid");
		}

		// check zip
		if (qstZip.value == '') {
			qstZip.classList.add("invalid");
			allOk = false;
			errZip.innerHTML = 'gelieve een postcode in te vullen';	
			errZip.style.display = 'block';			
		} else {
			qstZip.classList.add("valid");
		}

		// check city
		if (qstCity.value == '') {
			qstCity.classList.add("invalid");
			allOk = false;
			errCity.innerHTML = 'gelieve een gemeente in te vullen';	
			errCity.style.display = 'block';			
		} else {
			qstCity.classList.add("valid");
		}

		// check country
		if (qstCountry.value == '-1') {
			allOk = false;
			errCountry.innerHTML = 'gelieve een land in te vullen';	
			errCountry.style.display = 'block';
			document.querySelector(".qstn9").classList.add("invalid");		
		} else {
			document.querySelector(".qstn9").classList.add("valid");
		}

		var selected = document.querySelectorAll('#qstIterests* input[type=checkbox]:checked');

		if(selected.length < 3){
			allOk = false;
			errInterests.innerHTML = 'gelieve meer dan twee interesses aan te duiden';	
			errInterests.style.display = 'block';
			selected[0].classList.add("invalid");
			selected[1].classList.add("invalid");
		} else {

		}

		// check data usage
		if (document.getElementById('share_yes').checked == true || document.getElementById('share_no').checked == true) {
			qstData.classList.add("valid");
		} else {
			allOk = false;
			errShare.innerHTML = 'gelieve akkoord te gaan met de gebruikersvoorwaarden';	
			errShare.style.display = 'block';
			qstData.classList.add("invalid");
		}

		// check terms of use
		if (qstAccept.checked == false) {
			allOk = false;
			errAccept.innerHTML = 'gelieve akkoord te gaan met de gebruikersvoorwaarden';	
			errAccept.style.display = 'block';
			qstAccept.classList.add("invalid");
		} else {
			qstAccept.classList.add("valid");
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
			// show thank you
			document.getElementById('form1').style.display = 'none';
			document.getElementById('succes').style.display = 'block';
			document.getElementById('summary').className = '';

		} else {
			// show summary
			document.getElementById('summary').className = 'showSummary';
			document.getElementById('summary').style.display = 'block';
		}

	});
});