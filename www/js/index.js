$(window).on('load', function() {
    $('#loginbutton').on('click', function(e) {
		e.preventDefault();
		e.stopPropagation();

        //Display login section
        $('section#login').css('display', 'block');
        $('section#register').css('display', 'none');

        //Style the buttons
        $('#loginbutton').addClass('selected');
        $('#registerbutton').removeClass('selected');
    });
    
    $('#registerbutton').on('click', function(e) {
		e.preventDefault();
		e.stopPropagation();

        //Display register section
        $('section#login').css('display', 'none');
        $('section#register').css('display', 'block');

        //Style the buttons
        $('#loginbutton').removeClass('selected');
        $('#registerbutton').addClass('selected');
    });

    //Tijdelijke brakka om een succesvolle login te simuleren
    //Uiteraard aanpassen van zodra de REST-API werkt
    $('#loginform').on('submit', function(e) {
        e.preventDefault();
        e.stopPropagation();
        
        window.location.href = "activechallenges.html";
    });
    
    //Display login section by default
    $('#loginbutton').click();
});