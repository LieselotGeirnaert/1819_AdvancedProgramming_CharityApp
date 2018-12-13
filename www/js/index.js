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

    $('#register__form').on('submit', function(e) {
        e.preventDefault();
        e.stopPropagation();

        var prename = $('#register__form #prename').val();
        var lastname = $('#register__form #lastname').val();
        var email = $('#register__form #email').val();
        var password = $('#register__form #password').val();

        $.ajax({
            url: 'http://10.129.32.15:8080/user',
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify( { "firstName": prename, "lastName": lastname, "emailAddress": email, "passwordResetDate": null, "passwordReset": null, "bankAccount": null  } ),
            processData: false,
            success: function( data, textStatus, jQxhr ){
                window.location.href = "activechallenges.html";
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
    });

    $('#register__form input').on('change', function(e) {
        var prename = $('#register__form #prename').val();
        var lastname = $('#register__form #lastname').val();
        var email = $('#register__form #email').val();
        var password = $('#register__form #password').val();
        var repeatpassword = $('#register__form #repeatpassword').val();
        
        var valid = true;

        if (prename == "") valid = false;
        if (lastname == "") valid = false;
        if (/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email) == false) valid = false;
        if (password == "") valid = false;
        if (password != repeatpassword) valid = false;

        if (valid) {
            $('#register__form #register').removeAttr("disabled");
        } else {
            $('#register__form #register').attr("disabled", "disabled");
        }
    });
    
    //Display login section by default
    $('#loginbutton').click();
});