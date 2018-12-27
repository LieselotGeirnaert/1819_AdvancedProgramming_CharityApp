$(document).ready(function() {
  // Get active userchallenges
  $.ajax({
    url: "http://10.129.32.15:8080/userchallenge",
    type: "get",
    dataType: "json",
    beforeSend: function(xhr, settings) {
      var access_token = readCookie("access_token");
      xhr.setRequestHeader("Authorization", "Bearer " + access_token);
    },
    success: function(data, textStatus, jqXHR) {
      var activeChallenges = [];
      $.each(data, function(i, challenge) {
        if (challenge.completed == false) {
          activeChallenges.push(challenge);
        }
      });
      
      if (activeChallenges.length > 0) {
        $.each(activeChallenges, function(i, userChallenge) {
          if (userChallenge.completed == false) {
            var chall =
              '<section class="challengetile"><a href="detailchallenge.html?id=' +
              userChallenge.id +
              '"><div class="challengetile__info"><img src="' +
              userChallenge.challenge.linkToLogo +
              '" alt=""><div class="challengetile__text"><p>' +
              userChallenge.challenge.category[0].name +
              "</p><p>" +
              userChallenge.challenge.title +
              "</p><p>" +
              userChallenge.amountToCompleteDaily + ' ' + userChallenge.challenge.unitToMeasure.toLowerCase() +
              '</p></div><div class="progressbar"><div class="progressbar__status" style="width:' +
              userChallenge.dailyProgressPercentage +
              '%"></div></div></div></a>;<div id="userchallenge-'+ userChallenge.id +'" class="challengetile__add"><p>&#10003;</p></div></section>';

              if (userChallenge.dailyProgressPercentage = 100) {
                var id = "userchallenge-" + userChallenge.id;
                $('#' + id).addClass('completed');
              }
            $("#challenges").append(chall);
          }
        });
      } else if (activeChallenges <= 0) {
        var input = "<p>nog geen actieve challenges om weer te geven.</p>";
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

  // Add dailyprogess
  $('#challenges').on('click', '.challengetile__add', function(){
    var $that = $(this);
    var userchallengeId = $(this).attr('id').replace('userchallenge-', '');

    if (!$(this).hasClass('completed')) {
      $.ajax({
        url: "http://10.129.32.15:8080/dailyprogress",
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(xhr, settings) { 
          var access_token = readCookie("access_token");
          xhr.setRequestHeader('Authorization','Bearer ' + access_token);
        },
        data: JSON.stringify({ 
            userChallengeId: parseInt(userchallengeId)
        }),
        processData: false,
        success: function(data, textStatus, jqXHR) {
          if (data.dailyProgressPercentage < 100) {
            $that.prev('a').find('.progressbar__status').css('width', data.dailyProgressPercentage);
          } else {
            $that.addClass('completed');
          }
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
});
