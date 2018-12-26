$(window).on("load", function() {
  $("#hamburger").on("click", function(e) {
    e.preventDefault();
    e.stopPropagation();

    $("section#overlaymenu").css("display", "flex");
    $("section#content").css("display", "none");
  });

  $("#closeoverlay").on("click", function(e) {
    e.preventDefault();
    e.stopPropagation();

    $("section#overlaymenu").css("display", "none");
    $("section#content").css("display", "flex");
  });
});

$(".overlaycenter ul li a:contains('afmelden')").on("click", function(e) {
  e.preventDefault();
  e.stopPropagation();

  eraseCookie("access_token");
  window.location.href = "index.html";
});

function createCookie(name, value, days) {
  var expires;

  if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toGMTString();
  } else {
      expires = "";
  }
  document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
}

function readCookie(name) {
  var nameEQ = encodeURIComponent(name) + "=";
  var ca = document.cookie.split(';');
  for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) === ' ')
          c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) === 0)
          return decodeURIComponent(c.substring(nameEQ.length, c.length));
  }
  return null;
}

function eraseCookie(name) {
  createCookie(name, "", -1);
}