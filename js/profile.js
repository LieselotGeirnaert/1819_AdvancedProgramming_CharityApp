$(document).ready(function() {
  $.ajax({
    url: "http://10.129.32.15:8080/user",
    type: "get",
    dataType: "json",
    beforeSend: function(xhr, settings) {
      var access_token = readCookie("access_token");
      xhr.setRequestHeader("Authorization", "Bearer " + access_token);
    },
    success: function(data, textStatus, jqXHR) {
      $("#name").append(data.firstName + "</br>" + data.lastName);
      $("#emailaddress").append(data.emailAddress);
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

  $.ajax({
    url: "http://10.129.32.15:8080/userchallenge",
    type: "get",
    dataType: "json",
    beforeSend: function(xhr, settings) {
      var access_token = readCookie("access_token");
      xhr.setRequestHeader("Authorization", "Bearer " + access_token);
    },
    success: function(data, textStatus, jqXHR) {
      var count = data.length;
      console.log(count);
      $("#challengecount").append(count + " actieve challenges");
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
});
