$(document).ready(function() {
  $.ajax({
    url: "http://10.129.32.15:8080/userchallenge",
    type: "get",
    dataType: "json",
    beforeSend: function(xhr, settings) {
      var access_token = readCookie("access_token");
      xhr.setRequestHeader("Authorization", "Bearer " + access_token);
    },
    success: function(data, textStatus, jqXHR) {
      $.each(data, function(i, challenge) {
        var chall =
          '<section class="challengetile"><img src="' +
          challenge.challenge.linkToLogo +
          '" alt=""><div class="challengetile__text"><p>' +
          challenge.challenge.category[0].name +
          "</p><p>" +
          challenge.challenge.description +
          "</p><p>" +
          challenge.challenge.unitToMeasure +
          '</p></div><div class="progressbar"><div class="progressbar__status" style="width:' +
          challenge.challenge.progressPercentage +
          '%"></div></div></section>';
        $("#challenges").append(chall);
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
});
