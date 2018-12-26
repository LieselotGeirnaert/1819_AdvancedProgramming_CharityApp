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
      console.log(data);
      var startdate = new Date(data.startDate);
      var enddate = new Date(data.deadlineDate);

      console.log(startdate);
      $("#title").append(data.challenge.title);
      $("#category").append(data.challenge.category.name);
      $("#unit").append(data.challenge.unitToMeasure);
      $("#start").append(
        startdate.getDay() + "/" + startdate.getMonth() + "/" + startdate.getFullYear()
      );
      $("#end").append(
        enddate.getDay() +
          "/" +
          enddate.getMonth() +
          "/" +
          enddate.getFullYear()
      );
      $("#charity").append(data.charity.name);
      $("#amount").append(data.amountToDonate);
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
