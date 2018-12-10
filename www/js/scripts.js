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
