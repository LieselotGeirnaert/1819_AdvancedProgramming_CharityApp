$(document).ready(function() {
  var full_url = document.URL; // Get current url
  var url_array = full_url.split("="); // Split the string into an array with / as separator
  var id = url_array[url_array.length - 1]; // Get the last part of the array (-1)

  $.ajax({
    url: "http://10.129.32.15:8080/userchallenge/" + id,
    type: "get",
    dataType: "json",
    beforeSend: function(xhr, settings) {
      var access_token = readCookie("access_token");
      xhr.setRequestHeader("Authorization", "Bearer " + access_token);
    },
    success: function(data, textStatus, jqXHR) {
      var startdate = new Date(data.startDate);
      var enddate = new Date(data.deadlineDate);
      console.log(data);
      console.log(data.progressPercentage);
      var totalprogress =
        '<div class="progressbar__status" style="width:' +
        data.progressPercentage +
        '% "></div>';

      $("#title").append(data.challenge.title);
      $("#category").append(data.challenge.category[0].name);
      $("#unit").append(data.challenge.description);
      $("#start").append(
        startdate.getDate() +
          "/" +
          (startdate.getMonth() + 1) +
          "/" +
          startdate.getFullYear()
      );
      $("#end").append(
        enddate.getDate() +
          "/" +
          (enddate.getMonth() + 1) +
          "/" +
          enddate.getFullYear()
      );
      $("#charity").append(data.charity.name);
      $("#amount").append(data.amountToDonate);

      $("#totalprogressbar").append(totalprogress);

      $.each(data.dailyProgressesPercentages, function(day, progress) {
        var date = new Date(day);

        var progress =
          '<section class="progress__day"><h4>' +
          (date.getDate() +
            "/" +
            (date.getMonth() + 1) +
            "/" +
            date.getFullYear()) +
          '</h4><div class="progressbar"><div class="progressbar__status" style="width:' +
          progress +
          '%"></div></div></section >';
        $("#progress").append(progress);
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
