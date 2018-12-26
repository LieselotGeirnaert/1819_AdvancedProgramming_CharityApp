$(document).ready(function() {
    $.ajax({
      url: "http://10.129.32.15:8080/challenge",
      dataType: "json",
      type: "get",
      beforeSend: function(xhr, settings) {
        var access_token = readCookie("access_token");
        xhr.setRequestHeader("Authorization", "Bearer " + access_token);
      },
      success: function(data, textStatus, jQxhr) {
        //Do something with the returned data here
        $.each(data, function(i, challenge) {
          var $challenge =
            '<section class="challengetile collapsed"><img src="' +
            challenge.linkToLogo +
            '" alt=""><div class="challengetile__info"><p>' +
            challenge.title +
            "</p><p>" +
            challenge.description +
            "</p></div></section>";
          $(".challenges").append($challenge);
        });
      },
      error: function(jqXhr, textStatus, errorThrown) {
        //Check if the authentication was invalid, in which case return to index
        if (jqXhr.status == 401) {
          eraseCookie("access_token");
          window.location.href = "index.html";
        }
        console.log(errorThrown);
      }
    });
  
    $(".challenges").on("click", ".challengetile", function(e) {
      $this = $(this);
  
      $this.toggleClass("collapsed");
      $this.toggleClass("extended");
    });
  });