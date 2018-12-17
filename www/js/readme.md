# Authorised $.ajax template
Use the following template to perform ajax-calls to authorised domains
Replace url with the required API-endpoint and success and/or error with your desired response
DO NOT REMOVE THE 401-CHECK IN ERROR-HANDLING!
```javascript
    $.ajax({
        url: 'http://10.129.32.15:8080/user',
        dataType: 'json',
        type: 'get',
        beforeSend: function(xhr, settings) { 
            var access_token = readCookie("access_token");
            xhr.setRequestHeader('Authorization','Bearer ' + access_token);
        },
        success: function( data, textStatus, jQxhr ){
            //Do something with the returned data here
            console.log( data );
        },
        error: function( jqXhr, textStatus, errorThrown ){
            //Check if the authentication was invalid, in which case return to index
            if (jqXhr.status == 401) {
                eraseCookie("access_token");
                window.location.href = "index.html";
            }
            console.log( errorThrown );
        }
    });
```

# Example login-details
Email: "test@tester.be"
Password: "hash"