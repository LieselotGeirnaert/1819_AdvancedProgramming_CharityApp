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

    function login(username, password) {
        //Display login section
        $('section#login').css('display', 'block');
        $('section#register').css('display', 'none');

        //Style the buttons
        $('#loginbutton').addClass('selected');
        $('#registerbutton').removeClass('selected');

        //Disable login button
        $("#login__form #login").attr("disabled", "disabled");

        $.ajax({
            url: 'http://10.129.32.15:8080/oauth/token',
            dataType: 'json',
            type: 'post',
            data: { 
                'grant_type': 'password',
                'username': username,
                'password': password
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", "Basic " + btoa('char1Client' + ":" + 'f2a1ed52710d4533bde25be6da03b6e3'));
            },
            success: function( data, textStatus, jQxhr ){
                createCookie("access_token", data.access_token, 1);
                window.location.href = "activechallenges.html";
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
                //Reenable login button
                $("#login__form #login").removeAttr("disabled");
            }
        });
    }

    $('#login__form').on('submit', function(e) {
        e.preventDefault();
        e.stopPropagation();

        var email = $('#login__form #login__email').val();
        var password = $('#login__form #login__password').val();

        login(email, password);
    });

    $('#register__form').on('submit', function(e) {
        e.preventDefault();
        e.stopPropagation();

        var prename = $('#register__form #register__prename').val();
        var lastname = $('#register__form #register__lastname').val();
        var email = $('#register__form #register__email').val();
        var password = $('#register__form #register__password').val();

        $.ajax({
            url: 'http://10.129.32.15:8080/user',
            dataType: 'json',
            type: 'post',
            data: JSON.stringify( { "firstName": prename, "lastName": lastname, "emailAddress": email, "passwordResetDate": null, "passwordReset": null, "bankAccount": null  } ),
            processData: false,
            success: function( data, textStatus, jQxhr ){
                login(email, password);
            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
    });

    $('#register__form input').on('change', function(e) {
        var prename = $('#register__form #register__prename').val();
        var lastname = $('#register__form #register__lastname').val();
        var email = $('#register__form #register__email').val();
        var password = $('#register__form #register__password').val();
        var repeatpassword = $('#register__form #register__repeatpassword').val();
        
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