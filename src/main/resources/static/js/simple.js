// Activate Carousel
$("#carouselExampleIndicators").carousel();

$(".item").click(function () {
    $("#carouselExampleIndicators").carousel(1);
});

$(".left").click(function () {
    $("#carouselExampleIndicators").carousel("prev");
});


$(function () {

    var url = window.location.href;

    $("#nav-link a").each(function () {
        if (url == (this.href)) {
            $(this).closest("li").addClass("active");
        }
    });
});