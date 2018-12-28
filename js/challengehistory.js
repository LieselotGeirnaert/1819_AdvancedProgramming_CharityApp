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
      var completedChallenges = [];
      $.each(data, function(i, challenge) {
        if (challenge.completed == true) {
          completedChallenges.push(challenge);
        }
      });

      if (completedChallenges.length > 0) {
        $.each(completedChallenges, function(i, challenge) {
          var chall =
            '<a href="detailchallenge.html?id=' +
            challenge.id +
            '" class="challengetile"><img src="' +
            challenge.challenge.linkToLogo +
            '" alt=""><div class="challengetile__text"><p>' +
            challenge.challenge.category[0].name +
            "</p><p>" +
            challenge.challenge.title +
            "</p><p>" +
            challenge.amountToCompleteDaily +
            " " +
            challenge.challenge.unitToMeasure.toLowerCase() +
            '</p></div><div class="progressbar"><div class="progressbar__status" style="width:' +
            challenge.challenge.progressPercentage +
            '%"></div></div></a>';
          $("#challenges").append(chall);
        });
      } else if (completedChallenges <= 0) {
        var input =
          "<p>nog geen be&euml;indigde challenges om weer te geven</p>";
        $("#challenges").append(input);
      }
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
